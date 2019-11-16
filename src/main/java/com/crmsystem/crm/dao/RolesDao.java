package com.crmsystem.crm.dao;

import com.crmsystem.crm.entity.Roles;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 13:28
 * @version:第1版
 * @description:角色表Dao接口
 **/
public interface RolesDao {
    //根据角色ID查看角色名
    String findRolesNameById(@Param("rolesId") Integer rolesId);

    //条件查看角色
    List<Roles> findRole();
}
