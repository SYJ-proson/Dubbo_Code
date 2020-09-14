package com.youle.service;

import com.youle.entity.PageResult;
import com.youle.entity.QueryPageBean;
import com.youle.pojo.Setmeal;

public interface SetmealService {

    public void add(Setmeal setmeal,Integer[] checkgroupIds);

    public PageResult findPage(QueryPageBean pageBean);
}
