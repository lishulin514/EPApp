package com.bl.ep.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name="t_user")
public class User{
    @Id
    private Integer id;

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
    private Integer age;
    /**
     * 在没有数据的时候不返回
     * 0男 1女
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer sex;

    /**
     * 0未删除 1删除
     */
    @JsonIgnore
    @Column(name = "is_delete")
    private Integer isDelete;
    /**
     * Data 打印格式
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
//    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
