package com.accp.dao;

import com.accp.entity.Order;
import com.accp.entity.UserOrder;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserOrderDao {

    List<UserOrder> selectCommdityTop10();

    List<UserOrder> selectThreeTypes();

    List<UserOrder> selectSaleByMonth(@RequestParam("tyreeTypeId") Integer threeTypeId);

    List<UserOrder> selectSaleByAddress();
}
