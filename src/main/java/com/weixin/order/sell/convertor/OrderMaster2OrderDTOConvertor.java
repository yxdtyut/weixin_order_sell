package com.weixin.order.sell.convertor;

import com.weixin.order.sell.dataobject.OrderMaster;
import com.weixin.order.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : yangxudong
 * @Description :   订单主表到订单dto的转换
 * @Date : 下午9:48 2018/5/9
 */

public class OrderMaster2OrderDTOConvertor {
    public static OrderDTO convertor(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convertor(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream()
                .map(x -> convertor(x))
                .collect(Collectors.toList());
    }

}
