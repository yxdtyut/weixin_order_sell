package com.weixin.order.sell.service.impl;

import com.weixin.order.sell.dataobject.OrderDetail;
import com.weixin.order.sell.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午3:40 2018/5/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    private static final String OPEN_ID = "qwert";

    @Autowired
    private OrderServiceImpl service;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("杜牧");
        orderDTO.setBuyerAddress("后海");
        orderDTO.setBuyerPhone("13131313");
        orderDTO.setBuyerOpenid(OPEN_ID);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1234");
        o1.setProductQuantity(2);
        OrderDetail o2 = new OrderDetail();
        o2.setProductId("1234567");
        o2.setProductQuantity(2);
        orderDetailList.add(o2);
        orderDetailList.add(o1);
        orderDTO.setOrderDetailList(orderDetailList);

        service.create(orderDTO);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = service.findOne("1525852975452");
        log.info("[findOne 查询订单DTO:orderDTO = {}]", orderDTO);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = service.findList(OPEN_ID, pageRequest);
        log.info("[findList 根据open_id查询订单集合: {}]", orderDTOPage.getContent());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = service.findOne("1525845994765");
        OrderDTO orderDTOcancel = service.cancel(orderDTO);
        Assert.assertEquals(new Integer(2),orderDTOcancel.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = service.findOne("1525853021109");
        service.finish(orderDTO);
        Assert.assertEquals(new Integer(1), orderDTO.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = service.findOne("1525853290946");
        service.paid(orderDTO);
        Assert.assertEquals(new Integer(1), orderDTO.getPayStatus());
    }

    @Test
    public void findList1() {
    }
}