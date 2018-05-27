package com.accp.entity;

import java.io.Serializable;

public class Picture implements Serializable {
    private static final long serialVersionUID=1L;
    private Integer id;
    private Integer cId;//商品表Id
    private String Path;//图片地址；

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }
}
