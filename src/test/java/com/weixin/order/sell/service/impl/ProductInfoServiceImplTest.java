package com.weixin.order.sell.service.impl;

import com.weixin.order.sell.dataobject.ProductInfo;
import com.weixin.order.sell.enums.ProductInfoStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:48 2018/5/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl service;

    @Test
    public void findOne() {
        ProductInfo one = service.findOne("1234567");
        Assert.assertNotNull(one);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = service.findUpAll();
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<ProductInfo> page = service.findAll(pageRequest);
        Assert.assertNotNull(page.getContent());
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("12345678");
        productInfo.setProductName("詹姆斯6代");
        productInfo.setProductPrice(new BigDecimal(8888));
        productInfo.setProductDescription("三旬老汉");
        productInfo.setProductIcon("http://xxxxxx.jsp");
        productInfo.setProductStatus(ProductInfoStatusEnum.NOMAL.getId());
        productInfo.setCategoryType(2);
        productInfo.setProductStock(88);
        ProductInfo productInfo1 = service.save(productInfo);
        Assert.assertNotNull(productInfo1);
    }
}