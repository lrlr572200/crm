package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.UserDao;
import com.crmsystem.crm.entity.Emp;
import com.crmsystem.crm.entity.User;
import com.crmsystem.crm.service.UserService;
import com.crmsystem.crm.util.UserCondition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 15:03
 * @version:第1版
 * @description:客户表Service接口实现类
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    //修改客户
    @Override
    public int updateUserByEmp(User user) {
        return userDao.updateUserByEmp(user);
    }

    //销售代表删除客户
    @Override
    public int delUserByEmp(User user) {
        return userDao.delUserByEmp(user);
    }

    //添加客户
    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    //3.总监点击我的客户的总数量
    @Override
    public int findUserCountByDerictor(User user) {
        return userDao.findUserCountByDerictor(user);
    }

    //3.总监点击我的客户
    @Override
    public List<User> findUsersByDerictor(User user, Integer pageIndex, Integer pageSize) {
        return userDao.findUsersByDerictor(user,pageIndex,pageSize);
    }

    //2.部门经理点击我的客户的总数量
    @Override
    public int findUserCountByManager(Integer deptId,User user) {
        return userDao.findUserCountByManager(deptId,user);
    }

    //2.部门经理点击我的客户
    @Override
    public List<User> findUsersByManager(Integer deptId,User user,Integer pageIndex, Integer pageSize) {
        return userDao.findUsersByManager(deptId,user,pageIndex,pageSize);
    }

    //1.销售代表点击我的客户的总数量
    @Override
    public int findUserCountByEmp(User user) {
        return userDao.findUserCountByEmp(user);
    }

    //1.销售代表点击我的客户
    @Override
    public List<User> findUsersByEmp(User user,Integer pageIndex,Integer pageSize) {
        return userDao.findUsersByEmp(user,pageIndex,pageSize);
    }

    //分配客户池中的信息
    @Override
    public int updateLeaveUsers(String empCode, Integer userId) {
        return userDao.updateLeaveUsers(empCode,userId);
    }

    //查看客户池的客户总数量
    @Override
    public int findUsersCountByEmpCode(String space) {
        return userDao.findUsersCountByEmpCode(space);
    }

    //查看客户池的客户
    @Override
    public List<User> findUsersPageByEmpCode(String space, Integer pageIndex, Integer pageSize) {
        return userDao.findUsersPageByEmpCode(space,pageIndex,pageSize);
    }

    //根据客户ID修改客户类型
    @Override
    public int updateUserUserTypeById(User user) {
        return userDao.updateUserUserTypeById(user);
    }
    //根据客户ID修改信誉等级
    @Override
    public int updateUserCreditGradeById(User user) {
        return userDao.updateUserCreditGradeById(user);
    }

    //根据客户ID修改信息
    @Override
    public int updateUserStatesById(User user) {
        return userDao.updateUserStatesById(user);
    }

    //根据客户ID查询客户信息
    @Override
    public User findUserById(Integer userId) {
        return userDao.findUserById(userId);
    }

    //显示销售代表所维护的客户时分页检索客户
    @Override
    public List<User> findUserPaging(UserCondition userCondition, int pageIndex, int pageSize) {
        return userDao.findUserPaging(userCondition,(pageIndex-1)*pageSize,pageSize) ;
    }

    //显示销售代表所维护的客户时统计客户数量
    @Override
    public int findUserCount(UserCondition userCondition) {
        return userDao.findUserCount(userCondition);
    }
}
