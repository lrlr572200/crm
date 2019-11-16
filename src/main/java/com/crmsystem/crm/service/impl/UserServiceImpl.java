package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.UserDao;
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
