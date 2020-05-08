package com.hutc.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hutc.dao.INoticeDao;
import com.hutc.entity.Blog;
import com.hutc.entity.Notice;
import com.hutc.util.JDBCTemplate;
import com.hutc.util.JDBCTemplate.PreparedStatementSetter;
import com.hutc.util.JDBCTemplate.ResultSetCallBack;

public class NoticeDaoImpl implements INoticeDao {

	@Override
	public int addNotice(final Notice notice) {
		String sql="insert into BLOG_NOTICE values(seq_notice.nextval,?,?,?,?)";
		int result=JDBCTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, notice.getTitle());
				pstmt.setString(2, notice.getContent());
				pstmt.setTimestamp(3, notice.getTime());
				pstmt.setLong(4, notice.getAdmin_id());
				
			}
		});
		return result;
	}

	@Override
	public List<Notice> queryAllNotice() {
		String sql="select * from BLOG_NOTICE order by bn_id desc";
		List<Notice> notices=JDBCTemplate.query(sql, null, new ResultSetCallBack<Notice>() {

			@Override
			public Notice processRs(ResultSet rs) throws SQLException {
				Notice notice=new Notice();
				notice.setNotice_id(rs.getLong("bn_id"));
				notice.setTitle(rs.getString("bn_title"));
				notice.setContent(rs.getString("bn_content"));
				notice.setTime(rs.getTimestamp("bn_time"));
				notice.setAdmin_id(rs.getLong("admin_id"));
				return notice;
			}
		});
		return notices;
	}

	@Override
	public List<Notice> queryAllNoticeByPage(final int page) {
		String sql="select * from"
				+ " (select A.*,ROWNUM rn from (select * from blog_notice  order by bn_id desc) A"
				+ " where rownum<=?)"
				+"where rn>=?";
		List<Notice> notices=JDBCTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				
				pstmt.setInt(1, page * 16);
				pstmt.setInt(2, (page - 1) * 16 + 1);
			}
		}, new ResultSetCallBack<Notice>() {
	
			@Override
			public Notice processRs(ResultSet rs) throws SQLException {
				Notice  notice=new Notice();
				notice.setNotice_id(rs.getLong("bn_id"));
				notice.setTitle(rs.getString("bn_title"));
				notice.setContent(rs.getString("bn_content"));
				notice.setTime(rs.getTimestamp("bn_time"));
				notice.setAdmin_id(rs.getLong("admin_id"));
				return notice;
			}
	
	
		});
		return notices;
	}

	@Override
	public Notice queryNoticeById(final long notice_id) {
		String sql="select * from blog_notice where bn_id=?";
		Notice notice=JDBCTemplate.singleQuery(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, notice_id);
				
			}
		}, new ResultSetCallBack<Notice>() {

			@Override
			public Notice processRs(ResultSet rs) throws SQLException {
				Notice  notice=new Notice();
				notice.setNotice_id(rs.getLong("bn_id"));
				notice.setTitle(rs.getString("bn_title"));
				notice.setContent(rs.getString("bn_content"));
				notice.setTime(rs.getTimestamp("bn_time"));
				notice.setAdmin_id(rs.getLong("admin_id"));
				return notice;
			}
		});
		return notice;
	}

}
