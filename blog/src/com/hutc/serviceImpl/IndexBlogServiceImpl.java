package com.hutc.serviceImpl;

import java.util.List;

import com.hutc.dao.IIndexBlogDao;
import com.hutc.daoImpl.IndexBlogDaoImpl;
import com.hutc.entity.IndexBlog;
import com.hutc.service.IIndexBlogService;

public class IndexBlogServiceImpl implements IIndexBlogService {
	IIndexBlogDao dao = new IndexBlogDaoImpl();
	@Override
	public int addIndexBlog(IndexBlog blog) {
		int result = dao.addIndexBlog(blog);
		return result;
	}
	@Override
	public List<IndexBlog> queryAllIndexBlog() {
		List<IndexBlog> blogs=dao.queryAllIndexBlog();
		return blogs;
	}

}
