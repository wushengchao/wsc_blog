package com.hutc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hutc.entity.IndexBlog;
import com.hutc.service.IIndexBlogService;
import com.hutc.serviceImpl.IndexBlogServiceImpl;

public class AddIndexBlogServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		IIndexBlogService service = new IndexBlogServiceImpl();
		long blog_id=Long.parseLong(req.getParameter("blog_id"));
		String title=req.getParameter("title");
		String describe=req.getParameter("describe");
		IndexBlog blog=new IndexBlog(blog_id,title,describe);
		int result = service.addIndexBlog(blog);
		if(result!=0){
			System.out.println("成功推送至首页！");
			resp.sendRedirect("admin/index_admin.jsp");
		}else{
			System.out.println("操作失败");
		}
	}

}
