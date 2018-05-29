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
    public Pager<News> findNew(String type, Integer pageNo, Integer pageSize) {

        if(null==pageNo){
            pageNo=1;
        }
        Pager<News> pager=new Pager<News>();
        pager.setPageNo(pageNo);
        pager.setPageSize(pageSize);
        pager.setDatas(dao.findNew(type,pageNo-1,pageSize));
        pager.setTotalPage(dao.findCout(type)/pageSize);
        pager.setTotalRows(dao.findCout(type));
        //System.out.println(pageNo+"\t"+dao.findCout(type)+"\t"+pageSize);
        return pager;
    }

    @Override
    public List<News> findType() {
        return dao.findType();
    }
}
