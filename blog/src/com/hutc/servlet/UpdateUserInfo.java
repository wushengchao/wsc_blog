package com.hutc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hutc.entity.UserInfo;
import com.hutc.service.IUserInfoService;
import com.hutc.serviceImpl.UserInfoServiceImpl;

public class UpdateUserInfo extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		IUserInfoService service=new UserInfoServiceImpl();
		long user_id=Long.parseLong(req.getParameter("user_id"));
		String real_name=req.getParameter("real_name");
		String sex=req.getParameter("sex");
		String institute=req.getParameter("institute");
		
		String birthday=req.getParameter("year")+"-"+req.getParameter("month")+"-"+req.getParameter("day");
		Date date=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(!("null".equals(birthday)&&"".equals(birthday))){
			try {
				java.util.Date utilDate=sdf.parse(birthday);
				long time=utilDate.getTime();
				date=new Date(time);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		String email=req.getParameter("email");
		String contact=req.getParameter("contact");
		String introduce=req.getParameter("ps");
		UserInfo info=new UserInfo(user_id,real_name,sex,institute,date,email,contact,introduce);
		int result=service.updateUserInfo(info);
		if(result!=0){
			out.println("1");
			System.out.println("用户信息更新成功！");
		}else{
			System.out.println("用户信息更新失败！");
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
