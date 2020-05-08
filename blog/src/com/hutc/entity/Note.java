package com.hutc.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Note implements Serializable {
	private long note_id;
	private String note_content;
	private Timestamp note_time;
	private long user_id;
	private long ba_id;
	private String user_name;
	public Note() {
		super();
	}
	public Note(String note_content, Timestamp note_time, long user_id,
			long ba_id) {
		super();
		this.note_content = note_content;
		this.note_time = note_time;
		this.user_id = user_id;
		this.ba_id = ba_id;
	}
	@Override
	public String toString() {
		return "Note [note_id=" + note_id + ", note_content=" + note_content
				+ ", note_time=" + note_time + ", user_id=" + user_id
				+ ", ba_id=" + ba_id + "]";
	}
	public long getNote_id() {
		return note_id;
	}
	public void setNote_id(long note_id) {
		this.note_id = note_id;
	}
	public String getNote_content() {
		return note_content;
	}
	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}
	public Timestamp getNote_time() {
		return note_time;
	}
	public void setNote_time(Timestamp note_time) {
		this.note_time = note_time;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public long getBa_id() {
		return ba_id;
	}
	public void setBa_id(long ba_id) {
		this.ba_id = ba_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
}
