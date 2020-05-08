package com.hutc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hutc.entity.Blog;
import com.hutc.entity.Note;
import com.hutc.entity.Notice;
import com.hutc.service.IBlogService;
import com.hutc.service.INoteService;
import com.hutc.service.INoticeService;
import com.hutc.serviceImpl.BlogServiceImpl;
import com.hutc.serviceImpl.NoteServiceImpl;
import com.hutc.serviceImpl.NoticeServiceImpl;

public class ShowArticleServlet extends HttpServlet {


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
		INoticeService notice_service=new NoticeServiceImpl();
		INoteService note_service=new NoteServiceImpl();
		if(req.getParameter("blog_id")!=null){
			long blog_id=Long.parseLong(req.getParameter("blog_id"));
			//查找类名
			//String sort_name=service.querySortName((int)blog_id);
			//req.setAttribute("sort_name", sort_name);
			Blog blog = service.queryBlogById(blog_id);
			//查找评论
			List<Note> notes=note_service.queryAllNote((int)blog_id);
			req.setAttribute("notes", notes);
			//System.out.println(blog.toString());
			req.setAttribute("blog", blog);
		}else if(req.getParameter("notice_id")!=null){
			//公告查询
			long notice_id=Long.parseLong(req.getParameter("notice_id"));
			Notice notice=notice_service.queryNoticeById(notice_id);
			req.setAttribute("blog", notice);
			
		}
		
		String wait=req.getParameter("wait");
		String all=req.getParameter("all");
		String ready=req.getParameter("ready");
		if(wait!=null){
			req.getRequestDispatcher("admin/blog_review.jsp").forward(req, resp);
		}else if(all!=null){
			req.getRequestDispatcher("admin/blog_article.jsp").forward(req, resp);
		}else if(ready!=null){
			
			req.getRequestDispatcher("admin/sort_update.jsp").forward(req, resp);
		}else if(req.getParameter("notice_id")!=null){
			req.getRequestDispatcher("showNotice.jsp").forward(req, resp);
		}else if(req.getParameter("index")!=null){
			req.getRequestDispatcher("admin/index_set.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("showArticle.jsp").forward(req, resp);
		}
		
		
		
	}

}
