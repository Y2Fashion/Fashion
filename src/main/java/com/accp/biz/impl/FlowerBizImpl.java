package com.accp.biz.impl;

import com.accp.biz.FlowerBiz;
import com.accp.dao.FlowerDao;
import com.accp.entity.Colour;
import com.accp.entity.Component;
import com.accp.entity.Flower;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class FlowerBizImpl implements FlowerBiz {
    @Resource
    private FlowerDao flowerDao;
    @Override
    public List<Flower> findFlower() {
        return flowerDao.findFlower();
    }

    @Override
    public List<Colour> findColour() {
        return flowerDao.findColour();
    }

    @Override
    public List<Component> findComponent() {
        return flowerDao.findComponent();
    }

    @Override
    public Colour findClId(String col) {
        return flowerDao.findClId(col);
    }

    @Override
    public Component findCmId(String com) {
        return flowerDao.findCmId(com);
    }

    @Override
    public Flower findFId(String pattern) {
        return flowerDao.findFId(pattern);
    }
}
