package com.accp.entity;

/**
 * 三级分类表
 */

public class ThirdType {
    private Integer tId;//三级id
    private String thirdType;// 三级类型
    private Integer sId;//二级ID

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String getThirdType() {
        return thirdType;
    }

    public void setThirdType(String thirdType) {
        this.thirdType = thirdType;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }
}