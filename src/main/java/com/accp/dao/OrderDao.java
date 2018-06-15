package com.accp.dao;

import com.accp.entity.Order;
import com.accp.util.Pager;
/*import com.sun.tools.corba.se.idl.constExpr.Or;*/
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {
    //获取销售量
    int selectOrderCount();

    /**
     * 查询预订总数
     * id 时间段(1:以后 ,2:今天  ,3:以前)
     * @return
     */
    public int findCount(@Param("status")String status,@Param("id") int id);

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

    /***
     * 根据时间段查询所有订单
     * @param id 时间段(1:以后 ,2:今天  ,3:以前)
     * @param num 起始位置
     * @param sum 每页条数
     * @return
     */
    List<Order> findTime(@Param("id") int id,@Param("num") int num,@Param("sum") int sum);
}
