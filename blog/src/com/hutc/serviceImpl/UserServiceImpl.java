package com.hutc.serviceImpl;

import java.util.List;

import com.hutc.dao.IUserDao;
import com.hutc.daoImpl.UserDaoImpl;
import com.hutc.entity.User;
import com.hutc.service.IUserService;

public class UserServiceImpl implements IUserService {
	IUserDao dao=new UserDaoImpl();
	//注册
	@Override
	public int register(User user) {
		int result=dao.saveUser(user);
		return result;
	}

	@Override
	public User queryUserByName(String username) {
		User user=dao.queryUserByName(username);
		return user;
	}

	@Override
	public User login(User users) {
		User user = dao.login(users);
		return user;
	}

	@Override
	public List<User> queryAllUser() {
		List<User> users=dao.queryAllUser();
		return users;
	}

	@Override
	public List<User> queryNewUser() {
		List<User> users=dao.queryNewUser();
		return users;
	}

	@Override
	public User queryUserById(int user_id) {
		User user=dao.queryUserById(user_id);
		return user;
	}

}
