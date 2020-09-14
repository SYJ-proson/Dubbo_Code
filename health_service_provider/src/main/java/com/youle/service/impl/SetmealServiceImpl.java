package com.youle.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youle.dao.SetmealDao;
import com.youle.entity.PageResult;
import com.youle.entity.QueryPageBean;
import com.youle.pojo.Setmeal;
import com.youle.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);
        setSetmealAndCheckGroup(checkgroupIds,setmeal.getId());
    }

    @Override
    public PageResult findPage(QueryPageBean pageBean) {
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
        Page<Setmeal> page = setmealDao.findPage(pageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    public void setSetmealAndCheckGroup(Integer[] checkgroupIds, Integer setmealId) {
        if (checkgroupIds.length > 0 && checkgroupIds != null) {
            for (int i = 0; i < checkgroupIds.length; i++) {
                Map<String, Integer> map = new HashMap<>();
                map.put("setmeal_id", setmealId);
                map.put("checkgroup_id", checkgroupIds[i]);
                setmealDao.setSetmealAndCheckGroup(map);
            }
        }
    }
}
