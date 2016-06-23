package com.certiware.backend.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.main.LoginModel;
import com.certiware.backend.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/common")
public class mainController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public LoginModel UserLogin(@RequestBody Map<String, String> json) throws ServletException {
		LoginModel userLoginModel = new LoginModel();
		String userName = null;
		String password;
		try{
			System.out.println("UserLoginModel() 호출!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			/*
			if (json.get("username")==null || json.get("password") == null) {
				throw new ServletException ("Please fill in username and password");
			}
			*/
			userName =json.get("username");
			password = json.get("password");
			
			//userLoginModel = userService.login(userName, password);
			userLoginModel = userService.login("test11", "1111");
			
			if (userLoginModel == null) {
				throw new ServletException ("User name not found.");
			}
			/*
			String pwd = userLoginModel.getPassword();
			
			if (!password.equals(pwd)) {
				throw new ServletException("Invalid login. Please check your name and password.");
			}
			*/
			
			userLoginModel.setToken(Jwts.builder().setSubject(userName).claim("roles", "user").setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact());
			
		}catch(Exception e)
		{
			System.out.println("error!!! :" + e.toString());
			throw new ServletException(e.toString());
		}
		
		return userLoginModel;		
	}

}
