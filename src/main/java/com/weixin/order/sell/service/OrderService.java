package com.weixin.order.sell.service;

import com.weixin.order.sell.dataobject.OrderMaster;
import com.weixin.order.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description :   订单service
 * @Date : 下午2:28 2018/5/9
 */

public interface OrderService {
    /**创建订单.*/
    OrderDTO create(OrderDTO orderDTO);

    /**查询单个订单.*/
    OrderDTO findOne(String orderId);

    /**查询订单列表.*/
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /**取消订单.*/
    OrderDTO cancel(OrderDTO orderDTO);

    /**完结订单.*/
    OrderDTO finish(OrderDTO orderDTO);

    /**支付订单完成.*/
    OrderDTO paid(OrderDTO orderDTO);

    /**查询订单列表.*/
    Page<OrderDTO> findList(Pageable pageable);
}
