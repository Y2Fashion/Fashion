package com.accp.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 新闻表
 *
 */
public class News implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;//新闻Id
    private String  title;//新闻标题
    private String content;//新闻内容
    private Date createDate;//创建日期
    private String type;//新闻类型
    private String nPath;//新闻图片路径
    private String ntv;//网络
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getnPath() {
        return nPath;
    }

    public void setnPath(String nPath) {
        this.nPath = nPath;
    }

    public String getNtv() {
        return ntv;
    }

    public void setNtv(String ntv) {
        this.ntv = ntv;
    }
}
