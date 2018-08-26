package com.bl.ep.constant;

public enum ResultEnum {
    ERROR_404(404,"地址错误"),
    ERROR_500(500,"服务器异常");

    private int key;
    private String msg;

    private ResultEnum(int key, String msg){
        this.key = key;
        this.msg = msg;
    }

    public int getKey() {
        return key;
    }

    public String getMsg() {
        return msg;
    }
}
