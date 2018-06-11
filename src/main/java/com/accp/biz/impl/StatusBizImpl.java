package com.accp.biz.impl;

import com.accp.biz.StatusBiz;
import com.accp.dao.StatusDao;
import com.accp.entity.Status;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class StatusBizImpl implements StatusBiz {
    @Resource
    private StatusDao statusDao;
    @Override
    public List<Status> getAll() {
        return statusDao.getAll();
    }
}
