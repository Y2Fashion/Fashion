package com.accp.biz.impl;

import com.accp.biz.ThirdTypeBiz;
import com.accp.dao.ThirdTypeDao;
import com.accp.entity.ThirdType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ThirdTypeBizImpl implements ThirdTypeBiz {

    @Resource
    private ThirdTypeDao thirdTypeDao;
    @Override
    public List<ThirdType> getThirdTypeList(Object sId) {
        return thirdTypeDao.selectThirdType(sId);
    }
}
