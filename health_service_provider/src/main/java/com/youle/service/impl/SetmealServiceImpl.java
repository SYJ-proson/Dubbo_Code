package com.youle.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youle.constant.RedisConstant;
import com.youle.dao.SetmealDao;
import com.youle.entity.PageResult;
import com.youle.entity.QueryPageBean;
import com.youle.pojo.CheckGroup;
import com.youle.pojo.Setmeal;
import com.youle.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private JedisPool jedisPool;

    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);
        setSetmealAndCheckGroup(checkgroupIds,setmeal.getId());
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg());
    }

    @Override
    public PageResult findPage(QueryPageBean pageBean) {
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
        Page<Setmeal> page = setmealDao.findPage(pageBean.getQueryString());
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<CheckGroup> findCheckGroupIdsBySetmealId(Integer id) {
        return setmealDao.findCheckGroupIdsBySetmealId(id);
    }

    @Override
    public void edit(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.deleteAssociation(setmeal.getId());
        setSetmealAndCheckGroup(checkgroupIds, setmeal.getId());
        setmealDao.edit(setmeal);
    }

    @Override
    public void delete(Integer id) {
        setmealDao.deleteAssociation(id);
        setmealDao.delete(id);
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
