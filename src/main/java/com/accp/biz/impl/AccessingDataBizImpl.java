package com.accp.biz.impl;

import com.accp.biz.AccessingDataBiz;
import com.accp.dao.AccessingDataDao;
import com.accp.entity.AccessingData;
import com.alibaba.fastjson.support.odps.udf.CodecCheck;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccessingDataBizImpl implements AccessingDataBiz {

    @Resource
    private AccessingDataDao accessingDataDao;

    /*
     *按IP与typeID/cId查询访问记录 也能判断是否存在
     * bool  true：按tId, false:按cId
     * */
    @Override
    public List<AccessingData> getAccessingDataListBool(String IP,boolean bool) {
        AccessingData accessingData=new AccessingData();
        accessingData.setIp(IP);
        //accessingData.setcId(Integer.parseInt(cId.toString()));
        if(bool){
            List<AccessingData> accessingData1=accessingDataDao.selectADListByTID(accessingData);
            if(accessingData1.size()>0){
                return accessingData1;
            }else{
                return null;
            }
        }else {
            List<AccessingData> accessingData1=accessingDataDao.selectADListByCID(accessingData);
            if(accessingData1.size()>0){
                return accessingData1;
            }else{
                return null;
            }
        }

    }

    /*
    * 添加访问记录
    * */
    @Override
    public boolean addAccessingData(AccessingData accessingData) {
        return accessingDataDao.insertAccessingData(accessingData)>0;
    }

    /*
    * 修改访问记录
    * */
    @Override
    public boolean modifyAccessingData(AccessingData accessingData) {
        return accessingDataDao.updataAccessingData(accessingData)>0;
    }

    /*
    * 查询所有的用户记录
    * */
    @Override
    public List<AccessingData> getAccessingDataList(String IP) {
        AccessingData accessingData= new AccessingData();
        accessingData.setIp(IP);
        List<AccessingData> accessingData1=accessingDataDao.selectAccessingDataList(accessingData);
        if(accessingData1.size()>0){
            return accessingData1;
        }else{
            return null;
        }
    }

     @Override
    public boolean getAccessingDataByC(String IP,Integer cId) {
        AccessingData accessingData=new AccessingData();
        accessingData.setIp(IP);
        accessingData.setcId(cId);
        List<AccessingData> accessingDatas=accessingDataDao.selectADBootByCID(accessingData);
        return accessingDatas.size()>0;
    }

    @Override
    public boolean getAccessingDataByT(String IP,Integer tId) {
        AccessingData accessingData=new AccessingData();
        accessingData.setIp(IP);
        accessingData.setTypeID(tId);
        List<AccessingData> accessingDatas=accessingDataDao.selectADBootByTID(accessingData);
        return accessingDatas.size()>0;
    }

}
