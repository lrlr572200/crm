package com.crmsystem.crm.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 13:29
 * @version:第1版
 * @description:系统变量表Dao接口
 **/
public interface SystemDao {
    //查看机会分配变量
    String findChanValue(@Param("name") String name);
    //修改机会分配变量
    int updateChanValue(@Param("value") String value,@Param("name") String name);
}
