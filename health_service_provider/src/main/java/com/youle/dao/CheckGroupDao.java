package com.youle.dao;

import com.github.pagehelper.Page;
import com.youle.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {

    public void add(CheckGroup checkGroup);

    public void setCheckGroupAndCheckItem(Map<String, Integer> map);

    public Page<CheckGroup> selectByCondition(String queryString);

    public CheckGroup findById(Integer id);

    public void edit(CheckGroup checkGroup);

    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    public void deleteAssociation(Integer id);

    public void delete(Integer id);
}
