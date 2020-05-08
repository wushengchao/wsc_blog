package com.hutc.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Notice implements Serializable {
	private long notice_id;
	private String title;
	private String content;
	private Timestamp time;
	private long admin_id;
	
	public Notice() {
		super();
	}

	public Notice(String title, String content, Timestamp time, long admin_id) {
		super();
		this.title = title;
		this.content = content;
		this.time = time;
		this.admin_id = admin_id;
	}

	@Override
	public String toString() {
		return "Notice [notice_id=" + notice_id + ", title=" + title
				+ ", content=" + content + ", time=" + time + ", admin_id="
				+ admin_id + "]";
	}

	public long getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(long notice_id) {
		this.notice_id = notice_id;
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

	public long getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(long admin_id) {
		this.admin_id = admin_id;
	}
	
	
	
}
