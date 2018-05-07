package com.weixin.order.sell.repository;

import com.weixin.order.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午10:00 2018/5/7
 */

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
