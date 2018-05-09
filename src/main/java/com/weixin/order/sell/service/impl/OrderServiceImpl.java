package com.weixin.order.sell.service.impl;

import com.weixin.order.sell.dataobject.OrderDetail;
import com.weixin.order.sell.dataobject.OrderMaster;
import com.weixin.order.sell.dataobject.ProductInfo;
import com.weixin.order.sell.dto.CartDTO;
import com.weixin.order.sell.dto.OrderDTO;
import com.weixin.order.sell.enums.ResultEnum;
import com.weixin.order.sell.exception.SellException;
import com.weixin.order.sell.repository.OrderDetailRepository;
import com.weixin.order.sell.repository.OrderMasterRepository;
import com.weixin.order.sell.repository.ProductInfoRepository;
import com.weixin.order.sell.service.OrderService;
import com.weixin.order.sell.service.ProductInfoService;
import com.weixin.order.sell.utils.PrimaryKeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : yangxudong
 * @Description :   订单service实现
 * @Date : 下午2:49 2018/5/9
 */
@Service
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
            OrderDetail orderDetailSave = new OrderDetail();
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
            BeanUtils.copyProperties(productInfo, orderDetailSave);
            orderDetailSave.setDetailId(PrimaryKeyUtils.createKey());
            orderDetailSave.setOrderId(orderId);
            orderDetailSave.setProductQuantity(orderDetail.getProductQuantity());
            orderDetailRepository.save(orderDetailSave);
        }
        /**4.保存订单主表.*/
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
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
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        return null;
    }
}
