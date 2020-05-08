package com.hutc.dao;

import java.util.List;

import com.hutc.entity.Notice;

public interface INoticeDao {
	//添加公告
	int addNotice(Notice notice);
	
	//查找所有公告
	List<Notice> queryAllNotice();
	
	//所有公告分页
	List<Notice> queryAllNoticeByPage(int page);
	
	//通过id查找
	Notice queryNoticeById(long notice_id);
	
}
