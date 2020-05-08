package com.hutc.serviceImpl;

import java.util.List;

import com.hutc.dao.INoticeDao;
import com.hutc.daoImpl.NoticeDaoImpl;
import com.hutc.entity.Notice;
import com.hutc.service.INoticeService;

public class NoticeServiceImpl implements INoticeService {
	INoticeDao dao=new NoticeDaoImpl();
	@Override
	public int addNotice(Notice notice) {
		int result=dao.addNotice(notice);
		return result;
	}
	@Override
	public List<Notice> queryAllNotice() {
		List<Notice> notices=dao.queryAllNotice();
		return notices;
	}
	@Override
	public List<Notice> queryAllNoticeByPage(int page) {
		List<Notice> notices=dao.queryAllNoticeByPage(page);
		return notices;
	}
	@Override
	public Notice queryNoticeById(long notice_id) {
		Notice notice=dao.queryNoticeById(notice_id);
		return notice;
	}

}
