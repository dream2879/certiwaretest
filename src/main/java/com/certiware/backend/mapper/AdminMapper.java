package com.certiware.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certiware.backend.model.admin.SelectUserListModel;
import com.certiware.backend.model.common.DeptCodeModel;
import com.certiware.backend.model.common.UserModel;

public interface AdminMapper {
	
	/**
	 * TB_USER 테이블조회
	 * PASSWORD를 제외한 모든 유저 정보를 리턴 
	 * @return
	 * @throws Exception
	 */
	@Select(  "<script>"
			+ " SELECT A.USERID, A.USERNAME, A.DEPTCODE, B.DEPTNAME, A.RANKCODE, C.RANKNAME, A.ROLECODE, D.DESCRIPTION "
			+ " FROM  TB_USER A,                                                                                       "
			+ "       TB_DEPTCODE B,                                                                                   "
			+ "       TB_RANKCODE C,                                                                                   "
			+ "       TB_ROLECODE D                                                                                    "
			+ " WHERE A.DEPTCODE = B.DEPTCODE                                                                          "
			+ " AND A.RANKCODE = C.RANKCODE                                                                            "
			+ " AND A.ROLECODE = D.ROLECODE                                                                            "
			//userName
			+ "<if test=\"userName != null\"> "
			+ " AND A.USERNAME like CONCAT('%',#{userName}, '%') "
			+ "</if>"
			//deptCode
			+ "<if test=\"deptCode != null\"> "
			+ " AND A.DEPTCODE = #{deptCode} "
			+ "</if>"
			//rankCode
			+ "<if test=\"rankCode != null\"> "
			+ " AND A.RANKCODE = #{rankCode} "
			+ "</if>"			
			+ "</script>"		
			)
	public List<SelectUserListModel> selectUserList(UserModel userModel) throws Exception;
	
	/**
	 * TB_USER 테이블조회
	 * USERID에 해당하는 사용자 조회.
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Select(  "<script>"
			+ "SELECT USERID, USERNAME, DEPTCODE, RANKCODE, ROLECODE "
			+ "FROM TB_USER "
			// userId is not null이라면 WHERE조건 추가
			+ "<if test=\"userId != null\"> "
			+ "WHERE USERID =#{userId} "
			+ "</if>"
			+ "<if test=\"userName != null\"> "
			+ "WHERE USERNAME like CONCAT('%',#{userName}, '%') "
			+ "</if>"
			+ "</script>"			
			)	
	public UserModel selectUserByPK(UserModel userModel) throws Exception;
	
	/**
	 * TB_USER 테이블입력.
	 * @param userModel
	 * @throws Exception
	 */
	@Insert(  "INSERT INTO TB_USER VALUES ("
			+ "	#{userId},"
			+ " #{password},"
			+ " #{userName},"
			+ " #{deptCode},"
			+ " #{rankCode},"
			+ " #{roleCode});")
	public void insertUser(UserModel userModel) throws Exception;
	
	/**
	 * TB_USER 테이블 업데이트
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_USER "
			+ " SET USERID=#{userId},"
			+ "		PASSWORD=#{password}, "
			+ "		USERNAME=#{userName}, "
			+ "		DEPTCODE =#{deptCode}, "
			+ "		RANKCODE=#{rankCode}, "
			+ "		ROLECODE=#{roleCode} "
			+ " WHERE USERID = #{userId} "
			)
	public int updateUser(UserModel userModel) throws Exception;
	
	/**
	 * TB_USER 테이블삭제
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Delete(  "DELETE FROM TB_USER "
			+ "WHERE USERID = #{param1}")
	public int deleteUserByUserId(String userId) throws Exception;
	
	/**
	 * TB_DEPTCODE 테이블MERGE
	 * @param deptCodeModels
	 * @throws Exception
	 */
	@Insert(  " INSERT INTO TB_DEPTCODE VALUES (" //INSERT
			+ "		#{deptCode},"
			+ " 	#{deptName},"
			+ " 	#{priority}"
			+ "	) "
			+ " ON DUPLICATE KEY UPDATE " //UPDATE
			+ "		DEPTNAME=#{deptName}, "
			+ "		PRIORITY=#{priority} "
			)
	public int mergeDeptCode(List<DeptCodeModel> deptCodeModels) throws Exception;
	
	/**
	 * TB_DEPTCODE 테이블삭제 
	 * @param deptCodeModel
	 * @return
	 * @throws Exception
	 */
	@Delete(  "DELETE * FROM TB_DEPTCODE "
			+ "WHERE DEPTCODE = #{deptCode}")
	public int deleteDeptCodeByPK(List<DeptCodeModel> deptCodeModel) throws Exception;
	
	
}
