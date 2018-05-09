package com.weixin.order.sell.service.impl;

import com.weixin.order.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午1:57 2018/5/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = categoryService.findOne(1);
        Assert.assertEquals(new Integer(1), productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> categoryList = categoryService.findAll();
        Assert.assertNotEquals(0, categoryList.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("NBA球星款", 5);
        ProductCategory save = categoryService.save(productCategory);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByCategoryTypeList() {
        List<ProductCategory> byCategoryTypeList = categoryService.findByCategoryTypeList(Arrays.asList(1, 2, 3));
        Assert.assertNotEquals(0, byCategoryTypeList.size());
    }
}