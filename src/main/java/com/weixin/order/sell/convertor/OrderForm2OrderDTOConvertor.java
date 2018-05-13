package com.weixin.order.sell.convertor;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.weixin.order.sell.dataobject.OrderDetail;
import com.weixin.order.sell.dto.OrderDTO;
import com.weixin.order.sell.enums.ResultEnum;
import com.weixin.order.sell.exception.SellException;
import com.weixin.order.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 上午10:15 2018/5/11
 */
@Slf4j
public class OrderForm2OrderDTOConvertor {

    public static OrderDTO convertor(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
            orderDetails = gson.fromJson(orderForm.getItems(), new TypeToken<List< OrderDetail>>(){}.getType());
        } catch (JsonSyntaxException e) {
            log.error("【对象转换】错误,string = {}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }
}
