package com.hutc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.weld.context.RequestContext;

import com.hutc.entity.Blog;
import com.hutc.service.IBlogService;
import com.hutc.serviceImpl.BlogServiceImpl;

public class QueryNewBlogServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doPost(req,resp);
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		HttpSession session= req.getSession();
		
		IBlogService service=new BlogServiceImpl();
		
		long user_id=(Long) session.getAttribute("user_id");
		Blog blog = service.queryNewBlog(user_id);
		System.out.println(user_id+blog.toString());
		req.setAttribute("blog", blog);
		
		req.getRequestDispatcher("showArticle.jsp").forward(req, resp);
		out.flush();
		out.close();
	}

}
