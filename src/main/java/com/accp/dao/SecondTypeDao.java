package com.accp.dao;

import com.accp.entity.SecondType;

import java.util.List;

/**
 * 二级表
 */
public interface SecondTypeDao {
    /***
     * 查询二级表全部信息
     * @return
     */
    List<SecondType> selectList();
}
