package com.hutc.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hutc.dao.INoteDao;
import com.hutc.entity.Note;
import com.hutc.entity.User;
import com.hutc.service.IUserService;
import com.hutc.serviceImpl.UserServiceImpl;
import com.hutc.util.JDBCTemplate;
import com.hutc.util.JDBCTemplate.PreparedStatementSetter;
import com.hutc.util.JDBCTemplate.ResultSetCallBack;

public class NoteDaoImpl implements INoteDao {

	@Override
	public int addNote(final Note note) {
		String sql="insert into blog_note values(seq_note.nextval,?,?,?,?)";
		int result=JDBCTemplate.update(sql,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				
				pstmt.setString(1, note.getNote_content());
				pstmt.setTimestamp(2, note.getNote_time());
				pstmt.setLong(3, note.getUser_id());
				pstmt.setLong(4, note.getBa_id());
				
			}
		});
		return result;
	}

	@Override
	public List<Note> queryAllNote(final int blog_id) {
		String sql="select * from blog_note where ba_id=? order by note_id desc";
		List<Note> notes = JDBCTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, blog_id);
				
			}
		}, new ResultSetCallBack<Note>() {

			@Override
			public Note processRs(ResultSet rs) throws SQLException {
				Note note=new Note();
				note.setNote_id(rs.getLong("note_id"));
				note.setNote_content(rs.getString("note_content"));
				note.setNote_time(rs.getTimestamp("note_time"));
				note.setUser_id(rs.getLong("user_id"));
				note.setBa_id(rs.getLong("ba_id"));
				//获取用户名
				IUserService service = new UserServiceImpl();
				User user=service.queryUserById(rs.getInt("user_id"));
				note.setUser_name(user.getUserName());
				return note;
			}
		});
			return notes;
		}
	}


