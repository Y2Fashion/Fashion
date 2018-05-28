package com.accp.entity;

import java.io.Serializable;

public class Figure implements Serializable {
    private static final  long serialVersionUID = 1L;
    private Integer id;//风尚人物ID
    private String title;//标题
    private String content;//内容
    private String imagePath;// 图片地址
    private String IpPath;// 详细链接地址
    private String NAME;//人名

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getIpPath() {
        return IpPath;
    }

    public void setIpPath(String ipPath) {
        IpPath = ipPath;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
}
