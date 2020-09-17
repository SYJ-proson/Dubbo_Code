package com.youle.dao;

import com.youle.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface OrderSettingDao {

    public void add(OrderSetting orderSetting);

    public void editNumberByOrderDate(OrderSetting orderSetting);

    public long findCountByOrderDate(Date orderDate);

    public List<OrderSetting> getOrderSettingByMonth(Map map);

    public void editOrder(Date orderDate);

    public Integer findNumberByDate(Date date);

    public Integer findReservationsByDate(Date date);
}
