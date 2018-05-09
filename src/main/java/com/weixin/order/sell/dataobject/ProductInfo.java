package com.weixin.order.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Author : yangxudong
 * @Description : 商品实体类
 * @Date : 下午2:11 2018/5/8
 */
@Entity
@Data
public class ProductInfo {
    @Id
    private String productId;

    /**商品名称.*/
    private String productName;

    /**单价.*/
    private BigDecimal productPrice;

    /**库存.*/
    private Integer productStock;

    /**描述.*/
    private String productDescription;

    /**小图.*/
    private String productIcon;

    /**商品状态,0正常1下架.*/
    private Integer productStatus;

    /**类目编号.*/
    private Integer categoryType;
}
