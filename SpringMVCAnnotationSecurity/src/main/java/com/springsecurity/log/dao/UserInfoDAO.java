package com.springsecurity.log.dao;

import java.util.List;

import com.springsecurity.log.model.UserInfo;

public interface UserInfoDAO {

	public UserInfo findUserInfo(String userName);
	
	// [user, admin,...]
	public List<String> getUserRoles(String userName);
	
	
}
