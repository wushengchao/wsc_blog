package com.hutc.daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hutc.dao.IAdminDao;
import com.hutc.entity.Admin;
import com.hutc.util.JDBCTemplate;
import com.hutc.util.JDBCTemplate.PreparedStatementSetter;
import com.hutc.util.JDBCTemplate.ResultSetCallBack;

public class AdminDaoImpl implements IAdminDao {

	@Override
	public Admin queryAdmin(final Admin admin) {
		String sql="select * from Blog_admin where admin_name=? and admin_password=?";
		
		Admin admin_login=JDBCTemplate.singleQuery(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, admin.getAdmin_name());
				pstmt.setString(2, admin.getAdmin_password());
				
			}
		}, new ResultSetCallBack<Admin>() {

			@Override
			public Admin processRs(ResultSet rs) throws SQLException {
				Admin admin_login=new Admin();
				admin_login.setAdmin_id(rs.getLong("admin_id"));
				admin_login.setAdmin_name(rs.getString("admin_name"));
				admin_login.setAdmin_password(rs.getString("admin_password"));
				return admin_login;
			}
		});
		return admin_login;
	}

}
