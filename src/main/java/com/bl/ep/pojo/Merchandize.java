package com.bl.ep.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name="m_merchandize")
public class Merchandize {

    private Integer id;
    private String title;
    private String image;
    private String url;
    private String model;
    @Column(name = "now_price")
    private Double nowPrice;
    @Column(name = "old_price")
    private Double oldPrice;
    private String brand;
    @Column(name = "create_time")
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale = "zh", timezone = "GMT+8")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
