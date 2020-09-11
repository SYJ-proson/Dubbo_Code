package com.youle.service;

import com.youle.entity.PageResult;
import com.youle.entity.QueryPageBean;
import com.youle.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {

    public void add(CheckItem checkItem);

    public PageResult findPage(QueryPageBean pageBean);

    public void delete(Integer id);

    public CheckItem findById(Integer id);

    public void update(CheckItem checkItem);

    public List<CheckItem> findAll();
}
