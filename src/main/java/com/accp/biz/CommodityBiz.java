package com.accp.biz;

import com.accp.entity.AccessingData;
import com.accp.entity.Commodity;
import com.accp.entity.ThirdType;
import com.accp.entity.UserOrder;
import com.accp.util.Pager;

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

    /**
     * 统计点击量
     */
    public void addHits(Object comId);

    /*
    * 按热度和二级类型查询
    * */
    public List<Commodity> getCommoditys(Object secondTypeId);

    /*
    * 按热度和三级类型查询
    * */
    public List<Commodity> getCommoditys(int dandu);

    /*
    * 按热度和1级分类查询
    * */
    public List<Commodity> getCommoditys(boolean bool);

    /*
    * 按多个三级ID查询
    * */
    List<Commodity> getCommodityListByArray(List<AccessingData> intList);

    /*
    * 按多个id查询
    * */
    List<Commodity> getCommListByXQArray(List<AccessingData> accessingData);

    /*
    * 分页查询
    * */
    public Pager<Commodity> commodityList(Integer type,Integer pageNo);

    /*
    * 按id查询商品
    * */
    public Commodity getCommodityById(Integer id);

    /*
    *查询各个类型所有商品的热度
    * */
    public List<Commodity> getHitsGroupType();

}
