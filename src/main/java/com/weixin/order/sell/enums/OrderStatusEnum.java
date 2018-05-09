package com.weixin.order.sell.enums;

import lombok.Getter;

/**
 * @Author : yangxudong
 * @Description :   订单状态枚举
 * @Date : 下午1:41 2018/5/9
 */
@Getter
public enum OrderStatusEnum {
    NEW_ORDER(0,"新下单");
    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
