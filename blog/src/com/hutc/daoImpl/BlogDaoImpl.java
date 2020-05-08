package com.hutc.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hutc.dao.IBlogDao;
import com.hutc.entity.Blog;
import com.hutc.util.JDBCTemplate;
import com.hutc.util.JDBCTemplate.PreparedStatementSetter;
import com.hutc.util.JDBCTemplate.ResultSetCallBack;

public class BlogDaoImpl implements IBlogDao {

	@Override
	public int addBlog(final Blog blog) {
		String sql="insert into BLOG_ARTICLE values(seq_blog.nextval,?,?,?,?,'待审核')";
		int result=JDBCTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, blog.getTitle());
				pstmt.setString(2, blog.getContent());
				pstmt.setTimestamp(3, blog.getTime());
				pstmt.setLong(4, blog.getUser_id());
				
			}
		});
		return result;
	}

	@Override
	public Blog queryNewBlog(final long user_id) {
		String sql="select * from"
				+ "(select *  from Blog_ARTICLE "
				+ "order by ba_id desc)"
				+ "where user_id=? and rownum<=1";
		Blog blog=JDBCTemplate.singleQuery(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, user_id);
				
			}
		}, new ResultSetCallBack<Blog>() {

			@Override
			public Blog processRs(ResultSet rs) throws SQLException {
				Blog blog=new Blog();
				blog.setBlog_id(rs.getLong("Ba_id"));
				blog.setTitle(rs.getString("ba_title"));
				blog.setContent(rs.getString("ba_content"));
				blog.setTime(rs.getTimestamp("ba_time"));
				blog.setUser_id(user_id);
				blog.setStatus(rs.getString("ba_status"));
				return blog;
			}
		});
		return blog;
	}
	@Override
	public List<Blog> queryAllBlog() {
		String sql="select * from blog_article order by ba_id desc";
		List<Blog> blogs=JDBCTemplate.query(sql, null, new ResultSetCallBack<Blog>() {

			@Override
			public Blog processRs(ResultSet rs) throws SQLException {
				Blog blog=new Blog();
				blog.setBlog_id(rs.getLong("Ba_id"));
				blog.setTitle(rs.getString("ba_title"));
				blog.setContent(rs.getString("ba_content"));
				blog.setTime(rs.getTimestamp("ba_time"));
				blog.setUser_id(rs.getLong("user_id"));
				blog.setStatus(rs.getString("ba_status"));
				return blog;
			}
		});
		return blogs;
	}

	@Override
	public List<Blog> queryMyAllBlog(final long user_id) {
		String sql="select * from blog_article where user_id=? order by ba_id desc";
		List<Blog> blogs=JDBCTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, user_id);
				
			}
		}, new ResultSetCallBack<Blog>() {

			@Override
			public Blog processRs(ResultSet rs) throws SQLException {
				Blog blog=new Blog();
				blog.setBlog_id(rs.getLong("Ba_id"));
				blog.setTitle(rs.getString("ba_title"));
				blog.setContent(rs.getString("ba_content"));
				blog.setTime(rs.getTimestamp("ba_time"));
				blog.setUser_id(rs.getLong("user_id"));
				blog.setStatus(rs.getString("ba_status"));
				return blog;
			}
		});
		return blogs;
	}
	@Override
	public List<Blog> queryMyBlogByPage(final int page, final long user_id) {
		String sql="select * from"
				+ " (select A.*,ROWNUM rn from (select * from blog_article where user_id=? order by ba_id desc) A"
				+ " where rownum<=?)"
				+"where rn>=?";
		List<Blog> blogs=JDBCTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				
				pstmt.setLong(1, user_id);
				pstmt.setInt(2, page * 8);
				pstmt.setInt(3, (page - 1) * 8 + 1);
			}
		}, new ResultSetCallBack<Blog>() {

			@Override
			public Blog processRs(ResultSet rs) throws SQLException {
				Blog blog=new Blog();
				blog.setBlog_id(rs.getLong("Ba_id"));
				blog.setTitle(rs.getString("ba_title"));
				blog.setContent(rs.getString("ba_content"));
				blog.setTime(rs.getTimestamp("ba_time"));
				blog.setUser_id(user_id);
				blog.setStatus(rs.getString("ba_status"));
				return blog;
			}


		});
		return blogs;
	}

	@Override
	public Blog queryBlogById(final long blog_id) {
		String sql="select * from blog_article where ba_id=?";
		Blog blog=JDBCTemplate.singleQuery(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, blog_id);
				
			}
		}, new ResultSetCallBack<Blog>() {

			@Override
			public Blog processRs(ResultSet rs)throws SQLException {
				Blog blog=new Blog();
				blog.setBlog_id(rs.getLong("Ba_id"));
				blog.setTitle(rs.getString("ba_title"));
				blog.setContent(rs.getString("ba_content"));
				blog.setTime(rs.getTimestamp("ba_time"));
				blog.setUser_id(rs.getLong("user_id"));
				blog.setStatus(rs.getString("ba_status"));
				return blog;
			}
		});
		
		return blog;
				
	}

	@Override
	public List<Blog> queryAllBlogByPage(final int page, final int num) {
		String sql="select * from"
				+ " (select A.*,ROWNUM rn from (select * from blog_article  order by ba_id desc) A"
				+ " where rownum<=?)"
				+"where rn>=?";
		List<Blog> blogs=JDBCTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				
				pstmt.setInt(1, page * num);
				pstmt.setInt(2, (page - 1) * num + 1);
			}
		}, new ResultSetCallBack<Blog>() {

			@Override
			public Blog processRs(ResultSet rs) throws SQLException {
				Blog blog=new Blog();
				blog.setBlog_id(rs.getLong("Ba_id"));
				blog.setTitle(rs.getString("ba_title"));
				blog.setContent(rs.getString("ba_content"));
				blog.setTime(rs.getTimestamp("ba_time"));
				blog.setUser_id(rs.getLong("user_id"));
				blog.setStatus(rs.getString("ba_status"));
				return blog;
			}


		});
		return blogs;
	}

	@Override
	public int deleteBlog(final long blog_id) {
		String sql="delete from blog_article where ba_id=?";
		 int result=JDBCTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, blog_id);
				
			}
		});
				 
	
		return result;
	}

	@Override
	public List<Blog> queryNoReviewBlog() {
		String sql="select * from blog_article where ba_status='待审核' order by ba_id asc";
		List<Blog> blogs=JDBCTemplate.query(sql, null, new ResultSetCallBack<Blog>() {

			@Override
			public Blog processRs(ResultSet rs) throws SQLException {
				Blog blog=new Blog();
				blog.setBlog_id(rs.getLong("Ba_id"));
				blog.setTitle(rs.getString("ba_title"));
				blog.setContent(rs.getString("ba_content"));
				blog.setTime(rs.getTimestamp("ba_time"));
				blog.setUser_id(rs.getLong("user_id"));
				blog.setStatus(rs.getString("ba_status"));
				return blog;
			}
		});
		return blogs;
	}

	@Override
	public List<Blog> queryNoReviewBlogByPage(final int page) {
		String sql="select * from"
				+ " (select A.*,ROWNUM rn from (select * from blog_article where ba_status='待审核' order by ba_id asc) A"
				+ " where rownum<=?)"
				+"where rn>=?";
		List<Blog> blogs=JDBCTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				
				pstmt.setInt(1, page * 16);
				pstmt.setInt(2, (page - 1) * 16 + 1);
			}
		}, new ResultSetCallBack<Blog>() {

			@Override
			public Blog processRs(ResultSet rs) throws SQLException {
				Blog blog=new Blog();
				blog.setBlog_id(rs.getLong("Ba_id"));
				blog.setTitle(rs.getString("ba_title"));
				blog.setContent(rs.getString("ba_content"));
				blog.setTime(rs.getTimestamp("ba_time"));
				blog.setUser_id(rs.getLong("user_id"));
				blog.setStatus(rs.getString("ba_status"));
				return blog;
			}


		});
		return blogs;
	}

	@Override
	public List<Blog> queryReviewBlog() {
		String sql="select * from blog_article where ba_status!='待审核' order by ba_id desc";
		List<Blog> blogs=JDBCTemplate.query(sql, null, new ResultSetCallBack<Blog>() {

			@Override
			public Blog processRs(ResultSet rs) throws SQLException {
				Blog blog=new Blog();
				blog.setBlog_id(rs.getLong("Ba_id"));
				blog.setTitle(rs.getString("ba_title"));
				blog.setContent(rs.getString("ba_content"));
				blog.setTime(rs.getTimestamp("ba_time"));
				blog.setUser_id(rs.getLong("user_id"));
				blog.setStatus(rs.getString("ba_status"));
				return blog;
			}
		});
		return blogs;
	}

	@Override
	public List<Blog> queryReviewBlogByPage(final int page) {
		String sql="select * from"
				+ " (select A.*,ROWNUM rn from (select * from blog_article where ba_status!='待审核' order by ba_id desc) A"
				+ " where rownum<=?)"
				+"where rn>=?";
		List<Blog> blogs=JDBCTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				
				pstmt.setInt(1, page * 16);
				pstmt.setInt(2, (page - 1) * 16 + 1);
			}
		}, new ResultSetCallBack<Blog>() {

			@Override
			public Blog processRs(ResultSet rs) throws SQLException {
				Blog blog=new Blog();
				blog.setBlog_id(rs.getLong("Ba_id"));
				blog.setTitle(rs.getString("ba_title"));
				blog.setContent(rs.getString("ba_content"));
				blog.setTime(rs.getTimestamp("ba_time"));
				blog.setUser_id(rs.getLong("user_id"));
				blog.setStatus(rs.getString("ba_status"));
				return blog;
			}


		});
		return blogs;
	}

	@Override
	public int updateBlogStatus(final int blog_id,final String status) {
		String sql="update blog_article set ba_status=? where ba_id=?";
		int result=JDBCTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, status);
				pstmt.setLong(2, blog_id);
				
			}
		});
		return result;
	}

	@Override
	public int blogSort(final int blog_id, final int sort_id) {
		String sql="insert into blog_sort values(?,?)";
		int result=JDBCTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, blog_id);
				pstmt.setInt(2, sort_id);
				
			}
		});
		return result;
	}

	@Override
	public int updateBlogSort(final int blog_id, final int sort_id) {
		String sql="update blog_sort set bs_id=? where ba_id=?";
		int result=JDBCTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, sort_id);
				pstmt.setInt(2, blog_id);
				
			}
		});
		return result;
	}

	@Override
	public List<Blog> queryReviewBySort(final int sort_id) {
		String sql="select * from blog_article where ba_status='通过' and ba_id in"
				+ "(select ba_id from blog_sort where bs_id=?)"
				+ "order by ba_id desc";
		List<Blog> blogs=JDBCTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				
				pstmt.setInt(1, sort_id);
			}
		}, new ResultSetCallBack<Blog>() {

			@Override
			public Blog processRs(ResultSet rs) throws SQLException {
				Blog blog=new Blog();
				blog.setBlog_id(rs.getLong("Ba_id"));
				blog.setTitle(rs.getString("ba_title"));
				blog.setContent(rs.getString("ba_content"));
				blog.setTime(rs.getTimestamp("ba_time"));
				blog.setUser_id(rs.getLong("user_id"));
				blog.setStatus(rs.getString("ba_status"));
				return blog;
			}


		});
		return blogs;
	}

	@Override
	public List<Blog> queryReviewBySortPage(final int sort_id, final int page) {
		String sql="select * from"
				+ " (select A.*,ROWNUM rn from "
				+ "(select * from blog_article where ba_status='通过' and ba_id in"
				+ "(select ba_id from blog_sort where bs_id=?)"
				+ "order by ba_id desc)A"
				+ " where rownum<=?)"
				+"where rn>=?";
		List<Blog> blogs=JDBCTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				
				pstmt.setInt(1, sort_id);
				pstmt.setInt(2, page * 4);
				pstmt.setInt(3, (page - 1) * 4 + 1);
			}
		}, new ResultSetCallBack<Blog>() {

			@Override
			public Blog processRs(ResultSet rs) throws SQLException {
				Blog blog=new Blog();
				blog.setBlog_id(rs.getLong("Ba_id"));
				blog.setTitle(rs.getString("ba_title"));
				blog.setContent(rs.getString("ba_content"));
				blog.setTime(rs.getTimestamp("ba_time"));
				blog.setUser_id(rs.getLong("user_id"));
				blog.setStatus(rs.getString("ba_status"));
				return blog;
			}


		});
		return blogs;
	}

	@Override
	public List<Blog> queryReviewPassBlog() {
		String sql="select * from blog_article where ba_status='通过' order by ba_id desc";
		List<Blog> blogs=JDBCTemplate.query(sql, null, new ResultSetCallBack<Blog>() {

			@Override
			public Blog processRs(ResultSet rs) throws SQLException {
				Blog blog=new Blog();
				blog.setBlog_id(rs.getLong("Ba_id"));
				blog.setTitle(rs.getString("ba_title"));
				blog.setContent(rs.getString("ba_content"));
				blog.setTime(rs.getTimestamp("ba_time"));
				blog.setUser_id(rs.getLong("user_id"));
				blog.setStatus(rs.getString("ba_status"));
				return blog;
			}
		});
		return blogs;
	}

	@Override
	public List<Blog> queryReviewPassBlogByPage(final int page ,final int num) {
		String sql="select * from"
				+ " (select A.*,ROWNUM rn from "
				+ "(select * from blog_article where ba_status='通过' order by ba_id desc)A"
				+ " where rownum<=?)"
				+"where rn>=?";
		List<Blog> blogs=JDBCTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				
				pstmt.setInt(1, page * num);
				pstmt.setInt(2, (page - 1) * num + 1);
			}
		}, new ResultSetCallBack<Blog>() {

			@Override
			public Blog processRs(ResultSet rs) throws SQLException {
				Blog blog=new Blog();
				blog.setBlog_id(rs.getLong("Ba_id"));
				blog.setTitle(rs.getString("ba_title"));
				blog.setContent(rs.getString("ba_content"));
				blog.setTime(rs.getTimestamp("ba_time"));
				blog.setUser_id(rs.getLong("user_id"));
				blog.setStatus(rs.getString("ba_status"));
				return blog;
			}


		});
		return blogs;
	}

	@Override
	public String querySortName(final int blog_id) {
		String sql="select * from blog_sort_info where bs_id in "
				+ "(select bs_id from blog_sort"
				+ "where ba_id=? )";
		String sort_name=JDBCTemplate.singleQuery(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, blog_id);
				
			}
		}, new ResultSetCallBack<String>() {

			@Override
			public String processRs(ResultSet rs) throws SQLException {
				String sort_name=rs.getString("bs_name");
				System.out.print(rs.getString("bs_name"));
				return sort_name;
			}
		});
		return sort_name;
	}



}
