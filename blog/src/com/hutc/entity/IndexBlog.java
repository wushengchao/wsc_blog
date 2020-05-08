package com.hutc.entity;

import java.io.Serializable;

public class IndexBlog implements Serializable {
	private long bi_id;
	private long blog_id;
	private String title;
	private String describe;
	public IndexBlog() {
		super();
	}
	public IndexBlog(long blog_id, String title, String describe) {
		super();
		this.blog_id = blog_id;
		this.title = title;
		this.describe = describe;
	}
	@Override
	public String toString() {
		return "IndexBlog [bi_id=" + bi_id + ", blog_id=" + blog_id
				+ ", title=" + title + ", describe=" + describe + "]";
	}
	public long getBi_id() {
		return bi_id;
	}
	public void setBi_id(long bi_id) {
		this.bi_id = bi_id;
	}
	public long getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(long blog_id) {
		this.blog_id = blog_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
}
