package com.accp.biz;

import com.accp.entity.News;

import java.util.List;

public interface NewsBiz {
    /**
     * 新闻动态
     * @param news
     * @return
     */
    public List<News> findNews(News news);
}
