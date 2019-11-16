package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.RightDao;
import com.crmsystem.crm.service.RightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
