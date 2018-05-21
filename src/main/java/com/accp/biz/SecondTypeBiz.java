package com.accp.biz;

import com.accp.entity.SecondType;

import java.util.List;

/**
 * 二级表操作
 */
public interface SecondTypeBiz {
    /**
     * 查询二级表全部信息
     */
    List<SecondType> getList();
}
