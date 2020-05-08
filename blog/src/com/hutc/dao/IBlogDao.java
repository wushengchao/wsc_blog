package com.hutc.dao;

import java.util.List;

import com.hutc.entity.Blog;

public interface IBlogDao {
	//添加博文
	public int addBlog(Blog blog);
	
	//查找刚发布博文
	public Blog queryNewBlog(long user_id);
	
	//查找所有博客
	public List<Blog> queryAllBlog();
	
	//查找个人所有博客
	public List<Blog> queryMyAllBlog(long user_id);
	
	//个人博客分页
	public List<Blog> queryMyBlogByPage(int page,long user_id);
	
	//通过id查找博客
	public Blog queryBlogById(long blog_id);
	
	//所有博客分页
	
	public List<Blog> queryAllBlogByPage(int page,int num);
	
	//删除博客
	public int deleteBlog(long blog_id);
	
	//查找待审核博客
	public  List<Blog> queryNoReviewBlog();
	
	//分页查找待审核博客
	public  List<Blog> queryNoReviewBlogByPage(int page);
	
	//查找已审核博客
	public  List<Blog> queryReviewBlog();
	
	//分页查找已审核博客
	public  List<Blog> queryReviewBlogByPage(int page);
	
	//更改状态
	public int updateBlogStatus(int blog_id,String status);
	
	//博客分类
	public int blogSort(int blog_id,int sort_id);
	
	//修改博客分类
	public int updateBlogSort(int blog_id,int sort_id);
	
	//查找类名
	public String querySortName(int blog_id);
	
	//博客分类查询已审核博客
	public List<Blog> queryReviewBySort(int sort_id);
	
	//博客分类分页查询已审核博客
	public List<Blog> queryReviewBySortPage(int sort_id,int page);
	
	//查找所有审核通过文章
	public  List<Blog> queryReviewPassBlog();
	
	//分页查找所有审核通过文章
	public  List<Blog> queryReviewPassBlogByPage(int page,int num);
}
