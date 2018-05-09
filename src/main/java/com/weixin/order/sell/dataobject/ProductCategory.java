package com.weixin.order.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author : yangxudong
 * @Description : 商品类目实体类
 * @Date : 下午9:57 2018/5/7
 */
@Entity
@Data
@DynamicUpdate
public class ProductCategory {
    @Id
    @GeneratedValue
    private Integer categoryId;

    /**类目名字.*/
    private String categoryName;

    /**类目编号.*/
    private Integer categoryType;

    /**创建时间.*/
    private Date createTime;

    /**修改时间.*/
    private Date updateTime;

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public ProductCategory() {
    }
}
