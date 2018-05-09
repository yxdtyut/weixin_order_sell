package com.weixin.order.sell.enums;

import lombok.Getter;

/**
 * @Author : yangxudong
 * @Description :   商品状态枚举
 * @Date : 下午2:37 2018/5/8
 */
@Getter
public enum ProductInfoStatusEnum {

    NOMAL(0,"正常"),UNNOMAL(1,"下架");

    private Integer id;
    private String msg;

    ProductInfoStatusEnum(Integer id, String msg) {
        this.id = id;
        this.msg = msg;
    }
}
