package com.accp.dao;

import com.accp.entity.ThirdType;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ThirdTypeDao {

    List<ThirdType> selectThirdType(@RequestParam("id") Object id);
}
