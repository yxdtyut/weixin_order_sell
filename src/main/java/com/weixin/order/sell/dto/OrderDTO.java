package com.weixin.order.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.weixin.order.sell.dataobject.OrderDetail;
import com.weixin.order.sell.enums.OrderStatusEnum;
import com.weixin.order.sell.enums.PayStatusEnum;
import com.weixin.order.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author : yangxudong
 * @Description :   订单的dto
 * @Date : 下午2:41 2018/5/9
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private String orderId;

    /**买家名字.*/
    private String buyerName;

    /**买家电话.*/
    private String buyerPhone;

    /**买家地址.*/
    private String buyerAddress;

    /**
     * 买家微信openid.
     */
    private String buyerOpenid;

    /** 订单总金额.*/
    private BigDecimal orderAmount;

    /**订单状态, 默认为新下单.*/
    private Integer orderStatus = OrderStatusEnum.NEW_ORDER.getCode();

    /**支付状态, 默认未支付.*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**创建时间.*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /**修改时间.*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    /**
     * 商品详情集合.
     */
    private List<OrderDetail> orderDetailList;
}
