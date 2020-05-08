package com.hutc.dao;

import java.util.List;

import com.hutc.entity.User;



public interface IUserDao {

	//保存用户，注册
	public int saveUser(User user);
	
	//通过用户名查找 
	
	public User queryUserByName(String username);
	
	//登录
	public User login(User user);
	
	//查找所有用户
	public List<User> queryAllUser();

	//查找新用户
	public List<User> queryNewUser();
	
	//通过id查找
	public User queryUserById(int user_id);
}
