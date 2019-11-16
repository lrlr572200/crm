package com.crmsystem.crm.service;

import com.crmsystem.crm.entity.Roles;
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
}
