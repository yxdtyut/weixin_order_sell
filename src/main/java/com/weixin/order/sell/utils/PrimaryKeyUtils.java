package com.weixin.order.sell.utils;

import java.util.Random;

/**
 * @Author : yangxudong
 * @Description :   主键生成的工具类
 * @Date : 下午1:57 2018/5/9
 */

public class PrimaryKeyUtils {
    public static String createKey() {
        Integer id = new Random().nextInt(900000) + 100000;
        long l = System.currentTimeMillis();
        return String.valueOf(l + id);
    }
}
