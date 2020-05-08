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

public class MyBlogPageServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		/*resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.flush();
		out.close();*/
		HttpSession session=req.getSession();
		Long user_id=null;
		if(session.getAttribute("user_id")!=null){
			user_id=(Long) session.getAttribute("user_id");
		}else{
			resp.sendRedirect("login.jsp");
		}
		
		
		IBlogService service=new BlogServiceImpl();
		//设置当前页码
		Integer page=Integer.parseInt(req.getParameter("page"));
		Page p=(Page)session.getAttribute("page");
		p.setCurrentPage(page);
		
		List<Blog> blogs=service.myPageBloglist(page, user_id);
		req.setAttribute("blogList", blogs);
		
		req.getRequestDispatcher("user/myBlogList.jsp").forward(req, resp);
		
		
		
		
	}

}
