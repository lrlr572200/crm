package com.crmsystem.crm.service;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 14:54
 * @version:第1版
 * @description:权限表Service接口
 **/
public interface RightService {
    //根绝角色ID查看权限等级
    int findGradeByRolesId(Integer rolesId);
}
