package com.crmsystem.crm.service;

import com.crmsystem.crm.entity.Chan;
import com.crmsystem.crm.util.PageSupport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:郝存凯
 * @create: 2019-11-04 14:29
 * @version:第1版
 * @description:机会表Service接口
 **/
public interface ChanService {
    //创建机会
    int addChan(Chan chan);
    //过滤查询总条数
    int findChanCount(@Param("chan") Chan chan);
    //分页过滤查询机会

    /*PageSupport<Chan> findChan(Integer pageIndex, Integer pageSize, Chan chan);*/

    //分页过滤查询未分配的机会
    List<Chan> findChanByDid(Integer deptId, Integer pageIndex, Integer pageSize);
    //分页过滤查询未分配的数量
    int findChanCountByDid(Integer deptId);

    List<Chan> findChanPage(Integer pageIndex, Integer pageSize, Chan chan);

    //分页过滤查询的方法--李国庆
    List<Chan> findChanPaging(Integer pageIndex, Integer pageSize, Chan chan);

    //过滤时统计数据量的方法--李国庆
    int findChanCot(Chan chan);

    //查看机会来源
    List<String> findSource();

    //修改机会分配给员工
    int updateChan(Chan chan);
}
