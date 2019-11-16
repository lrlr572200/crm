package com.crmsystem.crm.service.impl;

import com.crmsystem.crm.dao.SystemDao;
import com.crmsystem.crm.service.SystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
