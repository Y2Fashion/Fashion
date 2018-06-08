package com.accp.entity;

import java.io.Serializable;

/**
 * 供应商渠道
 */
public class Channel implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer cmId;//成分ID
    private Integer clId;//颜色ID
    private Integer fId;//花型ID
    private Double price;//价格
    private Integer sId;//供应商ID
    private String col;//颜色
    private String Com;//成分
    private String pattern;//成分

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getCom() {
        return Com;
    }

    public void setCom(String com) {
        Com = com;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCmId() {
        return cmId;
    }

    public void setCmId(Integer cmId) {
        this.cmId = cmId;
    }

    public Integer getClId() {
        return clId;
    }

    public void setClId(Integer clId) {
        this.clId = clId;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }
}
