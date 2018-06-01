package com.accp.biz.impl;

import com.accp.biz.OrderBiz;
import com.accp.dao.OrderDao;
import com.accp.entity.Order;
import com.accp.util.Pager;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderBizImpl implements OrderBiz {
    @Resource
    private OrderDao dao;
    @Override
    public int findCount(String status) {
        return dao.findCount(status);
    }

    @Override
    public int Add(Order order) {
        return dao.Add(order);
    }

    @Override
    public Pager<Order> findAll(String status,Integer pageNo, Integer pageSize) {
        if(null==pageNo){
            pageNo=1;
        }
        int num=(dao.findCount(status)+pageSize-1)/pageSize;
        if(pageNo>num){
            pageNo=num;
        }
        Pager<Order> pager=new Pager<Order>();
        pager.setPageNo(pageNo);
        pager.setPageSize(pageSize);
        pager.setDatas(dao.findAll(status,(pageNo-1)*pageSize,pageSize));
        pager.setTotalPage(num);
        pager.setTotalRows(dao.findCount(status));
        return pager;
    }

    @Override
    public Order findById(Integer orderId) {
        return dao.findById(orderId);
    }

    @Override
    public int Update(Order order) {
        System.out.println(order.toString());
        return dao.Update(order);
    }

    @Override
    public int Del(Integer orderId) {
        return dao.Del(orderId);
    }
}
