package com.youle.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.youle.constant.MessageConstant;
import com.youle.entity.Result;
import com.youle.pojo.OrderSetting;
import com.youle.service.OrderSettingService;
import com.youle.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile) {
        try {
            List<String[]> list = POIUtils.readExcel(excelFile);
            List<OrderSetting> orderSettings = new ArrayList<>();
            for (String[] strings : list) {
                OrderSetting orderSetting = new OrderSetting();
                orderSetting.setOrderDate(new Date(strings[0]));
                orderSetting.setNumber(Integer.parseInt(strings[1]));
                orderSettings.add(orderSetting);
            }
            orderSettingService.add(orderSettings);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(@RequestParam("data") String data) {
        System.out.println(data);
        try {
            List<Map> list = orderSettingService.getOrderSettingByMonth(data);
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting) {
        try {
            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
    }
}
