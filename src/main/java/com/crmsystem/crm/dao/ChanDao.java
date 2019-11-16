package com.crmsystem.crm.dao;

import com.crmsystem.crm.entity.Chan;
import com.crmsystem.crm.util.PageSupport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:郝存凯
 * @create: 2019-11-04 13:17
 * @version:第1版
 * @description:机会表Dao接口
 **/
public interface ChanDao {
    //创建机会
    int addChan(Chan chan);
    //分页过滤查询机会
    List<Chan> findChanPage(@Param("pageIndex") Integer pageIndex,
                            @Param("pageSize") Integer pageSize,
                            @Param("chan") Chan chan);
    //过滤查询总条数
    int findChanCount(@Param("chan") Chan chan);


    //分页过滤查询未分配的机会
    List<Chan> findChanByDid(@Param("deptId") Integer deptId,
                             @Param("pageIndex") Integer pageIndex,
                             @Param("pageSize") Integer pageSize);
    //分页过滤查询未分配的数量
    int findChanCountByDid(@Param("deptId") Integer deptId);

    //分页过滤查询的方法--李国庆
    List<Chan> findChanPaging(@Param("pageIndex") Integer pageIndex,
                              @Param("pageSize") Integer pageSize,
                              @Param("chan") Chan chan);

    //过滤时统计数据量的方法--李国庆
    int findChanCot(@Param("chan")Chan chan);

    //查看机会来源
    List<String> findSource();

}
