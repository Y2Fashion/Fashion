package com.accp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品表
 *
 */
public class Commodity implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;//商品id
    private String commodityName;//商品名称
    private Double originalPrice;//商品原价格
    private Double vipPrice;//商品会员价
    private String photoPath;//商品图片路径
    private String state;//状态
    private Integer type;//类型
    private String lId;//材质Id
    private Integer hits;//点击量

    private ThirdType thirdType;

   /* private SecondType secondType;

    private FirstType firstType;*/

   /* private Integer firstType;//一级类型Id

    private Integer secondType;//二级类型Id

    private Integer thirdType;//三级类型Id*/

    private Lining lining=new Lining();
    private List<Picture> pictures=new ArrayList<Picture>();

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public Lining getLining() {
        return lining;
    }

    public void setLining(Lining lining) {
        this.lining = lining;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public String getlId() {
        return lId;
    }

    public void setlId(String lId) {
        this.lId = lId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(Double vipPrice) {
        this.vipPrice = vipPrice;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public ThirdType getThirdType() {
        return thirdType;
    }

    public void setThirdType(ThirdType thirdType) {
        this.thirdType = thirdType;
    }

   /* public SecondType getSecondType() {
        return secondType;
    }

    public void setSecondType(SecondType secondType) {
        this.secondType = secondType;
    }

    public FirstType getFirstType() {
        return firstType;
    }

    public void setFirstType(FirstType firstType) {
        this.firstType = firstType;
    }*/
}
