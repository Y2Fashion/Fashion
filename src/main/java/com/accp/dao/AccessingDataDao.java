package com.accp.dao;

import com.accp.entity.AccessingData;

import java.util.List;

public interface AccessingDataDao {
    List<AccessingData> selectADListByCID(AccessingData accessingData);
    List<AccessingData> selectADListByTID(AccessingData accessingData);
    List<AccessingData> selectAccessingDataList(AccessingData accessingData);
    List<AccessingData> selectADBootByCID(AccessingData accessingData);
    List<AccessingData> selectADBootByTID(AccessingData accessingData);
    int insertAccessingData(AccessingData accessingData);
    int updataAccessingData(AccessingData accessingData);
    //List<AccessingData> selectADByTimeOrCount(AccessingData accessingData);
}
