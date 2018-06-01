package com.accp.biz.impl;

import com.accp.biz.NewsBiz;
import com.accp.dao.NewsDao;
import com.accp.entity.News;
import com.accp.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewBizImpl implements NewsBiz {
    @Resource
    private NewsDao dao;

    @Override
    public List<News> getNews(String type) {
        return dao.selectNews(type);
    }
}
