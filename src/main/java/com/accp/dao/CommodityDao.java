package com.accp.dao;

import com.accp.entity.Commodity;
import com.sun.org.glassfish.external.probe.provider.annotations.ProbeParam;

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
    public Commodity findId(@ProbeParam("id") Integer id);
}
