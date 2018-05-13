package com.weixin.order.sell.enums;

import lombok.Getter;

/**
 * @Author : yangxudong
 * @Description :   支付状态枚举
 * @Date : 下午1:43 2018/5/9
 */
@Getter
public enum PayStatusEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    ;
    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
