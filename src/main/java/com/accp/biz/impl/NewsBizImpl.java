package com.accp.biz.impl;

import com.accp.biz.NewsBiz;
import com.accp.dao.NewsDao;
import com.accp.entity.News;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class NewsBizImpl implements NewsBiz {
    @Resource
    private NewsDao dao;
    @Override
    public List<News> findNews(News news) {
        return dao.findNews(news);
    }
}
