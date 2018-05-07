package com.weixin.order.sell.repository;

import com.weixin.order.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午10:01 2018/5/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Test
    public void getProductCategoryInfo() {
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        System.out.println(productCategory);
    }

    @Test
    @Transactional
    public void saveProductCategory() {
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        productCategory.setCategoryName("儿童款");
        productCategory.setCategoryType(1);
        ProductCategory save = productCategoryRepository.save(productCategory);
        Assert.assertNotNull(save);
        //Assert.assertNotEquals(null, save);
    }

    @Test
    public void getProductCategoryByTypeList() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<ProductCategory> byCategoryTypeIn = productCategoryRepository.findByCategoryTypeIn(list);
        for (ProductCategory productCategory : byCategoryTypeIn) {
            log.info(productCategory.toString());
        }
        Assert.assertNotEquals(0, byCategoryTypeIn.size());
    }
}