package com.accp.biz;

import com.accp.entity.ThirdType;

import java.util.List;

public interface ThirdTypeBiz {
    /*
    * 获取三级类型
    * */
    public List<ThirdType> getThirdTypeList(Object sId);
    public ThirdType getThirdType(Object tId);
}
