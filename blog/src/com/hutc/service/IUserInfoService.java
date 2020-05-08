package com.hutc.service;

import com.hutc.entity.UserInfo;

public interface IUserInfoService {
	public int updateUserInfo(UserInfo info);
	
	public UserInfo queryUserInfo(long user_id);
}
