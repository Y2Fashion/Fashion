package com.accp.biz.impl;

import com.accp.biz.AccessingDataBiz;
import com.accp.biz.ThirdTypeBiz;
import com.accp.dao.ThirdTypeDao;
import com.accp.entity.AccessingData;
import com.accp.entity.Commodity;
import com.accp.entity.ThirdType;
import com.accp.entity.UserOrder;
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

    @Override
    public List<ThirdType> getThirdTypeByCArray(List<Commodity> commodities) {
        Integer[] typeIdList=new Integer[commodities.size()];
        for (int i=0;i<commodities.size();i++) {
            typeIdList[i]=commodities.get(i).getType();
        }
        List<ThirdType> thirdTypeList2=thirdTypeDao.selectThirdTypeByArray(typeIdList);
        return thirdTypeList2;
    }
    @Override
    public List<ThirdType> getThirdName(List<UserOrder> userOrders) {
        Integer[] thirdArray=new Integer[userOrders.size()];
        int i=0;
        for (UserOrder userOrder:userOrders) {
            thirdArray[i]=userOrder.getThreeTypeId();
            i++;
        }
        return thirdTypeDao.selectThirdTypeByArray(thirdArray);
    }
}
