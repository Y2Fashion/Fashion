package com.accp.biz.impl;

import com.accp.biz.CommodityBiz;
import com.accp.biz.LiNingBiz;
import com.accp.biz.SecondTypeBiz;
import com.accp.biz.ThirdTypeBiz;
import com.accp.dao.CommodityDao;
import com.accp.dao.ThirdTypeDao;
import com.accp.entity.Commodity;
import com.accp.entity.SecondType;
import com.accp.entity.ThirdType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityBizImpl implements CommodityBiz {
    @Resource
    private CommodityDao dao;

    @Resource
    private ThirdTypeBiz thirdTypeBiz;

    @Resource
    private SecondTypeBiz secondTypeBiz;

    @Resource
    private LiNingBiz liNingBiz;

    @Override
    public List<Commodity> findType(Commodity commodity) {
        return dao.findType(commodity);
    }

    @Override
    public Commodity findId(Integer id) {
        addHits(id);
        Commodity commodity=dao.findId(id);
        commodity.setLining(liNingBiz.getLiNingById(commodity.getlId()));
        return commodity;
    }

    @Override
    public void addHits(Object comId) {
        Commodity commodity=dao.findId((Integer)comId);
        commodity.setHits(commodity.getHits()+1);
        dao.UpdateHits(commodity);
    }

    @Override
    public List<Commodity> getCommoditys(String secondTypeId) {
        List<ThirdType> thirdTypes=thirdTypeBiz.getThirdTypeList(secondTypeId);
        Integer[] thirdTypeArry=new Integer[thirdTypes.size()];
        int i=0;
        if(thirdTypes!=null){
            for (ThirdType third:thirdTypes) {
                thirdTypeArry[i]=third.gettId();
                i++;
            }
        }
        return dao.selectCommodityList(thirdTypeArry);
    }

    @Override
    public List<Commodity> getCommoditys(int dandu) {
        Integer[] integers=new Integer[]{dandu};
        return dao.selectCommodityList(integers);
    }

    @Override
    public List<Commodity> getCommoditys(boolean bool) {
        List<ThirdType> thirdTypes=null;
        List<ThirdType> thirdTypeList=new ArrayList<ThirdType>();
        int id=1;
        if(bool){
            id=2;
        }
        List<SecondType> secondTypes=secondTypeBiz.getListById(id);
        for (SecondType secondType:secondTypes) {
            thirdTypes=thirdTypeBiz.getThirdTypeList(secondType.getsId());
            for (ThirdType tt:thirdTypes) {
                thirdTypeList.add(tt);
            }
        }
        Integer[] integers=new Integer[thirdTypeList.size()];
        if(thirdTypeList!=null){
            int a=0;
            for (ThirdType third:thirdTypeList) {
                integers[a]=third.gettId();
                a++;
            }
        }
        return dao.selectCommodityList(integers);
    }
}
