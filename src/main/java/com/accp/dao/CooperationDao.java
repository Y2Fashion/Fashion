package com.accp.dao;

import com.accp.entity.Cooperation;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface CooperationDao {
    /**
     * 增加
     * @param cooperation
     * @return
     */
    int insertCooperation(Cooperation cooperation);

}
