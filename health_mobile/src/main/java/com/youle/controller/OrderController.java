package com.youle.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.youle.constant.MessageConstant;
import com.youle.constant.RedisMessageConstant;
import com.youle.entity.Result;
import com.youle.pojo.Order;
import com.youle.service.OrderService;
import com.youle.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    private OrderService orderService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/add")
    public Result add(@RequestBody Map map) {
        String validateCode = jedisPool.getResource().get((String) map.get("telephone")+ RedisMessageConstant.SENDTYPE_ORDER);
        if (validateCode.equals(map.get("validateCode"))) {
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            map.put("orderStatus", Order.ORDERSTATUS_NO);
            try {
                Result result = orderService.add(map);
                if (result.isFlag()) {
                    SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE,(String) map.get("telephone"),"");
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, MessageConstant.ORDERSETTING_FAIL);
            }
        } else {
            return new Result(false,MessageConstant.VALIDATECODE_ERROR);
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Map map = orderService.findById(id);
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
