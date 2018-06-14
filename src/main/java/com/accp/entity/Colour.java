package com.accp.entity;

import java.io.Serializable;

/**
 * 布料颜色
 */
public class Colour implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String col;//颜色

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }
}
