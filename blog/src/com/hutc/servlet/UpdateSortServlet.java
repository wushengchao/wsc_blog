package com.hutc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hutc.service.IBlogService;
import com.hutc.serviceImpl.BlogServiceImpl;

public class UpdateSortServlet extends HttpServlet {

	IBlogService service=new BlogServiceImpl();
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int blog_id=Integer.parseInt(req.getParameter("blog_id"));
		int result=0;
		if(blog_id!=0){
			result=service.updateBlogStatus(blog_id, "不通过");
		}
		
		if(result!=0){
			System.out.println("该博文审核没有通过,操作成功！");
			resp.sendRedirect("admin/blog_admin.jsp");
		}else{
			System.out.println("操作失败");
			resp.sendRedirect("admin/blog_admin.jsp");
		}
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int blog_id=Integer.parseInt(req.getParameter("blog_id"));
		int sort_id=Integer.parseInt(req.getParameter("sort"));
		int sort_result=service.updateBlogSort(blog_id, sort_id);
		int review_result=service.updateBlogStatus(blog_id, "通过");
		if(sort_result!=0){
			System.out.println("更改分类成功");
		}else{
			System.out.println("更改分类失败");
		}
		if(review_result!=0){
			System.out.println("审核通过成功");
			resp.sendRedirect("admin/blog_admin.jsp");
		}else{
			System.out.println("审核通过失败");
		}

	}

}
