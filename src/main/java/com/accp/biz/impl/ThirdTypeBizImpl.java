package com.accp.biz.impl;

import com.accp.biz.AccessingDataBiz;
import com.accp.biz.ThirdTypeBiz;
import com.accp.dao.ThirdTypeDao;
import com.accp.entity.AccessingData;
import com.accp.entity.ThirdType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class ThirdTypeBizImpl implements ThirdTypeBiz {

    @Resource
    private ThirdTypeDao thirdTypeDao;
    @Override
    public List<ThirdType> getThirdTypeList(Object sId) {
        return thirdTypeDao.selectThirdTypeList(sId);
    }

    @Override
    public ThirdType getThirdType(Object tId) {
        return thirdTypeDao.selectThirdType(tId);
    }

    @Override
    public List<ThirdType> getThirdTypeByArray(List<AccessingData> typeList) {
        Integer[] typeIdList=new Integer[typeList.size()];
        for (int i=0;i<typeList.size();i++) {
            typeIdList[i]=typeList.get(i).getTypeID();
        }
        List<ThirdType> thirdTypeList2=thirdTypeDao.selectThirdTypeByArray(typeIdList);
        return thirdTypeList2;
    }
}
