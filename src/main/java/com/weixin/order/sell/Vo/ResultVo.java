package com.weixin.order.sell.Vo;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :   与前端交互的返回对象
 * @Date : 下午3:07 2018/5/8
 */
@Data
public class ResultVo<T> {
    private Integer code;
    private String msg;
    private T data;

    public ResultVo(CodeMsg codeMsg, T data) {
        this.msg = codeMsg.getMsg();
        this.code = codeMsg.getCode();
        this.data = data;
    }

    public ResultVo(CodeMsg codeMsg) {
        this.msg = codeMsg.getMsg();
        this.code = codeMsg.getCode();
    }

    public ResultVo() {
    }

    public static ResultVo success(Object object) {
        ResultVo resutlVo = new ResultVo<>(CodeMsg.success,object);
        return resutlVo;
    }

    public static ResultVo success() {
        ResultVo resutlVo = new ResultVo<>(CodeMsg.success);
        return resutlVo;
    }

    public static ResultVo fail(CodeMsg codeMsg) {
        ResultVo resutlVo = new ResultVo<>(codeMsg);
        return resutlVo;
    }
}
