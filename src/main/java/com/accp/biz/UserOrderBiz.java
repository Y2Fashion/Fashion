package com.accp.biz;

import com.accp.entity.Order;
import com.accp.entity.UserOrder;

import java.util.List;

public interface UserOrderBiz {
    List<UserOrder> getCommdityTop10();
    List<UserOrder> getThreeTypes();
    List<UserOrder> getSaleByMonth(Integer threeTypeId);
    List<UserOrder> getSaleByAddress();
}
