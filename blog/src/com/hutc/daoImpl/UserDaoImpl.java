package com.hutc.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hutc.dao.IUserDao;
import com.hutc.entity.User;
import com.hutc.util.JDBCTemplate;
import com.hutc.util.JDBCTemplate.PreparedStatementSetter;
import com.hutc.util.JDBCTemplate.ResultSetCallBack;


public class UserDaoImpl implements IUserDao {

	@Override
	public int saveUser(final User user) {
		String sql="insert into Blog_User(user_id,username,password,reg_date) values(seq_user.nextval,?,?,?)";
		int result=JDBCTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1,user.getUserName());
				pstmt.setString(2,user.getPassword());
				//时间获取
				Date today=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd");
				String dateString=sdf.format(today);
				java.sql.Date date = null;
				try {
					Date utilDate=sdf.parse(dateString);
					long t=utilDate.getTime(); //很重要，否则util不能转为sql，参考shopping注册
					date=new java.sql.Date(t);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pstmt.setDate(3,date);
			}
		});
		return result;
	}

	@Override
	public User queryUserByName(final String username) {
		
		String sql="select * from blog_user where username=?";
		User user=JDBCTemplate.singleQuery(sql, new PreparedStatementSetter() {
		
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					// TODO Auto-generated method stub
					pstmt.setString(1, username);
				}
			}, new ResultSetCallBack<User>() {
				

				@Override
				public User processRs(ResultSet rs) throws SQLException {
					User user=new User();
					user.setUserId(rs.getLong("user_id"));
					user.setUserName(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					return user;
				}
				
			
			});
	
		
			return user;
		}

	@Override
	public User login(final User users) {
		String sql="select * from blog_user where username=? and password=?";
		
		 User user=JDBCTemplate.singleQuery(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, users.getUserName());
				pstmt.setString(2, users.getPassword());
			}
		}, new ResultSetCallBack<User>() {

			@Override
			public User processRs(ResultSet rs) throws SQLException {
				User user=new User();
				user.setUserId(rs.getLong("user_id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
		return user;
	}

	@Override
	public List<User> queryAllUser() {
		String sql="select * from blog_user";
		List<User> users=JDBCTemplate.query(sql, null, new ResultSetCallBack<User>() {

			@Override
			public User processRs(ResultSet rs) throws SQLException {
				User user=new User();
				user.setUserId(rs.getLong("user_id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
		return users;
	}

	@Override
	public List<User> queryNewUser() {
		String sql="select * from blog_user where reg_date=?";
		List<User> users=JDBCTemplate.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				//时间获取
				Date today=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd");
				String dateString=sdf.format(today);
				java.sql.Date date = null;
				try {
					Date utilDate=sdf.parse(dateString);
					long t=utilDate.getTime(); //很重要，否则util不能转为sql，参考shopping注册
					date=new java.sql.Date(t);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pstmt.setDate(1,date);
				
			}
		}, new ResultSetCallBack<User>() {

			@Override
			public User processRs(ResultSet rs) throws SQLException {
				User user=new User();
				user.setUserId(rs.getLong("user_id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
		
		return users;
	}

	@Override
	public User queryUserById(final int user_id) {
		String sql="select * from blog_user where user_id=?";
		User user=JDBCTemplate.singleQuery(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, user_id);
				
			}
		}, new ResultSetCallBack<User>() {

			@Override
			public User processRs(ResultSet rs) throws SQLException {
				User user=new User();
				user.setUserId(rs.getLong("user_id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
		return user;
	}

}
