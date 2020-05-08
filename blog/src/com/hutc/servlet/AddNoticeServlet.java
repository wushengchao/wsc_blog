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
import com.hutc.entity.Notice;
import com.hutc.service.INoticeService;
import com.hutc.serviceImpl.NoticeServiceImpl;

public class AddNoticeServlet extends HttpServlet {

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
		long admin_id=((Admin)session.getAttribute("admin")).getAdmin_id();
		
		Notice notice=new Notice(title,content,time,admin_id);
		INoticeService service=new NoticeServiceImpl();
		int result=service.addNotice(notice);
		if(result!=0){
			System.out.println("公告发布成功！");
			resp.sendRedirect("admin/notice_admin.jsp");
		}else{
			System.out.println("公告发布失败！");
		}
	}

}
