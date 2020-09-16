package com.youle.dao;

import com.github.pagehelper.Page;
import com.youle.entity.PageResult;
import com.youle.entity.QueryPageBean;
import com.youle.pojo.CheckGroup;
import com.youle.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealDao {

    public void add(Setmeal setmeal);

    public void setSetmealAndCheckGroup(Map<String,Integer> map);

    public Page<Setmeal> findPage(String queryString);

    public List<CheckGroup> findCheckGroupIdsBySetmealId(Integer id);

    public void edit(Setmeal setmeal);

    public void deleteAssociation(Integer id);

    public void delete(Integer id);

    public Setmeal findById(Integer id);

    public List<Setmeal> findAll();
}
