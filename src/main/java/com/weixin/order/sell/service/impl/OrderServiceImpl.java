package com.weixin.order.sell.service.impl;

import com.weixin.order.sell.convertor.OrderMaster2OrderDTOConvertor;
import com.weixin.order.sell.dataobject.OrderDetail;
import com.weixin.order.sell.dataobject.OrderMaster;
import com.weixin.order.sell.dataobject.ProductInfo;
import com.weixin.order.sell.dto.CartDTO;
import com.weixin.order.sell.dto.OrderDTO;
import com.weixin.order.sell.enums.OrderStatusEnum;
import com.weixin.order.sell.enums.PayStatusEnum;
import com.weixin.order.sell.enums.ResultEnum;
import com.weixin.order.sell.exception.SellException;
import com.weixin.order.sell.repository.OrderDetailRepository;
import com.weixin.order.sell.repository.OrderMasterRepository;
import com.weixin.order.sell.repository.ProductInfoRepository;
import com.weixin.order.sell.service.OrderService;
import com.weixin.order.sell.service.ProductInfoService;
import com.weixin.order.sell.utils.PrimaryKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : yangxudong
 * @Description :   订单service实现
 * @Date : 下午2:49 2018/5/9
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductInfoService productInfoService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = PrimaryKeyUtils.createKey();
        OrderMaster orderMaster = new OrderMaster();
        //商品总价
        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
            /**1.查询商品.*/
            ProductInfo productInfo = productInfoRepository.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NULL);
            }
            /**2.计算总价.*/
            totalPrice = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(totalPrice);
            /**3.保存订单详情表。*/
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setDetailId(PrimaryKeyUtils.createKey());
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);
        }
        /**4.保存订单主表.*/
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(totalPrice);
        orderMasterRepository.save(orderMaster);
        /**5.减库存.*/
        List<CartDTO> cartDTOS = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreseStock(cartDTOS);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        OrderDTO orderDTO = OrderMaster2OrderDTOConvertor.convertor(orderMaster);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConvertor.convertor(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalPages());
        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        /**判断订单状态.*/
        OrderMaster orderMaster = new OrderMaster();
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW_ORDER.getCode())) {
            log.error("[取消订单]订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        /**修改订单状态.*/
        orderDTO.setOrderStatus(OrderStatusEnum.CANSEL_ORDER.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateOrderMaster = orderMasterRepository.save(orderMaster);
        if (updateOrderMaster == null) {
            log.error("[取消订单]更新失败,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAILED);
        }
        /**查询订单详情.转换为购物车对象*/
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[取消订单]订单中无商品,orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                .stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());

        /**回退购买商品库存.*/
        productInfoService.increseStock(cartDTOList);
        /**如果已付款，则需要退款.*/
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS)) {
            //TODO
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {

        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW_ORDER.getCode())) {
            log.error("[完结订单]订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        /**修改订单状态为完成.*/
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH_ORDER.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster orderMasterUpdate = orderMasterRepository.save(orderMaster);
        if (orderMasterUpdate == null) {
            log.error("[完结订单]订单状态修改失败:orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAILED);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW_ORDER.getCode())) {
            log.error("[订单支付完成]订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("[订单支付完成]支付状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.PAY_STATUS_ERROR);
        }

        /**修改支付状态为已支付.*/
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster orderMasterUpdate = orderMasterRepository.save(orderMaster);
        if (orderMasterUpdate == null) {
            log.error("[订单支付完成]状态修改失败:orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAILED);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConvertor.convertor(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
        return orderDTOPage;
    }
}
