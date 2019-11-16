package com.crmsystem.crm.dao;

import com.crmsystem.crm.entity.Serve;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 13:28
 * @version:第1版
 * @description:服务表Dao接口
 **/
public interface ServeDao {
    //添加服务
    int addServe(Serve serve);
    //分页过滤查看服务
    List<Serve> findServePage(@Param("pageIndex") Integer pageIndex,
                              @Param("pageSize") Integer pageSize,
                              @Param("serve") Serve serve);
    //过滤查看服务总数量
    int findServeCount(@Param("serve") Serve serve);


    //分页检索的方法
    List<Serve> findServePaging(@Param("serve") Serve serve,
                                @Param("pageIndex") Integer pageIndex,
                                @Param("pageSize") Integer pageSize);

    //分页检索统计数据量
    int fintServeCot(@Param("serve") Serve serve);

    //修改服务单的方法
    int updServe(@Param("serve") Serve serve);

}
