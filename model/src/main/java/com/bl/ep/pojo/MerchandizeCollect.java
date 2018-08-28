package com.bl.ep.pojo;

import com.bl.ep.interfaces.Collect;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author： 李树林
 * @description：
 * @date： 2018/8/27 16:57
 */
@Table(name="t_merchandize_collect")
public class MerchandizeCollect extends BaseCollect {

    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "merchandize_category_id")
    private Integer merchandizeCategoryId;

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

    public Integer getMerchandizeCategoryId() {
        return merchandizeCategoryId;
    }

    public void setMerchandizeCategoryId(Integer merchandizeCategoryId) {
        this.merchandizeCategoryId = merchandizeCategoryId;
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
