package com.bl.ep.pojo;

import com.bl.ep.molds.Collect;

/**
 * @author： 李树林
 * @description：
 * @date： 2018/8/27 20:17
 */
public class BaseCollect implements Collect {


    private String image;
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
