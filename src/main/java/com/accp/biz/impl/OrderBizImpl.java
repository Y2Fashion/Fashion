package com.accp.biz.impl;

import com.accp.biz.OrderBiz;
import com.accp.dao.OrderDao;
import com.accp.entity.Order;
import com.accp.util.Pager;
/*import com.sun.tools.corba.se.idl.constExpr.Or;*/
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



    /***
     * 查询符合条件的订单总数量
     * @param status
     * @return
     */
    @Override
    public int findCount(String status) {
        return orderDao.findCount(status,0);
    }

    @Override
    public int Add(Order order) {
        return orderDao.Add(order);
    }

    @Override
    public Pager<Order> findAll(String status,Integer pageNo, Integer pageSize) {
        if(null==pageNo){
            pageNo=1;
        }
        int num=(orderDao.findCount(status,0)+pageSize-1)/pageSize;
        if(pageNo>num){
            pageNo=num;
        }
        Pager<Order> pager=new Pager<Order>();
        pager.setPageNo(pageNo);
        pager.setPageSize(pageSize);
        pager.setDatas(orderDao.findAll(status,(pageNo-1)*pageSize,pageSize));
        pager.setTotalPage(num);
        pager.setTotalRows(orderDao.findCount(status,0));
        return pager;
    }

    @Override
    public Order findById(Integer orderIyrd) {
        return orderDao.findById(orderIyrd);
    }

    @Override
    public int Update(Order order) {
        System.out.println(order.toString());
        return orderDao.Update(order);
    }

    @Override
    public int Del(Integer orderId) {
        return orderDao.Del(orderId);
    }


    /***
     * 根据时间段查询所有订单
     * @param id 时间段(1:以后 ,2:今天  ,3:以前)
     * @param num 起始位置
     * @param sum 每页条数
     * @return
     */
    public Pager<Order> findTime(int id,int num,int sum){
//      datetime-local
        Pager<Order> pager=new Pager<Order>();
        pager.setPageNo(num);//当前页数
        pager.setPageSize(sum);//每页显示的条数
        pager.setTotalRows(orderDao.findCount("",id));//总条数
        pager.setTotalPage((pager.getTotalRows()+sum-1)/sum);//总页数
        pager.setDatas(orderDao.findTime(id,(num-1)*sum,sum));//存放集合
        return pager;
    }
}
