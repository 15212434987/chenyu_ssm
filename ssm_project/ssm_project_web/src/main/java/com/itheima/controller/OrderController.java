package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Order;
import com.itheima.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    //查询所有订单,带条件,分页
    @RequestMapping("/findAll")
    public String findAll(@RequestParam(name = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                          @RequestParam(name = "pageSize",required = false,defaultValue = "5")Integer pageSize,
                          @RequestParam(name = "searchValue",required = false,defaultValue = "")String searchValue,Model model){
        List<Order> list = orderService.findAll(pageNum,pageSize,searchValue);
        PageInfo<Order> pageInfo = new PageInfo<Order>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("sv",searchValue);
        return "orders-list";
    }

    //订单详情
    @RequestMapping("/findOrderById")
    public String findOrderById(String id,Model model){
        Order order = orderService.findOrderById(id);
        model.addAttribute("orders",order);
        return "orders-show";
    }
}
