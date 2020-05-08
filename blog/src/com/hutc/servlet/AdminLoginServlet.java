package com.hutc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hutc.entity.Admin;
import com.hutc.service.IAdminService;
import com.hutc.serviceImpl.AdminServiceImpl;

public class AdminLoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		/*response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.flush();
		out.close();*/
		
		IAdminService service=new AdminServiceImpl();
		String admin_name=req.getParameter("username");
		String admin_pass=req.getParameter("password");
		Admin admin=new Admin(admin_name,admin_pass);
		System.out.println(admin.toString());
		Admin login_admin=service.admin_login(admin);
		
		if(login_admin!=null){
			req.getSession().setAttribute("admin", login_admin);
			System.out.println(login_admin.toString());
			resp.sendRedirect("admin/index.jsp");
			System.out.println("登录成功");
		}else{
			System.out.println("登录失败");
		}
		
		
	}

}
