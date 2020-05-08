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


public class CheckName extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doPost(req,resp);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		IUserService service=new UserServiceImpl();
		String uname=request.getParameter("user");
		User user=service.queryUserByName(uname);
		//停止3秒
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
				e1.printStackTrace();
		}
		if(user!=null){
			out.println("1");
			System.out.println("用户名已存在！");
		}else{
			System.out.println(uname+"用户名可用！");
			
		}
		
		out.flush();
		out.close();
		
		
}

}
