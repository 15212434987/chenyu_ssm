package com.itheima.dao;

import com.itheima.domain.Order;

import java.util.List;

public interface OrderDao {

    //查询所有订单
    List<Order> findAll(String searchValue);

    //查询订单详情
    Order findOrderById(String id);
}
