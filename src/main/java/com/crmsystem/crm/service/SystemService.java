package com.crmsystem.crm.service;

import org.apache.ibatis.annotations.Param;

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
}
