package com.bl.ep.constant;


import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *  200:表示成功
 *  500：表示错误，错误信息在msessage中
 *  501bean验证错误，不管多少个错误都以map形式返回
 *  502：拦截器拦截到用户token出错
 *  555：异常抛出信息
 */
public class ResultModel<T>{

    private int status = 200;
    /**
     * 在没有数据的时候不返回
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg = null;
    /**
     * 在没有数据的时候不返回
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data = null;

    public ResultModel(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultModel<T> response(T data){

        return new ResultModel<T>(200, "success", data);
    }

    public static <T> ResultModel<T> error(ResultEnum status){

        return new ResultModel<T>(status.getKey(), status.getMsg(), null);
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
