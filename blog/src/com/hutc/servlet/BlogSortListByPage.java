package com.hutc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hutc.entity.Blog;
import com.hutc.entity.Page;
import com.hutc.service.IBlogService;
import com.hutc.serviceImpl.BlogServiceImpl;

public class BlogSortListByPage extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.flush();
		out.close();*/
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		IBlogService service=new BlogServiceImpl();
		HttpSession session=req.getSession();
		Integer page=Integer.parseInt(req.getParameter("page"));
		List<Blog> blogs;
		Page p;
		int sort_id=9;
		if(Integer.parseInt(req.getParameter("sort_id"))==sort_id){
			p=(Page)session.getAttribute("page");
			p.setCurrentPage(page);
			blogs=service.queryReviewPassBlogByPage(page,4);
			System.out.println(Integer.parseInt(req.getParameter("sort_id")));
		}else{
			sort_id=Integer.parseInt(req.getParameter("sort_id"));

			List<Blog> allBlog=service.queryReviewBySort(sort_id);
			int size=allBlog.size();
			int total;
			
			if(size%4==0){
				total=size/4;
			}else{
				total=size/4+1;
			}
			
			p=new Page();
			int first;
			int t=0;
			if(size==t){
				first=0;
				p.setCurrentPage(0);
			}else{
				first=1;
				p.setCurrentPage(page);
			}
			p.setFirstPage(first);
			p.setLastPage(total);
			p.setTotalPage(total);
			
			blogs=service.queryReviewBySortPage(sort_id, p.getCurrentPage());
		}
		
		req.setAttribute("blogList", blogs);
		req.setAttribute("page", p);
		req.setAttribute("sort_id", sort_id);
		req.getRequestDispatcher("blog.jsp").forward(req, resp);
	}

}
