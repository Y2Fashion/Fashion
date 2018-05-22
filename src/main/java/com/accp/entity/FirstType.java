package com.accp.entity;

import java.io.Serializable;

/**
 * 一级分类表
 *
 */
public class FirstType implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer    fId;//一级Id
    private String firstType;//一级类型

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getFirstType() {
        return firstType;
    }

    public void setFirstType(String firstType) {
        this.firstType = firstType;
    }
}
