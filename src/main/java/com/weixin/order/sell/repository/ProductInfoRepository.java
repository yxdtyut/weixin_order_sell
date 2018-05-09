package com.weixin.order.sell.repository;

import com.weixin.order.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:18 2018/5/8
 */

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    List<ProductInfo> findByProductStatus(Integer status);
}
