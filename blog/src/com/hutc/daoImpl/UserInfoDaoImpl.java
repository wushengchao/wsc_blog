package com.hutc.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hutc.dao.IUserInfoDao;
import com.hutc.entity.UserInfo;
import com.hutc.util.JDBCTemplate;
import com.hutc.util.JDBCTemplate.PreparedStatementSetter;
import com.hutc.util.JDBCTemplate.ResultSetCallBack;

public class UserInfoDaoImpl implements IUserInfoDao {

	@Override
	public int updateUserInfo(final UserInfo info) {
		String sql="update blog_userinfo "
				+ "set real_name=?,sex=?,institute=?,"
				+ "birthday=?,email=?,contact=?,introduce=?"
				+ "where user_id=?";
		int result=JDBCTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, info.getReal_name());
				pstmt.setString(2, info.getSex());
				pstmt.setString(3, info.getInstitute());
				pstmt.setDate(4, info.getBirthday());
				pstmt.setString(5,info.getEmail());
				pstmt.setString(6,info.getContact());
				pstmt.setString(7,info.getIntroduce());
				pstmt.setLong(8, info.getUserId());
			}
		});
		return result;
	}

	@Override
	public UserInfo queryUserInfo(final long user_id) {
		String sql="select * from blog_userinfo where user_id=?";
		UserInfo info=JDBCTemplate.singleQuery(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, user_id);
				
			}
		}, new ResultSetCallBack<UserInfo>() {

			@Override
			public UserInfo processRs(ResultSet rs) throws SQLException {
				UserInfo info=new UserInfo();
				info.setUserId(rs.getLong(1));
				info.setReal_name(rs.getString(2));
				info.setSex(rs.getString(3));
				info.setInstitute(rs.getString(4));
				info.setBirthday(rs.getDate(5));
				info.setEmail(rs.getString(6));
				info.setContact(rs.getString(7));
				info.setIntroduce(rs.getString(8));
				return info;
			}
		});
		return info;
	}

}
