package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.OrderDao;
import com.itheima.domain.Order;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    //查询所有订单
    public List<Order> findAll(Integer pageNum,Integer pageSize,String searchValue) {
        PageHelper.startPage(pageNum,pageSize);
        return orderDao.findAll(searchValue);
    }

    //订单详情
    public Order findOrderById(String id) {
        return orderDao.findOrderById(id);
    }
}
