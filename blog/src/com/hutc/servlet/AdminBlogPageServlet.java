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
import com.hutc.entity.Notice;
import com.hutc.entity.Page;
import com.hutc.service.IBlogService;
import com.hutc.service.INoticeService;
import com.hutc.serviceImpl.BlogServiceImpl;
import com.hutc.serviceImpl.NoticeServiceImpl;

public class AdminBlogPageServlet extends HttpServlet {

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
		
		HttpSession session=req.getSession();
		IBlogService service=new BlogServiceImpl();
		//设置当前页码
		if(req.getParameter("page")!=null){
			Integer page=Integer.parseInt(req.getParameter("page"));
			Page p=(Page)session.getAttribute("all_page");
			p.setCurrentPage(page);
			System.out.println("当前页："+p.getCurrentPage());
			List<Blog> blogs=service.allPageBloglist(page,16);
			req.setAttribute("blogList", blogs);
			req.setAttribute("show_list",".all-blog");
			req.setAttribute("red_li",".all");
			req.getRequestDispatcher("admin/blog_admin.jsp").forward(req, resp);
		}else if(req.getParameter("wait_page")!=null){
			Integer page=Integer.parseInt(req.getParameter("wait_page"));
			Page p=(Page)session.getAttribute("wait_page");
			p.setCurrentPage(page);
			System.out.println("当前页："+p.getCurrentPage());
			List<Blog> blogs=service.queryNoReviewBlogByPage(page);
			req.setAttribute("wait_blogList", blogs);
			req.setAttribute("show_list",".wait-blog");
			req.setAttribute("red_li",".wait");
			req.getRequestDispatcher("admin/blog_admin.jsp").forward(req, resp);
		}else if(req.getParameter("ready_page")!=null){
			Integer page=Integer.parseInt(req.getParameter("ready_page"));
			Page p=(Page)session.getAttribute("ready_page");
			p.setCurrentPage(page);
			System.out.println("当前页："+p.getCurrentPage());
			List<Blog> blogs=service.queryReviewBlogByPage(page);
			req.setAttribute("ready_blogList", blogs);
			req.setAttribute("show_list",".ready-blog");
			req.setAttribute("red_li",".ready");
			req.getRequestDispatcher("admin/blog_admin.jsp").forward(req, resp);
			
		}else if(req.getParameter("notice_page")!=null){
			INoticeService notice_service=new NoticeServiceImpl();
			Integer page=Integer.parseInt(req.getParameter("notice_page"));
			Page p=(Page)session.getAttribute("notice_page");
			p.setCurrentPage(page);
			System.out.println("当前页："+p.getCurrentPage());
			List<Notice> notices=notice_service.queryAllNoticeByPage(page);
			req.setAttribute("admin_notice_list", notices);
			req.setAttribute("show_list",".notice-list");
			req.setAttribute("red_li",".all-notice");
			req.getRequestDispatcher("admin/notice_admin.jsp").forward(req, resp);
		}else if(req.getParameter("pass_page")!=null){
			Integer page=Integer.parseInt(req.getParameter("pass_page"));
			Page p=(Page)session.getAttribute("pass_page");
			p.setCurrentPage(page);
			System.out.println("当前页："+p.getCurrentPage());
			List<Blog> blogs=service.queryReviewPassBlogByPage(page,16);
			req.setAttribute("pass_blogList", blogs);
			req.setAttribute("show_list",".blog-choose-list");
			req.setAttribute("red_li",".blog-choose");
			req.getRequestDispatcher("admin/index_admin.jsp").forward(req, resp);
		}
		
		
		
		
	}

}
