package com.youle.service;

import com.youle.entity.PageResult;
import com.youle.entity.QueryPageBean;
import com.youle.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {

    public void add(CheckGroup checkGroup, Integer[] checkitemIds);

    public PageResult findPage(QueryPageBean pageBean);

    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    public CheckGroup findById(Integer id);

    public void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    public void delete(Integer id);

    public List<CheckGroup> findAll();
}
