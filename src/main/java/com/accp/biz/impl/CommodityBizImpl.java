package com.accp.biz.impl;

import com.accp.biz.*;
import com.accp.dao.CommodityDao;
import com.accp.dao.ThirdTypeDao;
import com.accp.entity.AccessingData;
import com.accp.entity.Commodity;
import com.accp.entity.SecondType;
import com.accp.entity.ThirdType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
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

    @Resource
    private PictureBiz pictureBiz;

    @Override
    public List<Commodity> findType(Commodity commodity) {
        return dao.findType(commodity);
    }


    //查询详细信息
    @Override
    public Commodity findId(Integer id) {
        addHits(id);
        Commodity commodity=dao.findId(id);
        commodity.setLining(liNingBiz.getLiNingById(commodity.getlId()));
        commodity.setPictures(pictureBiz.getPictureList(id));
        return commodity;
    }

    /*
    * 添加热度
    * */
    @Override
    public void addHits(Object comId) {
        Commodity commodity=dao.findId((Integer)comId);
        commodity.setHits(commodity.getHits()+1);
        dao.UpdateHits(commodity);
    }

    /*
    * 按二级分类查询
    * */
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

    /*
    * 按单个id查询
    * */
    @Override
    public List<Commodity> getCommoditys(int dandu) {
        Integer[] integers=new Integer[]{dandu};
        return dao.selectCommodityList(integers);
    }

    /*
    * 按1级分类查询
    * */
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

    /*
    * 根据用户访问记录查询
    * */
    @Override
    public List<Commodity> getCommodityListByArray(List<AccessingData> typeIDList) {
        System.out.println(typeIDList);
        Integer[] typeIdList=new Integer[typeIDList.size()];
        for (int i=0;i<typeIDList.size();i++) {
            typeIdList[i]=typeIDList.get(i).getcId();
        }
        List<Commodity> commodityList2=dao.selectCommodityListByIP(typeIdList);
        return commodityList2;
    }

    /*
    * 按多个用户兴趣id查询
    * */
    @Override
    public List<Commodity> getCommListByXQArray(List<AccessingData> accessingData) {
        Integer[] cIdList=new Integer[accessingData.size()];

        for (int i=0;i<accessingData.size();i++) {
            cIdList[i]=accessingData.get(i).getcId();
        }
        if(accessingData.size()>1){
            for(int a=0;a<accessingData.size()-1;a++){
                for(int b=a;b<accessingData.size()-a-1;a++){
                    if(accessingData.get(b).getLookCount()<accessingData.get(b+1).getLookCount()){
                        int num=cIdList[b];
                        cIdList[b]=cIdList[b+1];
                        cIdList[b+1]=num;
                    }
                }
            }
        }
        return dao.selectCommodityListByIP(cIdList);
    }
}
