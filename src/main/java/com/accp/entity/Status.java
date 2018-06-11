package com.accp.entity;

import java.io.Serializable;

/**
 * 状态表
 */
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String sta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSta() {
        return sta;
    }

    public void setSta(String sta) {
        this.sta = sta;
    }
}
