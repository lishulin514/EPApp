package com.bl.ep.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

    private int id;
    private String username;
    /**
     * 不打印
     */
    @JsonIgnore
    private String password;
    /**
     * 在没有数据的时候不返回
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String realname;
    /**
     * 在没有数据的时候不返回
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int age;
    /**
     * 在没有数据的时候不返回
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int sex;

    /**
     * Data 打印格式
     */
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale = "zh", timezone = "GMT+8")
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
