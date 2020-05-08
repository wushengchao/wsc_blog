package com.hutc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hutc.entity.UserInfo;
import com.hutc.service.IUserInfoService;
import com.hutc.serviceImpl.UserInfoServiceImpl;

public class UserInfoFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest reqq=(HttpServletRequest) req;
		HttpSession session=reqq.getSession(); //创建session
		IUserInfoService service=new UserInfoServiceImpl();
		Long user_id=(Long) session.getAttribute("user_id");
		UserInfo info=service.queryUserInfo(user_id);
		System.out.println(info.toString());
		req.setAttribute("info", info);
		chain.doFilter(req,resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
