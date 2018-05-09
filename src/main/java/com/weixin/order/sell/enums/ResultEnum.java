package com.weixin.order.sell.enums;

import lombok.Getter;

/**
 * @Author : yangxudong
 * @Description :  结果枚举
 * @Date : 下午3:32 2018/5/9
 */
@Getter
public enum ResultEnum {
    PRODUCT_NULL(0,"商品不存在"),
    PRODUCT_STOCK_NOT_ENOUGH(1,"商品库存不足");
    private Integer code;
    private String msg;

    private ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
