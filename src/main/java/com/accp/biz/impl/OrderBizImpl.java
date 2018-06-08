package com.accp.biz.impl;

import com.accp.biz.OrderBiz;
import com.accp.dao.OrderDao;
import com.accp.entity.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderBizImpl implements OrderBiz {

    @Resource
    private OrderDao orderDao;

    @Override
    public int getOrderCount() {
        return orderDao.selectOrderCount();
    }

}
