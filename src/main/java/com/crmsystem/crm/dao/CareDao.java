package com.crmsystem.crm.dao;

import com.crmsystem.crm.entity.Care;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 13:15
 * @version:第1版
 * @description:客户关怀表Dao接口
 **/
public interface CareDao {
    //查看未过期的关怀计划
    List<Care> findCareByNow(String empCode);

    //修改关怀计划
    int updateCareById(Care care);

    //根据ID查询关怀计划
    Care findCareById(Care care);

    //删除关怀计划
    int delCare(Care care);

    //添加关怀计划
    int addCare(Care care);

    //过滤查看客户关系表的总数量
    int findCareCountByUserId(@Param("care") Care care);

    //分页过滤查看客户的关怀列表
    List<Care> findCareByUserId(@Param("care") Care care,
                                @Param("pageIndex") Integer pageIndex,
                                @Param("pageSize") Integer pageSize);

}
