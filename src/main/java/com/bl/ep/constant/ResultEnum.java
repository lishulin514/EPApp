package com.bl.ep.constant;

public enum ResultEnum {
    NONO_USER(2000,"没有这个用户"),


    ERROR_400(400,"缺少必要参数"),
    ERROR_404(404,"地址未找到"),
    ERROR_405(405,"请求类型不支持"),
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
