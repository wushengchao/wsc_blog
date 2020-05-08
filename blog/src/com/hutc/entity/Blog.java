package com.hutc.entity;

import java.io.Serializable;
import java.sql.Timestamp;


public class Blog implements Serializable {
	private long blog_id;
	private String title;
	private String content;
	private Timestamp time;
	private long user_id;
	private String status;
	@Override
	public String toString() {
		return "Blog [blog_id=" + blog_id + ", title=" + title + ", content="
				+ content + ", time=" + time + ", user_id=" + user_id + "]";
	}
	public Blog() {
		super();
	}
	public Blog(String title, String content, Timestamp time, long user_id) {
		super();
		this.title = title;
		this.content = content;
		this.time = time;
		this.user_id = user_id;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
