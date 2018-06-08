package com.accp.entity;

import java.io.Serializable;

public class view implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Integer value;

    public view(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
