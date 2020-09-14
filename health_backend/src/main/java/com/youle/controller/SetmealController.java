package com.youle.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.youle.constant.MessageConstant;
import com.youle.entity.PageResult;
import com.youle.entity.QueryPageBean;
import com.youle.entity.Result;
import com.youle.pojo.Setmeal;
import com.youle.service.SetmealService;
import com.youle.utils.QiniuUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {
        String filename = imgFile.getOriginalFilename();
        int indexOf = filename.lastIndexOf(".");
        String substring = filename.substring(indexOf);
        String name = UUID.randomUUID().toString() + substring;
        try {
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),name);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, name);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal,Integer[] checkgroupIds) {
        try {
            setmealService.add(setmeal, checkgroupIds);
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean pageBean) {
        return setmealService.findPage(pageBean);
    }
}
