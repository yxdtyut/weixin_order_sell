package com.weixin.order.sell.Vo;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午3:14 2018/5/8
 */
@Data
public class CodeMsg {
    private Integer code;
    private String msg;

    public CodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /** 成功.*/
    public static final CodeMsg success = new CodeMsg(0,"成功");

    /**失败.*/
    public static final CodeMsg failer = new CodeMsg(500,"失败");

}
