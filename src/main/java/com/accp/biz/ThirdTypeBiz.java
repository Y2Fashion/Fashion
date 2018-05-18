package com.accp.biz;

import com.accp.entity.ThirdType;

import java.util.List;

public interface ThirdTypeBiz {
    /**
     * 查询三级列表
     * @param thirdType
     * @return
     */
    public List<ThirdType> findAll(ThirdType thirdType);
}
