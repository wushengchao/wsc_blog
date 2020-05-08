package com.hutc.serviceImpl;

import com.hutc.dao.IUserInfoDao;
import com.hutc.daoImpl.UserInfoDaoImpl;
import com.hutc.entity.UserInfo;
import com.hutc.service.IUserInfoService;

public class UserInfoServiceImpl implements IUserInfoService {
	IUserInfoDao dao=new UserInfoDaoImpl();
	@Override
	public int updateUserInfo(UserInfo info) {
		int result=dao.updateUserInfo(info);
		return result;
	}
	@Override
	public UserInfo queryUserInfo(long user_id) {
		UserInfo info=dao.queryUserInfo(user_id);
		return info;
	}

}
