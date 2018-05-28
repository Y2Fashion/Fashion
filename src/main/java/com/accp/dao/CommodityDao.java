package com.accp.dao;

import com.accp.entity.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityDao {
    /*
    * 分类查询
    * */
    public List<Commodity> findType(Commodity commodity);

    /**
     * 查询单个商品
     * @param id
     * @return
     */
    public Commodity findId(@Param("id") Integer id);
}
