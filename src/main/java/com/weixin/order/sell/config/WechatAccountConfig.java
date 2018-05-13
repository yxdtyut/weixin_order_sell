package com.weixin.order.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author : yangxudong
 * @Description :   微信账号的配置
 * @Date : 下午3:36 2018/5/12
 */
@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    /**微信公众平台的appid.*/
    private String mpAppid;

    /**微信公众平台的appSecret.*/
    private String mpAppsecret;
}
