package com.hutc.serviceImpl;

import java.util.List;

import com.hutc.dao.INoteDao;
import com.hutc.daoImpl.NoteDaoImpl;
import com.hutc.entity.Note;
import com.hutc.service.INoteService;

public class NoteServiceImpl implements INoteService {
	
	INoteDao dao = new NoteDaoImpl();
	
	@Override
	public int addNote(Note note) {
		int result = dao.addNote(note);
		return result;
	}

	@Override
	public List<Note> queryAllNote(int blog_id) {
		List<Note> notes = dao.queryAllNote(blog_id);
		return notes;
	}

}
