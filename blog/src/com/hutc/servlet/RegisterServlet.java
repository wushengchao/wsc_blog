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

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		IUserService service=new UserServiceImpl();
		
		String username=req.getParameter("user");
		String password=req.getParameter("pass");
		User user=new User(username,password);
		int result=service.register(user);
		if(result!=0){
			System.out.println("注册成功");
			out.println("1");
		}else{
			System.out.println("注册失败");
		}
		
		//停止3秒
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
