package com.weixin.order.sell.service;

import com.weixin.order.sell.dataobject.ProductInfo;
import com.weixin.order.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description :   商品service
 * @Date : 下午2:26 2018/5/8
 */

public interface ProductInfoService {
    ProductInfo findOne(String productId);

    /**获取上架的商品集合.*/
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**加库存.*/
    void increseStock(List<CartDTO> cartDTOList);

    /**减库存.*/
    void decreseStock(List<CartDTO> cartDTOList);
}
