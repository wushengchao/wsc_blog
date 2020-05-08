package com.hutc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hutc.daoImpl.BlogDaoImpl;
import com.hutc.service.IBlogService;
import com.hutc.serviceImpl.BlogServiceImpl;

public class DeleteBlogServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		/*response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.flush();
		out.close();*/
		IBlogService service=new BlogServiceImpl();
		long blog_id=Long.parseLong(req.getParameter("blog_id"));
		String href=req.getParameter("href");
		System.out.println(href);
		int result=service.deleteBlog(blog_id);
		if(result!=0){
			System.out.println("删除成功");
			resp.sendRedirect(href);
		}else{
			System.out.println("删除失败");
		}
	}

}
