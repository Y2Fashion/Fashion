package com.accp.entity;

import java.io.Serializable;

public class AccessingData implements Serializable {
    private static final long serialVersionUID=1L;
    private Integer id;//
    private String ip;//  访问者IP
    private Integer cId;//  商品ID
    private Integer lookTime;//  浏览时间/分钟
    private Integer lookCount;//  浏览次数
    private Integer typeID;


    public AccessingData() {
    }

    public AccessingData(String IP, Integer cId, Integer lookTime, Integer lookCount,Integer typeID) {
        this.ip = IP;
        this.cId = cId;
        this.lookTime = lookTime;
        this.lookCount = lookCount;
        this.typeID=typeID;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getLookTime() {
        return lookTime;
    }

    public void setLookTime(Integer lookTime) {
        this.lookTime = lookTime;
    }

    public Integer getLookCount() {
        return lookCount;
    }

    public void setLookCount(Integer lookCount) {
        this.lookCount = lookCount;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }
}
