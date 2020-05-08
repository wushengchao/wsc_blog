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

import com.hutc.entity.Admin;
import com.hutc.entity.Note;
import com.hutc.service.INoteService;
import com.hutc.serviceImpl.NoteServiceImpl;

public class AddNoteServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
		/*response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.flush();
		out.close();*/
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		INoteService service=new NoteServiceImpl();
		String content = req.getParameter("note-content");
		
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
		long user_id;
		if(session.getAttribute("user_id")==null){
			user_id=0;
		}else{
			user_id=(Long)session.getAttribute("user_id");
		}
		
		long blog_id=Long.parseLong(req.getParameter("blog_id"));
		Note note=new Note(content,time,user_id,blog_id);
		int result=service.addNote(note);
		
		if(result!=0){
			System.out.println("评论成功！");
			String href=req.getParameter("href");
			resp.sendRedirect("ShowArticleServlet?blog_id="+blog_id+"&href="+href);
		}else{
			System.out.println("评论失败！");
		}
	}

}
