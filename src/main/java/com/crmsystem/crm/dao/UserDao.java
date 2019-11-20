package com.crmsystem.crm.dao;

import com.crmsystem.crm.entity.Emp;
import com.crmsystem.crm.entity.User;
import com.crmsystem.crm.util.UserCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 13:30
 * @version:第1版
 * @description:客户表Dao接口
 **/
public interface UserDao {
    //3.总监点击我的客户的总数量
    int findUserCountByDerictor(@Param("user")User user);

    //3.总监点击我的客户
    List<User> findUsersByDerictor(@Param("user")User user,
                                   @Param("pageIndex")Integer pageIndex,
                                   @Param("pageSize")Integer pageSize);

    //2.部门经理点击我的客户的总数量
    int findUserCountByManager(@Param("deptId") Integer deptId,
                               @Param("user")User user);

    //2.部门经理点击我的客户
    List<User> findUsersByManager(@Param("deptId") Integer deptId,
                                  @Param("user")User user,
                                  @Param("pageIndex") Integer pageIndex,
                                  @Param("pageSize") Integer pageSize);

    //1.销售代表点击我的客户的总数量
    int findUserCountByEmp(@Param("user") User user);
    //1.销售代表点击我的客户
    List<User> findUsersByEmp(@Param("user") User user,
                              @Param("pageIndex") Integer pageIndex,
                              @Param("pageSize") Integer pageSize);

    //分配客户池中的信息
    int updateLeaveUsers(@Param("empCode") String empCode,
                         @Param("userId") Integer userId);
    //查看客户池客户的总数量
    int findUsersCountByEmpCode(@Param("space") String space);
    //查看客户池里的客户
    List<User> findUsersPageByEmpCode(@Param("space") String space,
                                     @Param("pageIndex") Integer pageIndex,
                                     @Param("pageSize") Integer pageSize);
    //根据客户ID修改客户类型
    int updateUserUserTypeById(@Param("user") User user);
    //根据客户ID修改信誉等级
    int updateUserCreditGradeById(@Param("user") User user);
    //根据ID修改客户状态
    int updateUserStatesById(@Param("user") User user);
    //根据客户ID查询客户信息
    User findUserById(@Param("userId") Integer userId);

    //显示销售代表所维护的客户时分页检索客户
    List<User> findUserPaging(@Param("userCondition") UserCondition userCondition,
                              @Param("pageIndex") int pageIndex,
                              @Param("pageSize") int pageSize);

    //显示销售代表所维护的客户时统计客户数量
    int findUserCount(@Param("userCondition")UserCondition userCondition);
}
