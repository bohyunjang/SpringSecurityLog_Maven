package com.springsecurity.log.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.springsecurity.log.authentication.MyDBAuthenticationService;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	MyDBAuthenticationService myDbAuthenticationService;
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.inMemoryAuthentication().withUser("user111").password("test").roles("user");
		auth.inMemoryAuthentication().withUser("admin111").password("test").roles("user,admin");
		
		auth.userDetailsService(myDbAuthenticationService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		// the pages does not require login
		http.authorizeRequests().antMatchers("/", "/welcome","/login","/logout").permitAll();
		
		// /userinfo page requires login as USER or ADMIN
		// If no login, it will redirect ro /login page
		http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('role_user', 'role_admin')");

		// for ADMIN only
		http.authorizeRequests().antMatchers("/admin").access("hasRole('role_admin')");
		
		// when the user has logged int as XX..
		// But access a page the requires role YY..
		// AccessDeniedException will throw /403..
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		
		
		// config for login form.
		http.authorizeRequests().and().formLogin()
		
					// Submit URL of login page.
					.loginProcessingUrl("/jb_spring_security_check") // submit URL
					.loginPage("/login")
					.defaultSuccessUrl("/userInfo")
					.failureUrl("/login?error=true")
					.usernameParameter("username")
					.usernameParameter("password")
					// config for logout page
					.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
		
	}

}
