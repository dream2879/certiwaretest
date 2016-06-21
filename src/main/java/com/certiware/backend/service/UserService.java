package com.certiware.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certiware.backend.mapper.UserMapper;
import com.certiware.backend.model.common.User;
import com.certiware.backend.model.user.UserLoginModel;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public UserLoginModel login(String userId, String passWord) throws Exception {	
		return userMapper.login(userId, passWord);
		
	}

}
