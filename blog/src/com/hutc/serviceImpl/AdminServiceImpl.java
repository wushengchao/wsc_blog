package com.hutc.serviceImpl;

import com.hutc.dao.IAdminDao;
import com.hutc.daoImpl.AdminDaoImpl;
import com.hutc.entity.Admin;
import com.hutc.service.IAdminService;

public class AdminServiceImpl implements IAdminService{

	@Override
	public Admin admin_login(Admin admin) {
		IAdminDao dao=new AdminDaoImpl();
		Admin admin_login= dao.queryAdmin(admin);
		return admin_login;
	}

}
