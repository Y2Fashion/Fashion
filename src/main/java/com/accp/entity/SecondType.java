package com.accp.entity;

/**
 * 二级分类表
 */
public class SecondType {
    private Integer sId;//二级id
    private String secondType;//二级类型
    private Integer fId;//一级ID

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getSecondType() {
        return secondType;
    }

    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }
}
