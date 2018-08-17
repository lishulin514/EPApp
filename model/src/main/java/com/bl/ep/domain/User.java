package com.bl.ep.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

    private int id;
    private String name;
    /**
     * 不打印
     */
    @JsonIgnore
    private String password;
    private Integer age;
    /**
     * Data 打印格式
     */
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale = "zh", timezone = "GMT+8")
    private Date birthday;
    /**
     * 在没有数据的时候不返回
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
