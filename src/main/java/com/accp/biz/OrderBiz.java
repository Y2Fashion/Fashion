package com.accp.biz;

import com.accp.entity.Order;
import com.accp.util.Pager;

import java.util.List;

public interface OrderBiz {
    /**
     * 查询预订总数
     * @return
     */
    public int findCount(String status);

    /**
     * 预约
     * @param order
     * @return
     */
    public int Add(Order order);
    /**
     * 查询
     * @return
     */
    public Pager<Order> findAll(String status,Integer pageNo,Integer pageSize);
    /**
     * 以ID查询
     * @param orderId
     * @return
     */
    public Order findById(Integer orderId);
    /**
     * 修改
     * @param order
     * @return
     */
    public int Update(Order order);
    /**
     * 删除
     * @param orderId
     * @return
     */
    public int Del(Integer orderId);
}
