package com.certiware.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certiware.backend.model.common.DeptCodeModel;
import com.certiware.backend.model.common.UserModel;

public interface AdminMapper {
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT USERID, USERNAME, DEPTCODE, RANKCODE, ROLECODE  FROM TB_USER")
	public List<UserModel> selectUserList() throws Exception;
	
	/**
	 * 	
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT USERID, USERNAME, DEPTCODE, RANKCODE, ROLECODE FROM TB_USER WHERE USERID =#{param1}")
	public UserModel selectUserByUserId(String userId) throws Exception;
	
	/**
	 * 
	 * @param userModel
	 * @throws Exception
	 */
	@Insert("INSERT INTO TB_USER VALUES (#{userId}, #{password}, #{userName}, #{deptCode}, #{rankCode}, #{roleCode});")
	public void insertUser(UserModel userModel) throws Exception;
	
	/**
	 * 
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_USER "
			+ " SET USERID=#{userId}, PASSWORD=#{password}, USERNAME=#{userName}, DEPTCODE =#{deptCode}, RANKCODE=#{rankCode}, ROLECODE=#{roleCode} "
			+ " WHERE USERID = #{userId} "
			)
	public int updateUser(UserModel userModel) throws Exception;
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Delete("DELETE FROM TB_USER WHERE USERID = #{param1}")
	public int deleteUserByUserId(String userId) throws Exception;
	
	/**
	 * 
	 * @param deptCodeModels
	 * @throws Exception
	 */
	@Insert(  " INSERT INTO TB_DEPTCODE VALUES (#{deptCode}, #{deptName}, #{priority}) "
			+ " ON DUPLICATE KEY UPDATE DEPTNAME=#{deptName}, PRIORITY=#{priority} "
			)
	public int mergeDeptCode(List<DeptCodeModel> deptCodeModels) throws Exception;
	
	/**
	 * 
	 * @param deptCodeModel
	 * @return
	 * @throws Exception
	 */
	@Delete("DELETE * FROM TB_DEPTCODE WHERE DEPTCODE = #{deptCode}")
	public int deleteDeptCodeByDeptCode(List<DeptCodeModel> deptCodeModel) throws Exception;
	
	
}
