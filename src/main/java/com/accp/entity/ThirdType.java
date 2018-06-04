package com.accp.entity;

import java.io.Serializable;

/**
 * 三级分类表
 */

public class ThirdType implements Serializable {

    private static final long serialVersionUID = 1L;
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

    public ThirdType(Integer tId, String thirdType, Integer sId) {
        this.tId = tId;
        this.thirdType = thirdType;
        this.sId = sId;
    }
}