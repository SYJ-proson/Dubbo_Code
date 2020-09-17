package com.youle.dao;

import com.youle.pojo.Order;

import java.util.Date;
import java.util.Map;

public interface OrderDao {

    public void add(Order order);

    public Order findById(Integer id);

    public Integer findCountByDateMemberId(Order order);

    public Map findById4Detail(Integer id);
}
