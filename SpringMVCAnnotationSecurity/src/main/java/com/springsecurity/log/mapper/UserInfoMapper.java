package com.springsecurity.log.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springsecurity.log.model.UserInfo;

public class UserInfoMapper implements RowMapper<UserInfo> {

	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		String userName = rs.getString("Usernmae");
		String password = rs.getString("Password");
		
		return new UserInfo(userName, password);
	}
}
