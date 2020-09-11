package com.youle.service;

import com.youle.entity.PageResult;
import com.youle.entity.QueryPageBean;
import com.youle.pojo.CheckItem;

public interface CheckItemService {

    public void add(CheckItem checkItem);

    public PageResult findPage(QueryPageBean pageBean);
}
