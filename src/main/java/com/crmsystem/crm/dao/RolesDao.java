package com.crmsystem.crm.dao;

import com.crmsystem.crm.entity.Roles;
import com.crmsystem.crm.util.RoleRight;
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

    //查看角色及其权限
    List<RoleRight> findRoleAndRight(@Param("main") String main,
                                     @Param("pageIndex")Integer pageIndex,
                                     @Param("pageSize")Integer pageSize);

    //统计数量
    int findRoleRightCot(@Param("main") String main);

    //添加角色
    int addRole(@Param("role") RoleRight role);

    //根据角色查找角色
    Roles findRoleByName(@Param("rolesName") String rolesName);

    //修改角色
    int updRole(@Param("roles") RoleRight roles);

    //删除角色的方法
    int delRole(@Param("rolesId")Integer rolesId);

}
