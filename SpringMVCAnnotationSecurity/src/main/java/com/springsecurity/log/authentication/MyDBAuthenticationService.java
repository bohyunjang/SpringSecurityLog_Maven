package com.springsecurity.log.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springsecurity.log.dao.UserInfoDAO;
import com.springsecurity.log.model.UserInfo;

@Service
public class MyDBAuthenticationService implements UserDetailsService{

	@Autowired
	private UserInfoDAO userInfoDAO;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserInfo userInfo = userInfoDAO.findUserInfo(username);
		System.out.println("UserInfo = "+userInfo);
		
		if(userInfo == null){
			throw new UsernameNotFoundException("User "+(username)+"was not found in the database");
		}

		List<String> roles = userInfoDAO.getUserRoles(username);
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		
		if(roles != null){
			for(String role: roles){
				
				GrantedAuthority authority = new SimpleGrantedAuthority("role_" + role);
				grantList.add(authority);
				
			}
		}
		
		UserDetails userDetails = (UserDetails) new User(userInfo.getUserName(),
				userInfo.getPassword(), grantList);
		
		return userDetails;
	}
	
}
