package com.weixin.order.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 上午9:26 2018/5/12
 */
@Controller
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {
    @GetMapping("auth")
    public void auth(@RequestParam("code") String code,
                     @RequestParam("state") String state) {
        log.info("【微信网页授权】");
        log.info("【微信网页授权】,code={}",code);
        log.info("【微信网页授权】,state={}",state);
        String url ="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxc277c20f864b1cce&secret=edaa34f986b94e17cc8215fb80dcf526&code="+code
                +"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("【微信网页授权】,获取access_token, response={}",response);
    }
}
