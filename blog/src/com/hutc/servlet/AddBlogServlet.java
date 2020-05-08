package com.hutc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hutc.entity.Blog;
import com.hutc.service.IBlogService;
import com.hutc.serviceImpl.BlogServiceImpl;

public class AddBlogServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		IBlogService service=new BlogServiceImpl();
		
		String title=req.getParameter("title");
		String content=req.getParameter("content");
		//时间获取
		Date nowTime=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String dateString=sdf.format(nowTime);
		java.sql.Timestamp time = null;
		try {
			Date utilDate=sdf.parse(dateString);
			long t=utilDate.getTime(); //很重要，否则util不能转为sql，参考shopping注册
			time=new java.sql.Timestamp(t);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session=req.getSession();
		long user_id=(Long) session.getAttribute("user_id");
		Blog blog = new Blog(title,content,time,user_id);
		
		int result=service.addBlog(blog);
		if(result!=0){
			System.out.println("添加blog成功！"+result);
			resp.sendRedirect("addSuccess.html");
			
		}else{
			System.out.println("添加blog失败！"+result);
		}
		
		out.flush();
		out.close();
	}

}
