package com.accp.dao;

import com.accp.entity.ThirdType;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ThirdTypeDao {

    List<ThirdType> selectThirdTypeList(@RequestParam("sId") Object sId);
    ThirdType selectThirdType(@RequestParam("tId") Object tId);
    List<ThirdType> selectThirdTypeByArray(@RequestParam("typeIdList")Integer[] typeIdList);
}
