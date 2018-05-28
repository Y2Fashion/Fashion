package com.accp.biz;

import com.accp.entity.News;
import com.accp.util.Pager;

import java.util.List;

public interface NewsBiz {
    /**
     * 分类查询新闻并分页
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pager<News> findNew(String type,Integer pageNo,Integer pageSize);
    /**
     * 查询新闻类型
     * @return
     */
    public List<News> findType();
}
