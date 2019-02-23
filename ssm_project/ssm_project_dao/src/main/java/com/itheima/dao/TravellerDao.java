package com.itheima.dao;

import com.itheima.domain.Traveller;

import java.util.List;

public interface TravellerDao {
    //用于给Order注入属性列表travellers
    List<Traveller> findTravellersInIds(String orderid);
}
