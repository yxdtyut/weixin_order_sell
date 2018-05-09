package com.weixin.order.sell.dto;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :   购物车
 * @Date : 下午3:18 2018/5/9
 */
@Data
public class CartDTO {
    private String productId;
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public CartDTO() {
    }
}
