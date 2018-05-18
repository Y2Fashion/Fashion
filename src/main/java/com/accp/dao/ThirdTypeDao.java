package com.accp.dao;

import com.accp.entity.ThirdType;

import java.util.List;

public interface ThirdTypeDao  {
    /**
     * 查询三级列表
     * @param thirdType
     * @return
     */
    public List<ThirdType> findAll(ThirdType thirdType);
}
