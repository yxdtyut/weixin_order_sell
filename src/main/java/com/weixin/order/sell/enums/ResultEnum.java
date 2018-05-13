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
    PRODUCT_STOCK_NOT_ENOUGH(1,"商品库存不足"),
    ORDER_NOT_EXIST(2,"订单不存在"),
    ORDER_STATUS_ERROR(3,"订单状态错误"),
    ORDER_UPDATE_FAILED(4,"订单更新失败"),
    ORDER_DETAIL_EMPTY(5,"订单详情为空"),
    PAY_STATUS_ERROR(6,"支付状态不正确"),
    PARAM_ERROR(7,"参数错误"),
    NOT_OWN_ORDER(8,"该订单不属于当前用户"),
    WECHAT_MP_ERROR(9,"微信公众号错误"),
    ENCODER_ERROR(10,"转码失败")
    ;
    private Integer code;
    private String msg;

    private ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
