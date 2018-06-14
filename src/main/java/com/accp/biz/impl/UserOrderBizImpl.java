package com.accp.biz.impl;

import com.accp.biz.UserOrderBiz;
import com.accp.dao.UserOrderDao;
import com.accp.entity.Order;
import com.accp.entity.UserOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserOrderBizImpl implements UserOrderBiz {

    @Resource
    private UserOrderDao userOrderDao;
    /*
    * 查询销量前十商品
    * */
    @Override
    public List<UserOrder> getCommdityTop10() {
        return userOrderDao.selectCommdityTop10();
    }

    /*
    * 查询所有已有销售记录的类型
    * */
    @Override
    public List<UserOrder> getThreeTypes() {
        return userOrderDao.selectThreeTypes();
    }

    /*
    * 按类型查询各个月的销量
    * */
    @Override
    public List<UserOrder> getSaleByMonth(Integer threeTypeId) {
        return userOrderDao.selectSaleByMonth(threeTypeId);
    }

    /*
    * 查询各个省份的服装需求
    * */

    @Override
    public List<UserOrder> getSaleByAddress() {
        return userOrderDao.selectSaleByAddress();
    }
}
