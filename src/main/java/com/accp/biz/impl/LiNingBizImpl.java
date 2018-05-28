package com.accp.biz.impl;

import com.accp.biz.LiNingBiz;
import com.accp.dao.LiNingDao;
import com.accp.entity.Lining;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LiNingBizImpl implements LiNingBiz {

    @Resource
    private LiNingDao liNingDao;

    /*
     * 按id查询面料
     * */

    @Override
    public Lining getLiNingById(String id) {
        return liNingDao.selecttLiNingById(id);
    }
    /*
     * 查询所有面料
     * */
    @Override
    public List<Lining> getLiNingList() {
        return liNingDao.selectLiNingList();
    }


}
