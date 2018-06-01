package com.accp.biz;

import com.accp.entity.News;

import java.util.List;

public interface NewsBiz {
    List<News> getNews(String type);
}
