package com.youle.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.youle.constant.MessageConstant;
import com.youle.dao.MemberDao;
import com.youle.dao.OrderDao;
import com.youle.dao.OrderSettingDao;
import com.youle.entity.Result;
import com.youle.pojo.Member;
import com.youle.pojo.Order;
import com.youle.service.OrderService;
import com.youle.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Autowired
    private MemberDao memberDao;

    @Override
    public Result add(Map map) throws Exception {

        Date orderDate = DateUtils.parseString2Date((String) map.get("orderDate"));
        Integer number = orderSettingDao.findNumberByDate(orderDate);
        if (number <= 0) {
            return new Result(false, "预约未设置");
        }
        Integer reservations = orderSettingDao.findReservationsByDate(orderDate);
        if (reservations >= number && reservations < 0) {
            return new Result(false, MessageConstant.ORDER_FULL);
        }
        Order order = new Order();
        order.setOrderDate(orderDate);
        order.setSetmealId(Integer.parseInt((String) map.get("setmealId")));
        order.setOrderType((String) map.get("orderType"));
        order.setOrderStatus((String) map.get("orderStatus"));
        String telephone = (String) map.get("telephone");
        Member member = memberDao.findByTelephone(telephone);
        if (member == null) {
            member = new Member();
            member.setPhoneNumber((String) map.get("telephone"));
            member.setIdCard((String) map.get("idCard"));
            member.setName((String) map.get("name"));
            member.setSex((String) map.get("sex"));
            memberDao.add(member);
        }
        order.setMemberId(member.getId());
        Integer count = orderDao.findCountByDateMemberId(order);
        if (count > 0) {
            return new Result(false, MessageConstant.HAS_ORDERED);
        }
        orderDao.add(order);
        orderSettingDao.editOrder(DateUtils.parseString2Date((String) map.get("orderDate")));
        return new Result(true,MessageConstant.ORDER_SUCCESS,order.getId());
    }

    @Override
    public Map findById(Integer id) {
        return orderDao.findById4Detail(id);
    }
}
