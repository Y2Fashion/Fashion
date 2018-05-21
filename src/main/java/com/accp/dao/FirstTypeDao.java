package com.accp.dao;

import com.accp.entity.FirstType;

import java.util.List;

/***
 * 一级表
 */
public interface FirstTypeDao {
    /**
     * 查询全部一级表操作
     * @return
     */
    List<FirstType> selectList();
}
