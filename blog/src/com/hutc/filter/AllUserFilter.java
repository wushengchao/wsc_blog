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

import com.hutc.entity.User;
import com.hutc.service.IUserService;
import com.hutc.serviceImpl.UserServiceImpl;

public class AllUserFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest reqq=(HttpServletRequest) req;
		HttpSession session=reqq.getSession(); //创建session
		//全部用户
		IUserService service=new UserServiceImpl();
		List<User> users=service.queryAllUser();
		int user_size=users.size();
		req.setAttribute("users", users);
		req.setAttribute("user_size", user_size);
		//新用户
		List<User> new_users=service.queryNewUser();
		int new_size=new_users.size();
		req.setAttribute("new_users", new_users);
		req.setAttribute("new_size", new_size);
		
		
		System.out.println("总用户有"+user_size+"人");
		System.out.println("新用户有"+new_size+"人");
		
		chain.doFilter(req,resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
