package com.hutc.service;

import java.util.List;

import com.hutc.entity.User;



public interface IUserService {
	//注册
	abstract int register(User user);
	
	//查找用户名
	User queryUserByName(String username);
	
	//登录
	User login(User User);
	
	//查找所有用户
	public List<User> queryAllUser();
	
	//查找新用户
	public List<User> queryNewUser();
	
	//通过id查找
	public User queryUserById(int user_id);
}
