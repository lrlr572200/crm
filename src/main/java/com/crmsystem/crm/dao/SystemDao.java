package com.crmsystem.crm.dao;

import com.crmsystem.crm.entity.System;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    //查看定时任务
    List<System> findTimedTask(@Param("system") System system,
                               @Param("pageIndex") Integer pageIndex,
                               @Param("pageSize") Integer pageSize);

    //统计条数
    int findCot(@Param("system") System system);

    //修改系统变量
    int updSystem(@Param("system") System system);

    //根据编号查找系统变量
    System findTheSystem(@Param("id")Integer id);

    //无分页条件查找系统变量
    List<System> findAllSystem(@Param("system")System system);

    //等值条件查找
    List<System> findEquSystem(@Param("system")System system);

}
