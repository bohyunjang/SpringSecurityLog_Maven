package com.springsecurity.log.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsecurity.log.dao.UserInfoDAO;
import com.springsecurity.log.mapper.UserInfoMapper;
import com.springsecurity.log.model.UserInfo;


@Service
@Transactional
public class UserInfoDAOImpl extends JdbcDaoSupport implements UserInfoDAO{
	
	@Autowired
	public UserInfoDAOImpl(DataSource dataSource){
		this.setDataSource(dataSource);
	}
	
	public UserInfo findUserInfo(String userName) {
		// TODO Auto-generated method stub
		System.out.println("UserInfo findUserInfo");
		
		String sql = "select u.Username, u.Password"//
				+ " from Users u where u.Username =?";
		
		Object[] params = new Object[] {userName};
		UserInfoMapper mapper = new UserInfoMapper();
		try{
			 UserInfo userInfo = (UserInfo) this.getJdbcTemplate().queryForMap(sql, params, mapper);
			return userInfo;
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	
	public List<String> getUserRoles(String userName) {
		// TODO Auto-generated method stub
		System.out.println("getUserRoles");
		
		String sql = "Select r.User_Role "//
				+ " from User_Roles r where r.Username = ?";
		
		Object[] params = new Object[] {userName};
		
		List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);
		
		return roles;
	}
	
}
