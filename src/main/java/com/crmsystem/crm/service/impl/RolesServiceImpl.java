package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.RolesDao;
import com.crmsystem.crm.entity.Roles;
import com.crmsystem.crm.service.RolesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 14:57
 * @version:第1版
 * @description:角色表Service接口实现类
 **/
@Service
public class RolesServiceImpl implements RolesService {
    @Resource
    RolesDao rolesDao;
    //根绝角色ID查看角色名称
    @Override
    public String findRolesNameById(Integer rolesId) {
        return rolesDao.findRolesNameById(rolesId);
    }

    //条件查看角色
    @Override
    public List<Roles> findRole() {
        return rolesDao.findRole();
    }
}
