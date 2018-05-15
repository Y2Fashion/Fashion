package com.accp.entity;

/**
 *
 * 面料表
 *
 */
public class Lining {
    private Integer lId;//面料Id
    private String ingredient;//面料成分
    private String color;//面料颜色
    private String type;//花型

    public Integer getlId() {
        return lId;
    }

    public void setlId(Integer lId) {
        this.lId = lId;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
