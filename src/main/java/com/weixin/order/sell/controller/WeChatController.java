package com.weixin.order.sell.controller;

import com.weixin.order.sell.enums.ResultEnum;
import com.weixin.order.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceBaseImpl;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午3:11 2018/5/12
 */
@Controller
@Slf4j
@RequestMapping("wechat")
public class WeChatController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        //调用方法
        String url = "http://yxdtyut.natapp1.cc/sell/wechat/userInfo";
        String redirectUrl = null;
        try {
            redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("【微信网页授权】,编码失败,error={}", e);
            throw new SellException(ResultEnum.ENCODER_ERROR);
        }
        log.info("【微信网页授权】,获取code,result={}", redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        log.info("【微信网页授权】，code={}", code);
        log.info("【微信网页授权】，returnUrl={}", returnUrl);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】，获取access_token失败,{}",e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String redirectUrl = returnUrl + "?openid=" + wxMpOAuth2AccessToken.getOpenId();
        log.info("【微信网页授权】获取到openid,重定向地址:" + redirectUrl);
        return "redirect:" + redirectUrl;
    }
}
