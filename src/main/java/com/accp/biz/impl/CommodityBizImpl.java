package com.accp.biz.impl;

import com.accp.biz.*;
import com.accp.dao.CommodityDao;
import com.accp.dao.ThirdTypeDao;
import com.accp.entity.Commodity;
import com.accp.entity.SecondType;
import com.accp.entity.ThirdType;
import com.accp.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityBizImpl implements CommodityBiz {
    @Resource
    private CommodityDao commodityDao;

    @Resource
    private ThirdTypeBiz thirdTypeBiz;

    @Resource
    private SecondTypeBiz secondTypeBiz;

    @Resource
    private LiNingBiz liNingBiz;

    @Resource
    private PictureBiz pictureBiz;

    @Override
    public List<Commodity> findType(Commodity commodity) {
        return commodityDao.findType(commodity);
    }

    @Override
    public Commodity findId(Integer id) {
        addHits(id);
        Commodity commodity=commodityDao.findId(id);
        commodity.setLining(liNingBiz.getLiNingById(commodity.getlId()));
        commodity.setPictures(pictureBiz.getPictureList(id));
        return commodity;
    }

    @Override
    public void addHits(Object comId) {
        Commodity commodity=commodityDao.findId((Integer)comId);
        commodity.setHits(commodity.getHits()+1);
        commodityDao.UpdateHits(commodity);
    }

    @Override
    public List<Commodity> getCommoditys(Object secondTypeId) {
        List<ThirdType> thirdTypes=thirdTypeBiz.getThirdTypeList(secondTypeId);
        Integer[] thirdTypeArry=new Integer[thirdTypes.size()];
        int i=0;
        if(thirdTypes!=null){
            for (ThirdType third:thirdTypes) {
                thirdTypeArry[i]=third.gettId();
                i++;
            }
        }
        return commodityDao.selectCommodityList(thirdTypeArry);
    }

    @Override
    public List<Commodity> getCommoditys(int dandu) {
        Integer[] integers=new Integer[]{dandu};
        return commodityDao.selectCommodityList(integers);
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
        return commodityDao.selectCommodityList(integers);
    }

    @Override
    public Pager<Commodity> commodityList(Integer type, Integer pageNo) {
        if(null == pageNo){
            pageNo=1;
        }
        Pager<Commodity> pager = new Pager<>();
        pager.setPageNo(pageNo);
        pager.setTotalRows(commodityDao.commodityCount(type));
        pager.setTotalPage((pager.getTotalRows()+8-1)/8);
        pager.setDatas(commodityDao.commodityList(type,pageNo-1));
        return pager;
    }
}
