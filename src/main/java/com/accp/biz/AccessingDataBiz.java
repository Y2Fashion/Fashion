package com.accp.biz;

import com.accp.entity.AccessingData;

import java.util.List;

public interface AccessingDataBiz {
    List<AccessingData> getAccessingDataListBool(String IP,boolean bool);
    List<AccessingData> getAccessingDataList(String IP);
    boolean getAccessingDataByT(String IP,Integer tId);
    boolean getAccessingDataByC(String IP,Integer cId);
    boolean addAccessingData(AccessingData accessingData);
    boolean modifyAccessingData(AccessingData accessingData);
}
