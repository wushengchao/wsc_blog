package com.hutc.dao;

import java.util.List;

import com.hutc.entity.Note;

public interface INoteDao {
	//添加评论
	public int addNote(Note note);
	
	//查找评论
	public List<Note> queryAllNote(int blog_id);
}
