package com.crmsystem.crm.service;

import com.crmsystem.crm.entity.Notice;
import com.crmsystem.crm.util.NoticeUtil;

import java.util.List;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author:
 * @create: 2019-11-04 14:48
 * @version:第1版
 * @description:公告表Service接口
 **/
public interface NoticeService {
    //查询一个公告
    NoticeUtil findANotice(Integer noticeId);
    //查询所有公告
    List<NoticeUtil> findAllNotice();
    //添加公告
    int addNotice(Notice notice);
    //删除公告
    int delNotice(Integer noticeId);
    //修改公告
    int updateNotice(Notice notice);
    //分页过滤查询
    List<NoticeUtil> findNoticePage(Integer pageSize, Integer pageIndex,
                                           NoticeUtil notice);
    //过滤查询公告总数量
    int findNoticeCount(NoticeUtil notice);
}
