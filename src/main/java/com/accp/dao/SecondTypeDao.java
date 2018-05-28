package com.accp.dao;

import com.accp.entity.SecondType;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 二级表
 */
public interface SecondTypeDao {
    /***
     * 查询二级表全部信息
     * @return
     */
    List<SecondType> selectListById(@RequestParam("id") Object id);
    List<SecondType> selectList();
}
