package com.weixin.order.sell.Vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description :   商品类目的vo
 * @Date : 下午3:25 2018/5/8
 */
@Data
public class CategoryVo {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;
}
