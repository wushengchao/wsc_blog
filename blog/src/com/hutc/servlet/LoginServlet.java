package com.hutc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hutc.entity.User;
import com.hutc.service.IUserService;
import com.hutc.serviceImpl.UserServiceImpl;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	IUserService service=new UserServiceImpl();
		
		String name=req.getParameter("user");
		String password=req.getParameter("pass");
		User user=new User();
		
		user.setUserName(name);
		user.setPassword(password);
		System.out.println(user.toString());
		User login_user=service.login(user);
		
		if(login_user==null){
			System.out.println("登录失败");
			
			//out.print(1);
		}else{
			//用户名保存在session域中,用session,也用cookie
			req.getSession().setAttribute("user_id", login_user.getUserId());
			//System.out.println(req.getSession().getAttribute("user").toString());
			//req.getRequestDispatcher("index.jsp").forward(req, resp);
			System.out.println(login_user.toString());
			System.out.println("登录成功");
			resp.sendRedirect("user/myBlogList.jsp");
			//out.print(0);
		}
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		IUserService service=new UserServiceImpl();
		
		String name=req.getParameter("user");
		String password=req.getParameter("pass");
		User user=new User();
		
		user.setUserName(name);
		user.setPassword(password);
		System.out.println(user.toString());
		User login_user=service.login(user);
		
		if(login_user==null){
			System.out.println("登录失败");
			
			out.print(1);
		}else{
			//用户名保存在session域中,用session,也用cookie
			req.getSession().setAttribute("user_id", login_user.getUserId());
			//System.out.println(req.getSession().getAttribute("user").toString());
			//req.getRequestDispatcher("index.jsp").forward(req, resp);
			System.out.println(login_user.toString());
			System.out.println("登录成功");
			out.print(0);
		}
		
		//停止2秒
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		out.flush();
		out.close();
	}

}
