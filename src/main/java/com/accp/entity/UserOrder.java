package com.accp.entity;

import java.io.Serializable;
import java.sql.Date;

public class UserOrder implements Serializable {
    private static final long serialVersionUID=1L;
    private Integer id;//id
    private Integer commodityId;//商品ID
    private Integer threeTypeId;//类型ID
    private Integer countCID;//数量
    private Integer orderID;//客户ID
    private Date createTime;//创建时间
    private Integer time;
    private Commodity commodity =new Commodity();
    private String Address;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getThreeTypeId() {
        return threeTypeId;
    }

    public void setThreeTypeId(Integer threeTypeId) {
        this.threeTypeId = threeTypeId;
    }

    public Integer getCountCID() {
        return countCID;
    }

    public void setCountCID(Integer countCID) {
        this.countCID = countCID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }
}
