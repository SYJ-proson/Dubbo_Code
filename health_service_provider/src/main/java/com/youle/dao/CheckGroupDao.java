package com.youle.dao;

import com.youle.pojo.CheckGroup;

import java.util.Map;

public interface CheckGroupDao {

    public void add(CheckGroup checkGroup);

    public void setCheckGroupAndCheckItem(Map<String, Integer> map);
}
