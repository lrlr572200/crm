package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.SystemDao;
import com.crmsystem.crm.entity.System;
import com.crmsystem.crm.service.SystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 15:01
 * @version:第1版
 * @description:系统变量表Service接口实现类
 **/
@Service
public class SystemServiceImpl implements SystemService {
    @Resource
    SystemDao systemDao;
    //查看机会分配变量
    @Override
    public String findChanValue(String name) {
        return systemDao.findChanValue(name);
    }
    //修改机会分配变量
    @Override
    public int updateChanValue(String value,String name) {
        return systemDao.updateChanValue(value,name);
    }

    //查看定时任务
    @Override
    public List<System> findTimedTask(System system, Integer pageIndex, Integer pageSize) {
        return systemDao.findTimedTask(system,pageIndex,pageSize);
    }

    //统计条数
    @Override
    public int findCot(System system) {
        return systemDao.findCot(system);
    }

    //修改系统变量
    @Override
    public int updSystem(System system) {
        return systemDao.updSystem(system);
    }

    //根据编号查找系统变量
    @Override
    public System findTheSystem(Integer id) {
        return systemDao.findTheSystem(id);
    }

    //无分页条件查找系统变量
    @Override
    public List<System> findAllSystem(System system) {
        return systemDao.findAllSystem(system);
    }

    //等值条件查找
    @Override
    public List<System> findEquSystem(System system) {
        return systemDao.findEquSystem(system);
    }
}
