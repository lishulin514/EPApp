package com.bl.ep.model;

import com.bl.ep.pojo.User;

/**
 * @author： 李树林
 * @description：
 * @date： 2018/8/29 15:41
 */
public class UserModel extends User{

    private String host;

    private String port;

    private String imagePath;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
