package com.weixin.order.sell.service;

import com.weixin.order.sell.dataobject.ProductCategory;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @Author : yangxudong
 * @Description :   商品类目的service
 * @Date : 下午1:51 2018/5/8
 */

public interface ProductCategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    ProductCategory save(ProductCategory productCategory);

    List<ProductCategory> findByCategoryTypeList(List<Integer> categoryTypeList);
}
