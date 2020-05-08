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
import javax.servlet.http.HttpSession;

import com.hutc.entity.Blog;
import com.hutc.entity.Page;
import com.hutc.service.IBlogService;
import com.hutc.serviceImpl.BlogServiceImpl;

public class BlogAdminFilter implements Filter {

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
		
		//所有的博客
		List<Blog> blogs=service.allBlogList();
		
		int size=blogs.size();
		
		int total;
		
		if(size%16==0){
			total=size/16;
		}else{
			total=size/16+1;
		}
		
		Page p=new Page();
	
		p.setFirstPage(1);
		p.setCurrentPage(1);
		p.setLastPage(total);
		p.setTotalPage(total);
		List<Blog> blogList=service.allPageBloglist(p.getFirstPage(),16);
		session.setAttribute("all_size",size);
		session.setAttribute("all_page", p);
		session.setAttribute("blogList", blogList);
		
		
		//已审核博客
		List<Blog> readyBlogs=service.queryReviewBlog();
		int ready_size=readyBlogs.size();
		int ready_total;
		if(ready_size%16==0){
			ready_total=ready_size/16;
		}else{
			ready_total=ready_size/16+1;
		}
		
		Page p2=new Page();
		
		p2.setFirstPage(1);
		p2.setCurrentPage(1);
		p2.setLastPage(ready_total);
		p2.setTotalPage(ready_total);
		List<Blog> readyBlogList=service.queryReviewBlogByPage(p2.getFirstPage());
		session.setAttribute("ready_size", ready_size);
		session.setAttribute("ready_page", p2);
		session.setAttribute("ready_blogList", readyBlogList);
		
		
		//待审核博客
		List<Blog> waitBlogs=service.queryNoReviewBlog();
		int wait_size=waitBlogs.size();
		int wait_total;
		if(wait_size%16==0){
			wait_total=wait_size/16;
		}else{
			wait_total=wait_size/16+1;
		}
		
		Page p1=new Page();
		int first;
		int t=0;
		if(wait_size==t){
			first=0;
			p1.setCurrentPage(0);
		}else{
			first=1;
			p1.setCurrentPage(1);
		}
		p1.setFirstPage(first);
		p1.setLastPage(wait_total);
		p1.setTotalPage(wait_total);
		List<Blog> waitBlogList=service.queryNoReviewBlogByPage(p1.getFirstPage());
		session.setAttribute("wait_size", wait_size);
		session.setAttribute("wait_page", p1);
		session.setAttribute("wait_blogList", waitBlogList);
		
		
		
		chain.doFilter(req,resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
