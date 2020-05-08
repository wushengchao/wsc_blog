package com.hutc.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hutc.entity.Blog;
import com.hutc.entity.Page;
import com.hutc.service.IBlogService;
import com.hutc.serviceImpl.BlogServiceImpl;

public class MyBlogListFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
			IBlogService service=new BlogServiceImpl();
			HttpServletRequest reqq=(HttpServletRequest) req;
			HttpServletResponse respp=(HttpServletResponse) resp;
			HttpSession session=reqq.getSession(); //创建session
			Long user_id=null;

			if(session.getAttribute("user_id")!=null){
				user_id=(Long) session.getAttribute("user_id");
				List<Blog> blogs=service.myAllBlogList(user_id);
				
				int size=blogs.size();
				
				int total;
				
				
				if(size%8==0){
					total=size/8;
				}else{
					total=size/8+1;
				}
				
				Page p=new Page();
			
				p.setFirstPage(1);
				p.setCurrentPage(1);
				p.setLastPage(total);
				p.setTotalPage(total);
				
				List<Blog> blogList=service.myPageBloglist(p.getFirstPage(), user_id);
				session.setAttribute("page", p);
				req.setAttribute("blogList", blogList);
				chain.doFilter(req,resp);
			}else{
				respp.sendRedirect("../login.jsp");
			}
			
			
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
