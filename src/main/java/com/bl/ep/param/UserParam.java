package com.bl.ep.param;

import com.bl.ep.pojo.User;

public class UserParam extends User {

    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
