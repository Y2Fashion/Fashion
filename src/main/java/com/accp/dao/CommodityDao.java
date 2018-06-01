package com.accp.dao;

import com.accp.entity.Commodity;
import com.accp.entity.ThirdType;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityDao {
    /*
     * 分类查询
     * */
    public List<Commodity> findType(Commodity commodity);

    /**
     * 查询单个商品
     *
     * @param id
     * @return
     */
    public Commodity findId(@Param("id") Integer id);

    /*
     * 添加点击量
     * */
    public void UpdateHits(Commodity commodity);

    /*
     * 按热度与二级类型查询
     * */
    public List<Commodity> selectCommodityList(Integer[] thirdTypeArry);

}