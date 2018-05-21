package com.accp.biz.impl;

import com.accp.biz.CommodityBiz;
import com.accp.dao.CommodityDao;
import com.accp.entity.Commodity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommodityBizImpl implements CommodityBiz {
    @Resource
    private CommodityDao dao;
    @Override
    public List<Commodity> findType(Commodity commodity) {
        return dao.findType(commodity);
    }

    @Override
    public Commodity findId(Integer id) {
        return dao.findId(id);
    }
}
