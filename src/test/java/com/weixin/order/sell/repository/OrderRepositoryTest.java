package com.weixin.order.sell.repository;

import com.weixin.order.sell.dataobject.OrderMaster;
import com.weixin.order.sell.utils.PrimaryKeyUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午1:55 2018/5/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void addTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(PrimaryKeyUtils.createKey());
        orderMaster.setBuyerName("李白");
        orderMaster.setBuyerPhone("123123123123");
        orderMaster.setBuyerAddress("唐朝三里屯");
        orderMaster.setBuyerOpenid("asdf");
        orderMaster.setOrderAmount(new BigDecimal(99.99));
        repository.save(orderMaster);
    }
}