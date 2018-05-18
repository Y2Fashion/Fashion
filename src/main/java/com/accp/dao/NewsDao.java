package com.accp.dao;

import com.accp.entity.News;

import java.util.List;

public interface NewsDao {

    /**
     * 新闻动态
     * @param news
     * @return
     */
    public List<News> findNews(News news);
}
