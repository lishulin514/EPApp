package com.bl.ep.param;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author： 李树林
 * @description：
 * @date： 2018/8/27 18:44
 */
public class CollectParam {

    private Integer collectType;

    public Integer getCollectType() {
        return collectType;
    }

    public void setCollectType(Integer collectType) {
        this.collectType = collectType;
    }
}
