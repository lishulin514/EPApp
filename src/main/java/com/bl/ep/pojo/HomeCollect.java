package com.bl.ep.pojo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author： 李树林
 * @description：
 * @date： 2018/8/27 17:10
 */
@Table(name="t_home_collect")
public class HomeCollect extends BaseCollect{

    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "home_id")
    private Integer homeId;

    @Column(name = "is_delete")
    private Byte isDelete;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "create_time")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHomeId() {
        return homeId;
    }

    public void setHomeId(Integer homeId) {
        this.homeId = homeId;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
