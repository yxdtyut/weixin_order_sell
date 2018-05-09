package com.weixin.order.sell.repository;

import com.weixin.order.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午1:54 2018/5/9
 */

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String orderId);
}
