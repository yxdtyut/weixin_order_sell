package com.weixin.order.sell.enums;

import lombok.Getter;

/**
 * @Author : yangxudong
 * @Description :   支付状态枚举
 * @Date : 下午1:43 2018/5/9
 */
@Getter
public enum PayStatusEnum {
    NOT_PAYED(0,"未支付");
    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
