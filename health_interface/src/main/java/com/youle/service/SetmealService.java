package com.youle.service;

import com.youle.entity.PageResult;
import com.youle.entity.QueryPageBean;
import com.youle.pojo.CheckGroup;
import com.youle.pojo.Setmeal;

import java.util.List;

public interface SetmealService {

    public void add(Setmeal setmeal,Integer[] checkgroupIds);

    public PageResult findPage(QueryPageBean pageBean);

    public List<CheckGroup> findCheckGroupIdsBySetmealId(Integer id);

    public void edit(Setmeal setmeal, Integer[] checkgroupIds);

    public void delete(Integer id);

    public Setmeal findById(Integer id);
}
