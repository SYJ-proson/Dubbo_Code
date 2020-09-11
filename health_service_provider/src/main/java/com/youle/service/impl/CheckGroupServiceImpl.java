package com.youle.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.youle.dao.CheckGroupDao;
import com.youle.pojo.CheckGroup;
import com.youle.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.add(checkGroup);
        setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
    }

    public void setCheckGroupAndCheckItem(Integer checkGroupId,Integer[] checkitemIds) {
        if (checkitemIds.length > 0 && checkitemIds != null) {
            for (int i = 0; i < checkitemIds.length; i++) {
                Map<String, Integer> map = new HashMap<>();
                map.put("checkgroup_id", checkGroupId);
                map.put("checkitem_id", checkitemIds[i]);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }
}
