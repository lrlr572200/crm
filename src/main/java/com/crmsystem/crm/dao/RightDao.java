package com.crmsystem.crm.dao;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 13:27
 * @version:第1版
 * @description:权限表Dao接口
 **/
public interface RightDao {
    //根绝角色ID查看权限等级
    int findGradeByRolesId(Integer rolesId);

}
