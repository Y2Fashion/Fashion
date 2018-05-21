package com.accp.biz;

import com.accp.entity.Commodity;

import java.util.List;

public interface CommodityBiz {
    /*
     * 分类查询
     * */
    public List<Commodity> findType(Commodity commodity);
    /**
     * 查询单个商品
     * @param id
     * @return
     */
    public Commodity findId(Integer id);
}
