package com.accp.biz;

import com.accp.entity.Colour;
import com.accp.entity.Component;
import com.accp.entity.Flower;

import java.util.List;

public interface FlowerBiz {
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
    public Flower findFId(String pattern);
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
    public Component findCmId(String com);
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
    public Colour findClId(String col);
}
