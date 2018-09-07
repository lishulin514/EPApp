package com.bl.ep.model;

import com.bl.ep.pojo.Request;

/**
 * @author： 李树林
 * @description：
 * @date： 2018/9/7 9:20
 */
public class RequestModel extends Request{
    private boolean myRequest;

    public boolean isMyRequest() {
        return myRequest;
    }

    public void setMyRequest(boolean myRequest) {
        this.myRequest = myRequest;
    }
}
