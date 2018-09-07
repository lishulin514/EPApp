package com.bl.ep.model;

import com.bl.ep.pojo.Answer;

/**
 * @author： 李树林
 * @description：
 * @date： 2018/9/7 9:20
 */
public class AnswerModel extends Answer{

    private boolean myRequest;

    public boolean isMyRequest() {
        return myRequest;
    }

    public void setMyRequest(boolean myRequest) {
        this.myRequest = myRequest;
    }
}
