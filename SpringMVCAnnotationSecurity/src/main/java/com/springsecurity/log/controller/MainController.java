package com.springsecurity.log.controller;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class MainController {
	
	@RequestMapping(value={"/", "/welcome"}, method = RequestMethod.GET)
	public String welcomePage(Model model){
		model.addAttribute("title","Welcome");
		model.addAttribute("message", "This is Welcome page!!!");
		
		return "welcomPage";
	}
	
	@RequestMapping(value="/amdin", method = RequestMethod.GET)
	public String adminPage(Model model){
		System.out.println("move admin page");

		return "adminPage";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginPage(Model model){
		System.out.println("move loginPage");
		
		return "loginPage";
	}
	
	@RequestMapping(value="/logoutSuccessful", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model){
		model.addAttribute("title","Logout");
		return "logoutSuccessfulPage";
	}
	
	@RequestMapping(value="/userInfo", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal){
		String userName = principal.getName();
		System.out.println("User Name ::"+ userName);
		
		return "usrInfoPage";
	}
	
	@RequestMapping(value="/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal){
		
		if(principal != null){
			model.addAttribute("message", "HI~!!" + principal.getName()
								+ "<br> You do not have permission to access this page!!!");
		}else{
			model.addAttribute("msg",
					"You do not have permission to access this page!!");
		}
		return "403Page";
	}
}
