package com.accp.biz.impl;

import com.accp.biz.FirstTypeBiz;
import com.accp.dao.FirstTypeDao;
import com.accp.entity.FirstType;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


/***
 * 一级表
 */
@Service("firstTypeBizImpl")
public class FirstTypeBizImpl implements FirstTypeBiz {
    @Resource
    private FirstTypeDao firstDao;

    /**
     * 查询一级表全部信息
     * @return
     */
    @Override
    public List<FirstType> getList() {
        return firstDao.selectList();
    }

}
