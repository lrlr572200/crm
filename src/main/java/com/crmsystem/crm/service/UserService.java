package com.crmsystem.crm.service;

import com.crmsystem.crm.entity.User;
import com.crmsystem.crm.util.UserCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 15:02
 * @version:第1版
 * @description:客户表Service接口
 **/
public interface UserService {
    //根据客户ID修改客户类型
    int updateUserUserTypeById(User user);
    //根据客户ID修改信誉等级
    int updateUserCreditGradeById( User user);
    //根据ID修改客户信息
    int updateUserStatesById(User user);
    //根据客户ID查询客户信息
    User findUserById( Integer userId);

    //显示销售代表所维护的客户时分页检索客户
    List<User> findUserPaging(UserCondition userCondition, int pageIndex, int pageSize);

    //显示销售代表所维护的客户时统计客户数量
    int findUserCount(UserCondition userCondition);
}
