package com.weixin.order.sell.service;

import com.weixin.order.sell.dto.OrderDTO;

/**
 * @Author : yangxudong
 * @Description :   买家
 * @Date : 下午2:14 2018/5/11
 */

public interface BuyerService {
    OrderDTO findOrderOne(String openid, String orderId);

    OrderDTO cancelOrder(String openid, String orderId);
}
