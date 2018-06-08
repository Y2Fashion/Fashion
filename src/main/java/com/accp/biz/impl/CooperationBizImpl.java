package com.accp.biz.impl;

import com.accp.biz.CooperationBiz;
import com.accp.dao.CooperationDao;
import com.accp.entity.Cooperation;
import com.accp.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CooperationBizImpl implements CooperationBiz {

    @Resource
    private CooperationDao cooperationDao;

    @Override
    public boolean addCooperation(Cooperation cooperation) {
        return cooperationDao.insertCooperation(cooperation)>0;
    }
}
