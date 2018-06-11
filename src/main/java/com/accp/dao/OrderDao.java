package com.accp.dao;

import com.accp.entity.Order;
import com.accp.util.Pager;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {
    /**
     * 查询预订总数
     * @return
     */
    public int findCount(@Param("status")String status);

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
    public List<Order> findAll(@Param("status")String status,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    /**
     * 以ID查询
     * @param orderId
     * @return
     */
    public Order findById(@Param("orderId") Integer orderId);

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
    public int Del(@Param("orderId")Integer orderId);
}
