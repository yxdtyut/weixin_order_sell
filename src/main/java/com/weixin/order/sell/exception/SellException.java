package com.weixin.order.sell.exception;

import com.weixin.order.sell.enums.ResultEnum;
import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午3:30 2018/5/9
 */
@Data
public class SellException extends RuntimeException {
    private Integer code;
    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
    public SellException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
