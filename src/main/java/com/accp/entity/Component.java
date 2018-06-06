package com.accp.entity;

import java.io.Serializable;

/**
 * 布料成分
 */
public class Component implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String Com;//成分

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCom() {
        return Com;
    }

    public void setCom(String com) {
        Com = com;
    }
}
