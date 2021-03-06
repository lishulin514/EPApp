package com.bl.ep.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author： 李树林
 * @description：
 * @date： 2018/8/27 14:41
 */
@Table(name="m_merchandize_category")
public class MerchandizeCategory {

    private Integer id;
    @Column(name = "merchandize_id")
    private Integer merchandizeId;
    private String title;
    private String image;
    private String url;
    @Column(name = "now_price")
    private Double nowPrice;
    @Column(name = "old_price")
    private Double oldPrice;
    @Column(name = "default_opt")
    private Byte defaultOpt;
    @Column(name = "create_time")
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale = "zh", timezone = "GMT+8")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchandizeId() {
        return merchandizeId;
    }

    public void setMerchandizeId(Integer merchandizeId) {
        this.merchandizeId = merchandizeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(Double nowPrice) {
        this.nowPrice = nowPrice;
    }

    public Double getOldPrice() {
        return oldPrice;
    }


    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Byte getDefaultOpt() {
        return defaultOpt;
    }

    public void setDefaultOpt(Byte defaultOpt) {
        this.defaultOpt = defaultOpt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
