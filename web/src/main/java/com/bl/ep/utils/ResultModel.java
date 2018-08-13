package com.bl.ep.utils;

import java.util.List;

/**
 *  200:表示成功
 *  500：表示错误，错误信息在msessage中
 *  501bean验证错误，不管多少个错误都以map形式返回
 *  502：拦截器拦截到用户token出错
 *  555：异常抛出信息
 */
public class ResultModel {

    private int status = 200;
    private String msg = null;
    private Object data = null;

    public ResultModel(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static ResultModel ok(Object data){

        return new ResultModel(200, "OK", data);
    }
}
