package com.weixin.order.sell.controller;

import com.weixin.order.sell.Vo.ResultVo;
import com.weixin.order.sell.convertor.OrderForm2OrderDTOConvertor;
import com.weixin.order.sell.dataobject.OrderMaster;
import com.weixin.order.sell.dto.OrderDTO;
import com.weixin.order.sell.enums.ResultEnum;
import com.weixin.order.sell.exception.SellException;
import com.weixin.order.sell.form.OrderForm;
import com.weixin.order.sell.service.BuyerService;
import com.weixin.order.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 上午10:09 2018/5/11
 */
@RequestMapping("/buyer/order")
@RestController
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    @PostMapping("/create")
    public ResultVo<Map<String, Object>> create(@Valid OrderForm orderForm, BindingResult result) {
        if (result.hasErrors()) {
            log.error("【创建订单参数有误】,orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    result.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConvertor.convertor(orderForm);
        OrderDTO orderDTOResult = orderService.create(orderDTO);
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderDTOResult.getOrderId());
        return ResultVo.success(map);
    }

    @GetMapping("list")
    public ResultVo<List<OrderMaster>> orderList(@RequestParam("openid") String openid,
                                                 @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageable);
        return ResultVo.success(orderDTOPage.getContent());
    }

    @GetMapping("/detail")
    public ResultVo<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        return ResultVo.success(buyerService.findOrderOne(openid,orderId));
    }

    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid") String openid,
                        @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.cancelOrder(openid, orderId);
        return ResultVo.success();
    }
}
