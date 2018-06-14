package com.accp.biz;

import com.accp.entity.Status;

import java.util.List;

public interface StatusBiz {
    /**
     * 查询所有
     * @return
     */
    public List<Status> getAll();
}
