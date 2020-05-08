package com.hutc.dao;

import java.util.List;

import com.hutc.entity.IndexBlog;

public interface IIndexBlogDao {
	//添加内容
	public int addIndexBlog(IndexBlog blog);
	
	//查找所有
	public List<IndexBlog> queryAllIndexBlog();
}
