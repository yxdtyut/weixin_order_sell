package com.weixin.order.sell.service.impl;

import com.weixin.order.sell.dto.OrderDTO;
import com.weixin.order.sell.enums.ResultEnum;
import com.weixin.order.sell.exception.SellException;
import com.weixin.order.sell.service.BuyerService;
import com.weixin.order.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:14 2018/5/11
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOpenIdFindOrderDTO(openid,orderId);
    }

    private OrderDTO checkOpenIdFindOrderDTO(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equals(openid)) {
            log.error("【订单查询】查询的不是本人的订单:openid={},orderId={}", openid, orderId);
            throw new SellException(ResultEnum.NOT_OWN_ORDER);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOpenIdFindOrderDTO(openid, orderId);
        if (orderDTO == null) {
            log.error("【订单查询】查询不到订单:openid={},orderId={}", openid, orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        OrderDTO orderDTOReturn = orderService.cancel(orderDTO);
        return orderDTOReturn;
    }
}
