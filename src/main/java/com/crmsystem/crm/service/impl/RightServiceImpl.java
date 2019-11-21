package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.RightDao;
import com.crmsystem.crm.entity.Right;
import com.crmsystem.crm.service.RightService;
import com.crmsystem.crm.util.RoleRight;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 14:55
 * @version:第1版
 * @description:权限表Service接口实现类
 **/
@Service
public class RightServiceImpl implements RightService {
    @Resource
    RightDao rightDao;

    @Override
    public int findGradeByRolesId(Integer rolesId) {
        return rightDao.findGradeByRolesId(rolesId);
    }

    //查看所有权限等级分组
    @Override
    public List<String> findRightMain() {
        return rightDao.findRightMain();
    }

    //查找权限
    @Override
    public Right findRightByMain(String main) {
        return rightDao.findRightByMain(main);
    }

    //动态查找权限
    @Override
    public Right findRight(Right right) {
        return rightDao.findRight(right);
    }

    //添加权限
    @Override
    @Transactional
    public int addRight(RoleRight right) {
        return rightDao.addRight(right);
    }

    //修改权限
    @Override
    @Transactional
    public int updRighrt(Right right) {
        return rightDao.updRighrt(right);
    }

    //删除权限
    @Override
    @Transactional
    public int delRight(Integer rightId) {
        return rightDao.delRight(rightId);
    }
}
