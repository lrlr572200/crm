package com.crmsystem.crm.service;

import com.crmsystem.crm.entity.System;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 15:01
 * @version:第1版
 * @description:系统变量表Service接口
 **/
public interface SystemService {
    //查看机会分配变量
    String findChanValue(String name);
    //修改机会分配变量
    int updateChanValue(String value,String name);

    //查看定时任务
    List<System> findTimedTask(System system,Integer pageIndex,Integer pageSize);

    //统计条数
    int findCot(System system);

    //修改系统变量
    int updSystem(System system);

    //根据编号查找系统变量
    System findTheSystem(Integer id);

    //无分页条件查找系统变量
    List<System> findAllSystem(System system);

    //等值条件查找
    List<System> findEquSystem(System system);
}
