package com.bl.ep.utils;

public enum ResultEnum {
    ERROR_404(404,"找不到页面"),
    ERROR_500(500,"服务器出问题了");

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
