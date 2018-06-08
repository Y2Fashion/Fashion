package com.accp.dao;

import com.accp.entity.Status;

import java.util.List;

public interface StatusDao {
    /**
     * 查询所有
     * @return
     */
    public List<Status> getAll();
}
