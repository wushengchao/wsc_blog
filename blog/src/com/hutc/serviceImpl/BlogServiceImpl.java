package com.hutc.serviceImpl;

import java.util.List;

import com.hutc.dao.IBlogDao;
import com.hutc.daoImpl.BlogDaoImpl;
import com.hutc.entity.Blog;
import com.hutc.service.IBlogService;

public class BlogServiceImpl implements IBlogService {
	
	IBlogDao dao=new BlogDaoImpl();
	@Override
	public int addBlog(Blog blog) {
		int result=dao.addBlog(blog);
		return result;
	}
	@Override
	public Blog queryNewBlog(long user_id) {
		Blog blog=dao.queryNewBlog(user_id);
		return blog;
	}
	
	@Override
	public List<Blog> allBlogList() {
		List<Blog> blogs=dao.queryAllBlog();
		return blogs;
	}
	@Override
	public List<Blog> myAllBlogList(long user_id) {
		List<Blog> blogs=dao.queryMyAllBlog(user_id);
		return blogs;
	}
	@Override
	public List<Blog> myPageBloglist(int page, long user_id) {
		List<Blog> blogs=dao.queryMyBlogByPage(page, user_id);
		return blogs;
	}
	@Override
	public Blog queryBlogById(long blog_id) {
		Blog blog=dao.queryBlogById(blog_id);
		return blog;
	}
	@Override
	public List<Blog> allPageBloglist(int page, int num) {
		List<Blog> blogs=dao.queryAllBlogByPage(page,num);
		return blogs;
	}
	@Override
	public int deleteBlog(long blog_id) {
		int result=dao.deleteBlog(blog_id);
		return result;
	}
	@Override
	public List<Blog> queryNoReviewBlog() {
		List<Blog> blogs=dao.queryNoReviewBlog();
		return blogs;
	}
	@Override
	public List<Blog> queryNoReviewBlogByPage(int page) {
		List<Blog> blogs=dao.queryNoReviewBlogByPage(page);
		return blogs;
	}
	@Override
	public List<Blog> queryReviewBlog() {
		List<Blog> blogs=dao.queryReviewBlog();
		return blogs;
	}
	@Override
	public List<Blog> queryReviewBlogByPage(int page) {
		List<Blog> blogs=dao.queryReviewBlogByPage(page);
		return blogs;
	}
	@Override
	public int updateBlogStatus(int blog_id, String status) {
		int result=dao.updateBlogStatus(blog_id, status);
		return result;
	}
	@Override
	public int blogSort(int blog_id, int sort_id) {
		int result=dao.blogSort(blog_id, sort_id);
		return result;
	}
	@Override
	public int updateBlogSort(int blog_id, int sort_id) {
		int result=dao.blogSort(blog_id, sort_id);
		return result;
	}
	@Override
	public List<Blog> queryReviewBySort(int sort_id) {
		List<Blog> blogs=dao.queryReviewBySort(sort_id);
		return blogs;
	}
	@Override
	public List<Blog> queryReviewBySortPage(int sort_id, int page) {
		List<Blog> blogs=dao.queryReviewBySortPage(sort_id,page);
		return blogs;
	}
	@Override
	public List<Blog> queryReviewPassBlog() {
		List<Blog> blogs=dao.queryReviewPassBlog();
		return blogs;
	}
	@Override
	public List<Blog> queryReviewPassBlogByPage(int page,int num) {
		List<Blog> blogs=dao.queryReviewPassBlogByPage(page,num);
		return blogs;
	}
	@Override
	public String querySortName(int blog_id) {
		String sort_name=dao.querySortName(blog_id);
		return sort_name;
	}


}
