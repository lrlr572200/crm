package com.crmsystem.crm.service;

import com.crmsystem.crm.entity.Right;
import com.crmsystem.crm.util.RoleRight;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    //查看所有权限等级分组
    List<String> findRightMain();

    //查找权限
    Right findRightByMain(String main);

    //动态查找权限
    Right findRight(Right right);

    //添加权限
    int addRight(RoleRight right);

    //修改权限
    int updRighrt(Right right);

    //删除权限
    int delRight(Integer rightId);
}
