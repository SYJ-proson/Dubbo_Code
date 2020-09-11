package com.youle.dao;

import com.github.pagehelper.Page;
import com.youle.entity.QueryPageBean;
import com.youle.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {

    public void add(CheckItem checkItem);

    public Page<CheckItem> findPageList(String queryString);

    public void delete(Integer id);

    public Integer findCountByCheckItemId(Integer id);

    public CheckItem findById(Integer id);

    public void update(CheckItem checkItem);
}
