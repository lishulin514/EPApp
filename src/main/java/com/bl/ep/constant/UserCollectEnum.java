package com.bl.ep.constant;

/**
 * @author： 李树林
 * @description：
 * @date： 2018/8/29 17:30
 */
public enum UserCollectEnum {
    COLLECT_HOME(1,"首页收藏"),
    COLLECT_MERCHANDIZE(2,"商品收藏");

    private int key;
    private String msg;

    private UserCollectEnum(int key, String msg){
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
