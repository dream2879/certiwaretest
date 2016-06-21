package com.certiware.backend.mapper;

import org.apache.ibatis.annotations.Select;

import com.certiware.backend.model.common.User;
import com.certiware.backend.model.user.UserLoginModel;

public interface UserMapper {
	
	/**
	 * 로그인한다.
	 * String으로 받을경우 param1, param2 ... or 0, 1 ... 으로 매핑
	 * @throws Exception
	 */
	@Select	(  
				" SELECT * "
			+ 	" FROM TB_USER "
			+ 	" WHERE USERID = #{param1} "
			+ 	" AND PASSWORD = #{param2} "
			)
	public UserLoginModel login(String userId, String passWord) throws Exception;
	

}
