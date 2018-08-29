package com.bl.ep.constant;

public enum ResultEnum {
    ERROR_404(404,"地址未找到"),
    ERROR_405(405,"请求类型不支持"),
    ERROR_500(500,"服务器异常"),

    COLLECT_HOME(1,"首页收藏"),
    COLLECT_MERCHANDIZE(2,"商品收藏");

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
