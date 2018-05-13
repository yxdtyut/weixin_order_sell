package com.weixin.order.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @Author : yangxudong
 * @Description :   前端表单提交过来的订单
 * @Date : 上午10:10 2018/5/11
 */
@Data
public class OrderForm {
    @NotEmpty(message = "姓名不能为空")
    private String name;
    @NotEmpty(message = "手机不能为空")
    private String phone;
    @NotEmpty(message = "地址不能为空")
    private String address;
    @NotEmpty(message = "微信openid不能为空")
    private String openid;
    @NotEmpty(message = "购物车不能为空")
    private String items;

}
