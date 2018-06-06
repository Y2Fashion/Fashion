package com.accp.entity;

import java.io.Serializable;

/**
 * 布料花型
 */
public class Flower implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String pattern;//成分

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
