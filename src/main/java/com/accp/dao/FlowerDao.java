package com.accp.dao;

import com.accp.entity.Colour;
import com.accp.entity.Component;
import com.accp.entity.Flower;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlowerDao {
    /**
     * 查询花型
     * @return
     */
    public List<Flower> findFlower();
    /**
      * 按条件查ID
     * @param pattern
     * @return
             */
    public Flower findFId(@Param("pattern")String pattern);
    /**
     * 查询成分
     * @return
     */
    public List<Component> findComponent();
    /**
     * 按条件查ID
     * @param com
     * @return
     */
    public Component findCmId(@Param("com")String com);
    /**
     * 查询颜色
     * @return
     */
    public List<Colour> findColour();
    /**
     * 按条件查ID
     * @param col
     * @return
     */
    public Colour findClId(@Param("col")String col);

}
