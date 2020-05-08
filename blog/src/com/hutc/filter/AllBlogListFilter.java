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

public class AllBlogListFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		IBlogService service=new BlogServiceImpl();
		HttpServletRequest reqq=(HttpServletRequest) req;
		
		HttpSession session=reqq.getSession(); //创建session
		
		
		List<Blog> blogs=service.queryReviewPassBlog();
		
		int size=blogs.size();
		//页码设置
		int total;
		if(size%4==0){
			total=size/4;
		}else{
			total=size/4+1;
		}
		Page page=new Page();
		int first;
		int t=0;
		if(size==t){
			first=0;
			page.setCurrentPage(0);
		}else{
			first=1;
			page.setCurrentPage(1);
		}
		page.setFirstPage(first);
		page.setLastPage(total);
		page.setTotalPage(total);
		List<Blog> blogList=service.queryReviewPassBlogByPage(1,4);
		session.setAttribute("page", page);
		req.setAttribute("sort_id", 9);
		req.setAttribute("blogList", blogList);
		
		
		
		
		chain.doFilter(req,resp);
	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
