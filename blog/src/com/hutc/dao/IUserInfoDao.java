package com.hutc.dao;

import com.hutc.entity.UserInfo;

public interface IUserInfoDao {
	//更新用户信息
	public int updateUserInfo(UserInfo info);
	//查询用户信息
	public UserInfo queryUserInfo(long user_id);
}
