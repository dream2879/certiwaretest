package com.certiware.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.certiware.backend.model.common.UserModel;
import com.certiware.backend.model.main.SelectMenuModel;

public interface MainMapper {
	
	/**
	 * 로그인한다.
	 * String으로 받을경우 param1, param2 ... or 0, 1 ... 으로 매핑
	 * @throws Exception
	 */
	@Select	(  
				" SELECT * "
			+ 	" FROM TB_USER "
			+ 	" WHERE USERID = #{param1} "			
			)
	public UserModel selectUserByPK(String userId) throws Exception;
	
	
	/**
	 * TB_MENU 테이블조회
	 * ROLECODE에 해당되는 코드만 조회
	 * @param roleCode
	 * @return
	 * @throws Exception
	 */
	@Select(  " SELECT *                       "
			+ " FROM TB_MENU                   "
			+ " WHERE ROLECODE <= #{param1}    "
			+ " ORDER BY MAINMENUID, SUBMENUID " )
	public List<SelectMenuModel> selectMenuByRoleCode(String roleCode) throws Exception;
	

}
