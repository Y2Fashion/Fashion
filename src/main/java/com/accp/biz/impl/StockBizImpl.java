package com.accp.biz.impl;

import com.accp.biz.StockBiz;
import com.accp.dao.StockDao;
import com.accp.entity.Stock;
import com.accp.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class StockBizImpl implements StockBiz {
    @Resource
    private StockDao stockDao;
    @Override
    public Pager<Stock> findStock(Integer pageNo, Integer pageSize, Integer cmId, Integer clId, Integer fId) {
        if(null==pageNo){
            pageNo=1;
        }
        int num=(stockDao.findCount(cmId,clId,fId)+pageSize-1)/pageSize;
        if(pageNo>num){
            pageNo=num;
        }
        Pager<Stock> pager=new Pager<Stock>();
        pager.setPageNo(pageNo);
        pager.setPageSize(pageSize);
        pager.setDatas(stockDao.findStock((pageNo-1)*pageSize,pageSize,cmId,clId,fId));
        pager.setTotalPage(num);
        pager.setTotalRows(stockDao.findCount(cmId,clId,fId));
        return pager;
    }

    @Override
    public Stock findId(Integer id) {
        return stockDao.findId(id);
    }

    @Override
    public int del(Integer id) {
        return stockDao.del(id);
    }

    @Override
    public int add(Stock stock) {
        return stockDao.add(stock);
    }

    @Override
    public int update(Stock stock) {
        return stockDao.update(stock);
    }
}
