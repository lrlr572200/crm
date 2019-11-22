package com.crmsystem.crm.service;

import com.crmsystem.crm.entity.Emp;
import com.crmsystem.crm.entity.Serve;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 14:59
 * @version:第1版
 * @description:服务表Service接口
 **/
public interface ServeService {
    //首页显示待办服务
    List<Serve> findServeOnIndex(Emp emp, Serve serve,Serve serve1, Integer pageIndex, Integer pageSize);

    //添加服务
    int addServe(Serve serve);
    //分页过滤查看服务
    List<Serve> findServePage(Integer pageIndex,
                              Integer pageSize,
                              Serve serve);
    //过滤查看服务总数量
    int findServeCount(Serve serve);


    //分页检索的方法
    List<Serve> findServePaging(@Param("serve") Serve serve,
                                @Param("pageIndex") Integer pageIndex,
                                @Param("pageSize") Integer pageSize);

    //分页检索统计数据量
    int fintServeCot(@Param("serve") Serve serve);

    //修改服务单的方法
    int updServe(@Param("serve") Serve serve);
}
