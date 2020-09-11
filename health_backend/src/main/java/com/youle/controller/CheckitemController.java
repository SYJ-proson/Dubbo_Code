package com.youle.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.youle.constant.MessageConstant;
import com.youle.entity.PageResult;
import com.youle.entity.QueryPageBean;
import com.youle.entity.Result;
import com.youle.pojo.CheckItem;
import com.youle.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkitem")
public class CheckitemController {

    @Reference
    private CheckItemService checkitemService;

    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        try {
            checkitemService.add(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean pageBean) {
        PageResult result = checkitemService.findPage(pageBean);
        return result;
    }
}
