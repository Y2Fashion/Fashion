package com.accp.biz;

import com.accp.entity.FirstType;

import java.util.List;

/***
 * 一级表
 */
public interface FirstTypeBiz {
    /**
     * 查询一级表全部信息
     * @return
     */
    List<FirstType> getList();
}
