package com.youle.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youle.dao.CheckItemDao;
import com.youle.entity.PageResult;
import com.youle.entity.QueryPageBean;
import com.youle.pojo.CheckItem;
import com.youle.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult findPage(QueryPageBean pageBean) {
        PageHelper.startPage(pageBean.getCurrentPage(),pageBean.getPageSize());
        Page<CheckItem> page = checkItemDao.findPageList(pageBean.getQueryString());
        PageResult pageResult = new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }
}
