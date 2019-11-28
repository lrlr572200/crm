package com.crmsystem.crm.service;

import com.crmsystem.crm.entity.Roles;
import com.crmsystem.crm.util.RoleRight;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 14:56
 * @version:第1版
 * @description:角色表Service接口
 **/
public interface RolesService {
    //根据角色ID查看角色名
    String findRolesNameById(Integer rolesId);

    //条件查看角色
    List<Roles> findRole();

    //查看角色及其权限
    List<RoleRight> findRoleAndRight(String main,Integer pageIndex,Integer pageSize);

    //统计数量
    int findRoleRightCot(String main);

    //添加角色
    int addRole(RoleRight role);

    //根据角色查找角色
    Roles findRoleByName(String rolesName);

    //修改角色
    int updRole(RoleRight roles);

    //删除角色的方法
    int delRole(Integer rolesId);

    //数量统计计数SQL
    int findRoleCountByName(String rolesName);
}
