package com.bl.ep.constant;

/**
 * @author： 李树林
 * @description：
 * @date： 2018/8/29 17:30
 */
public enum UserEnum {
    COLLECT_HOME(1,"首页收藏"),
    COLLECT_MERCHANDIZE(2,"商品收藏"),

    UPDATE_PASSWORD_SUCCESS(1, "修改成功"),
    UPDATE_PASSWORD_FAILED(2, "账号或密码错误");

    private int key;
    private String msg;

    private UserEnum(int key, String msg){
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
