package com.crmsystem.crm.dao;

import com.crmsystem.crm.entity.Notice;
import com.crmsystem.crm.util.NoticeUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 13:23
 * @version:第1版
 * @description:公告表Dao接口
 **/
public interface NoticeDao {
    //查询所有公告
    List<NoticeUtil> findAllNotice();
    //查询一个公告
    NoticeUtil findANotice(Integer noticeId);
    //添加公告
    int addNotice(Notice notice);
    //删除公告
    int delNotice(Integer noticeId);
    //修改公告
    int updateNotice(Notice notice);
    //过滤查询公告总数量
    int findNoticeCount(@Param("notice") NoticeUtil notice);
    //分页过滤查询
    List<NoticeUtil> findNoticePage(@Param("pageSize") Integer pageSize,
                                           @Param("pageIndex") Integer pageIndex,
                                           @Param("notice") NoticeUtil notice);

}
