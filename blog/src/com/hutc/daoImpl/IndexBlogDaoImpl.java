package com.hutc.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hutc.dao.IIndexBlogDao;
import com.hutc.entity.IndexBlog;
import com.hutc.util.JDBCTemplate;
import com.hutc.util.JDBCTemplate.PreparedStatementSetter;
import com.hutc.util.JDBCTemplate.ResultSetCallBack;

public class IndexBlogDaoImpl implements IIndexBlogDao {

	@Override
	public int addIndexBlog(final IndexBlog blog) {
		String sql="insert into blog_index values(seq_note.nextval,?,?,?)";
		int result=JDBCTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, blog.getBlog_id());
				pstmt.setString(2, blog.getTitle());
				pstmt.setString(3, blog.getDescribe());
				
			}
		});
		return result;
	}

	@Override
	public List<IndexBlog> queryAllIndexBlog() {
		String sql="select * from blog_index order by bi_id desc";
		List<IndexBlog> blogs=JDBCTemplate.query(sql, null, new ResultSetCallBack<IndexBlog>() {

			@Override
			public IndexBlog processRs(ResultSet rs) throws SQLException {
				IndexBlog blog=new IndexBlog();
				blog.setBi_id(rs.getLong("bi_id"));
				blog.setBlog_id(rs.getLong("ba_id"));
				blog.setTitle(rs.getString("bi_title"));
				blog.setDescribe(rs.getString("bi_describe"));
				return blog;
			}
		
		
		});
		return blogs;
	}

}
