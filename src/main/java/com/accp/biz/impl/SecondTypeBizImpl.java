package com.accp.biz.impl;

import com.accp.biz.SecondTypeBiz;
import com.accp.dao.SecondTypeDao;
import com.accp.entity.SecondType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 二级表操作
 */
@Service("secondTypeBizImpl")
public class SecondTypeBizImpl implements SecondTypeBiz {
    @Resource
    private SecondTypeDao secondDao;

    /**
     * 查询二级表操作全部
     * @return
     */
    @Override
    public List<SecondType> getList() {
        return secondDao.selectList();
    }
}
