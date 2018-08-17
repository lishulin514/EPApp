package com.bl.ep.utils;


import java.io.Serializable;

/**
 *  200:表示成功
 *  500：表示错误，错误信息在msessage中
 *  501bean验证错误，不管多少个错误都以map形式返回
 *  502：拦截器拦截到用户token出错
 *  555：异常抛出信息
 */
public class ResultModel<T>{

    private int status = 200;
    private String msg = null;
    private Object data = null;

    public ResultModel(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultModel<T> ok(T data){

        return new ResultModel<T>(200, "OK", data);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
