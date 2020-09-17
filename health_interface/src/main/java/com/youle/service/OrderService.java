package com.youle.service;

import com.youle.entity.Result;
import com.youle.pojo.Order;

import java.util.Map;

public interface OrderService {

    public Result add(Map map) throws Exception;

    public Map findById(Integer id);
}
