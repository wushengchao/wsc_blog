package com.hutc.service;

import java.util.List;

import com.hutc.entity.IndexBlog;

public interface IIndexBlogService {
	//添加内容
	public int addIndexBlog(IndexBlog blog);	
	
	//查找所有
	public List<IndexBlog> queryAllIndexBlog();
}
