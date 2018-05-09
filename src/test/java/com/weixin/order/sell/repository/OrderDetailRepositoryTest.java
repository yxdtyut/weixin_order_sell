package com.weixin.order.sell.repository;

import com.weixin.order.sell.dataobject.OrderDetail;
import com.weixin.order.sell.dataobject.ProductInfo;
import com.weixin.order.sell.utils.PrimaryKeyUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:05 2018/5/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        ProductInfo productInfo = productInfoRepository.findOne("1234567");
        BeanUtils.copyProperties(productInfo, orderDetail);
        orderDetail.setOrderId("1525845994765");
        orderDetail.setDetailId(PrimaryKeyUtils.createKey());
        orderDetail.setProductQuantity(2);
        repository.save(orderDetail);
    }
}