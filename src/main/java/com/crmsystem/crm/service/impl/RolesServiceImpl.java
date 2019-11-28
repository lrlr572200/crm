package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.RightDao;
import com.crmsystem.crm.dao.RolesDao;
import com.crmsystem.crm.dao.SystemDao;
import com.crmsystem.crm.entity.Emp;
import com.crmsystem.crm.entity.Right;
import com.crmsystem.crm.entity.Roles;
import com.crmsystem.crm.entity.System;
import com.crmsystem.crm.service.RolesService;
import com.crmsystem.crm.util.RoleRight;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    RightDao rightDao;
    @Resource
    SystemDao systemDao;

    //根据角色ID查看角色名称
    @Override
    public String findRolesNameById(Integer rolesId) {
        return rolesDao.findRolesNameById(rolesId);
    }

    //条件查看角色
    @Override
    public List<Roles> findRole() {
        return rolesDao.findRole();
    }

    //查看角色及其权限
    @Override
    public List<RoleRight> findRoleAndRight(String main, Integer pageIndex, Integer pageSize) {
        return rolesDao.findRoleAndRight(main,pageIndex,pageSize);
    }

    //统计数量
    @Override
    public int findRoleRightCot(String main) {
        return rolesDao.findRoleRightCot(main);
    }

    //根据角色查找角色
    @Override
    public Roles findRoleByName(String rolesName) {
        return rolesDao.findRoleByName(rolesName);
    }

    //添加角色
    @Override
    @Transactional
    public int addRole(RoleRight role) {

        System system =systemDao.findTheSystem(Integer.parseInt(role.getMain()));
        int sign = rolesDao.addRole(role);
        if (sign>0){
            Roles ro = this.findRoleByName(role.getRolesName());
            RoleRight rr = new RoleRight();
            rr.setGrade(Integer.parseInt(system.getValue()));
            rr.setMain(system.getName());
            rr.setRolesId(ro.getRolesId());
            sign=rightDao.addRight(rr);
        }
        return sign;
    }

    //修改角色
    @Override
    @Transactional
    public int updRole(RoleRight role) {
        int sign=0;
        if (role.getRolesId()!=null && role.getRolesName()!=null
                && !"".equals(role.getRolesId()) && !"".equals(role.getRolesName())){
            sign=rolesDao.updRole(role);
            if (sign>0){
                Right r = new Right();
                r.setRolesId(role.getRolesId());
                Right daoRight = rightDao.findRight(r);
                System system =systemDao.findTheSystem(Integer.parseInt(role.getMain()));
                Right updRg = new Right();
                updRg.setRightId(daoRight.getRightId());
                updRg.setRolesId(role.getRolesId());
                updRg.setGrade(Integer.parseInt(system.getValue()));
                updRg.setMain(system.getName());
                sign=rightDao.updRighrt(updRg);
            }

        }
        return sign;
    }

    //删除角色的方法
    @Override
    @Transactional
    public int delRole(Integer rolesId) {
        Right findRight = new Right();
        findRight.setRolesId(rolesId);
        Right right = rightDao.findRight(findRight);
        int sign = rolesDao.delRole(rolesId);
        if (sign>0){
            sign=rightDao.delRight(right.getRightId());
        }
        return sign;
    }

    //数量统计计数SQL
    @Override
    public int findRoleCountByName(String rolesName) {
        return rolesDao.findRoleCountByName(rolesName);
    }
}
