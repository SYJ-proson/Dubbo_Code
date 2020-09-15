package com.youle.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.youle.dao.OrderSettingDao;
import com.youle.pojo.OrderSetting;
import com.youle.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> list) {
        if (list.size() > 0 && list != null) {
            for (OrderSetting orderSetting : list) {
                long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if (count > 0) {
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                } else {
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String data) {
        String startDate = data + "-1";
        String endDate = data + "-31";
        Map map = new HashMap();
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        List<OrderSetting> orderSettings = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> list = new ArrayList<>();
        for (OrderSetting orderSetting : orderSettings) {
            Map map1 = new HashMap();
            map1.put("date", orderSetting.getOrderDate().getDate());
            map1.put("number", orderSetting.getNumber());
            map1.put("reservations", orderSetting.getReservations());
            list.add(map1);
        }
        return list;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if (count > 0) {
            orderSettingDao.editNumberByOrderDate(orderSetting);
        } else {
            orderSettingDao.add(orderSetting);
        }
    }
}
