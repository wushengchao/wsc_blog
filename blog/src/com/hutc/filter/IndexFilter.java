package com.hutc.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hutc.entity.Notice;
import com.hutc.service.INoticeService;
import com.hutc.serviceImpl.NoticeServiceImpl;

public class IndexFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest reqq=(HttpServletRequest) req;
		HttpSession session=reqq.getSession(); //创建session
		//查找公告
		INoticeService service=new NoticeServiceImpl();
		List<Notice> allNotice=service.queryAllNotice();
		session.setAttribute("allNotice", allNotice);
		
		
		
		chain.doFilter(req,resp);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
