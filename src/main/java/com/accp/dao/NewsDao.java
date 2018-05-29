package com.accp.dao;

import com.accp.entity.News;

import java.util.List;

public interface NewsDao {

    List<News> selectNews(String type);
}
