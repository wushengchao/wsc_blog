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
import com.hutc.entity.Page;
import com.hutc.service.INoticeService;
import com.hutc.serviceImpl.NoticeServiceImpl;

public class AllNoticeListFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		INoticeService service=new NoticeServiceImpl();
		HttpServletRequest reqq=(HttpServletRequest) req;
		HttpSession session=reqq.getSession(); //创建session
		
		List<Notice> allNotice=service.queryAllNotice();
		int size = allNotice.size();
		
		int total;
		
		if(size%16==0){
			total=size/16;
		}else{
			total=size/16+1;
		}
		
		Page noticePage=new Page();
		noticePage.setFirstPage(1);
		noticePage.setCurrentPage(1);
		noticePage.setLastPage(total);
		noticePage.setTotalPage(total);
		List<Notice> notices=service.queryAllNoticeByPage(noticePage.getCurrentPage());
		session.setAttribute("notice_size", size);
		session.setAttribute("notice_page", noticePage);
		session.setAttribute("admin_notice_list", notices);
		
		chain.doFilter(req,resp);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
