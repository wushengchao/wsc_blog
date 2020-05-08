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
import com.hutc.entity.IndexBlog;
import com.hutc.entity.Page;
import com.hutc.service.IBlogService;
import com.hutc.service.IIndexBlogService;
import com.hutc.serviceImpl.BlogServiceImpl;
import com.hutc.serviceImpl.IndexBlogServiceImpl;

public final class IndexAdminFilter implements Filter {

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
		
		//选择列表
		List<Blog> blogs=service.queryReviewPassBlog();
		
		int size=blogs.size();
		//页码设置
		int total;
		if(size%4==0){
			total=size/16;
		}else{
			total=size/16+1;
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
		List<Blog> blogList=service.queryReviewPassBlogByPage(1,16);
		session.setAttribute("pass_page", page);
		req.setAttribute("pass_blogList", blogList);
		
		//当前首页列表
		IIndexBlogService index_service=new IndexBlogServiceImpl();
		
		List<IndexBlog> index_blogs=index_service.queryAllIndexBlog();
		req.setAttribute("index_blogs", index_blogs);
		
		chain.doFilter(req,resp);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
