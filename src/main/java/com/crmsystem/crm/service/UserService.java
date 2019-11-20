package com.crmsystem.crm.service;

import com.crmsystem.crm.entity.Emp;
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
    //3.总监点击我的客户的总数量
    int findUserCountByDerictor(User user);

    //3.总监点击我的客户
    List<User> findUsersByDerictor(User user, Integer pageIndex, Integer pageSize);

    //2.部门经理点击我的客户的总数量
    int findUserCountByManager(Integer deptId,User user);

    //2.部门经理点击我的客户
    List<User> findUsersByManager(Integer deptId,User user,Integer pageIndex, Integer pageSize);

    //1.销售代表点击我的客户的总数量
    int findUserCountByEmp(User user);
    //1.销售代表点击我的客户
    List<User> findUsersByEmp(User user,Integer pageIndex,Integer pageSize);

    //分配客户池中的信息
    int updateLeaveUsers(String empCode, Integer userId);

    //查看客户池客户的总数量
    int findUsersCountByEmpCode(String space);

    //查看客户池里的客户
    List<User> findUsersPageByEmpCode(String space, Integer pageIndex, Integer pageSize);
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
