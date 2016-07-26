package com.certiware.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certiware.backend.model.admin.SelectUserListModel;
import com.certiware.backend.model.admin.UpdateUserModel;
import com.certiware.backend.model.admin.DeleteDeptCodeModel;
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
			+ " SELECT A.USERID, A.USERNAME, A.EMAIL, A.PHONENUMBER, A.DEPTCODE, B.DEPTNAME, A.RANKCODE, C.RANKNAME, A.ROLECODE, D.DESCRIPTION "
			+ " FROM  TB_USER A,                                                                                       "
			+ "       TB_DEPTCODE B,                                                                                   "
			+ "       TB_RANKCODE C,                                                                                   "
			+ "       TB_ROLECODE D                                                                                    "
			+ " WHERE A.DEPTCODE = B.DEPTCODE                                                                          "
			+ " AND A.RANKCODE = C.RANKCODE                                                                            "
			+ " AND A.ROLECODE = D.ROLECODE                                                                            "
			//userName
			+ "<if test=\"userName != null and userName != '' \"> "
			+ " AND A.USERNAME LIKE CONCAT('%',#{userName}, '%') "
			+ "</if>"
			//deptName
			+ "<if test=\"deptName != null and deptName != '' \"> "
			+ " AND B.DEPTNAME LIKE CONCAT('%',#{deptName}, '%') "
			+ "</if>"
			//rankName
			+ "<if test=\"rankName != null and rankName != '' \"> "
			+ " AND C.RANKNAME LIKE CONCAT('%',#{rankName}, '%') "
			+ "</if>"			
			+ "</script>"		
			)
	public List<SelectUserListModel> selectUserList(SelectUserListModel selectUserListModel) throws Exception;
	
	/**
	 * TB_USER 테이블조회
	 * USERID에 해당하는 사용자 조회.
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Select(  "<script>"
			+ "SELECT USERID, USERNAME, EMAIL, PHONENUMBER, DEPTCODE, RANKCODE, ROLECODE "
			+ "FROM TB_USER "
			// userId is not null이라면 WHERE조건 추가
			+ "<if test=\"userId != null and userId != ''\"> "
			+ "WHERE USERID =#{userId} "
			+ "</if>"
			+ "<if test=\"userName != null and userName != ''\"> "
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
	@Insert(  "INSERT INTO TB_USER VALUES "
			+ "("
			+ "	#{userId},"
			+ " #{password},"
			+ " #{userName},"
			+ " #{email},"
			+ " #{phoneNumber},"
			+ " #{deptCode},"
			+ " #{rankCode},"
			+ " #{roleCode}"
			+ ")"
			)
	public void insertUser(UserModel userModel) throws Exception;
	
	/**
	 * TB_USER 테이블 업데이트
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	@Update(  "<script>"
			+ " UPDATE TB_USER "
			+ " SET USERID=#{userId},"
			+ "<if test=\"password != null and password != ''\"> "
			+ "		PASSWORD=#{password}, "
			+ "</if>"
			+ "		USERNAME=#{userName}, "
			+ "		EMAIL=#{email}, "
			+ "		PHONENUMBER=#{phoneNumber}, "
			+ "		DEPTCODE =#{deptCode}, "
			+ "		RANKCODE=#{rankCode}, "
			+ "		ROLECODE=#{roleCode} "
			+ " WHERE USERID = #{pk} "
			+ "</script>"			
			)
	public int updateUser(UpdateUserModel updateUserModel) throws Exception;
	
	/**
	 * TB_USER 테이블삭제
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Delete(  "DELETE FROM TB_USER "
			+ "WHERE USERID = #{param1}")
	public int deleteUserByUserId(String userId) throws Exception;
	
//	/**
//	 * TB_DEPTCODE 테이블MERGE
//	 * @param deptCodeModels
//	 * @throws Exception
//	 */
//	@Insert(  " INSERT INTO TB_DEPTCODE VALUES (" //INSERT
//			+ "		#{deptCode},"
//			+ " 	#{deptName},"
//			+ " 	#{priority}"
//			+ "	) "
//			+ " ON DUPLICATE KEY UPDATE " //UPDATE
//			+ "		DEPTNAME=#{deptName}, "
//			+ "		PRIORITY=#{priority} "
//			)
//	public int mergeDeptCode(DeptCodeModel deptCodeModel) throws Exception;
//	
	
	/**
	 * TB_DEPTCODE 테이블 INSERT
	 * @param deptName
	 * @throws Exception
	 */
	@Insert(  " INSERT INTO TB_DEPTCODE "
			+ " VALUES "
			+ " ("
			+ " (SELECT MAX(CAST(DEPTCODE AS UNSIGNED)) + 1 FROM TB_DEPTCODE AS TEMP1), " // 본부코드 임의생성
			+ " #{param1}, "
			+ " (SELECT MAX(PRIORITY) + 1 FROM TB_DEPTCODE AS TEMP)" // 우선순위 최하위로 설정
			+ ") "
			)
	public void insertDeptCode(String deptName) throws Exception;	
	
	
	/**
	 * TB_DEPTCODE 테이블 UPDATE
	 * @param deptName
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_DEPTCODE 				"
			+ "   SET DEPTNAME = #{deptName},  	"
			+ "   PRIORITY = #{priority}        "
			+ " WHERE DEPTCODE = #{deptCode}   	"
			)
	public void updateDeptCode(DeptCodeModel deptCodeModel) throws Exception;
	
	
	/**
	 * TB_DEPTCODE 테이블삭제 
	 * @param deptCodeModel
	 * @return
	 * @throws Exception
	 */
	@Delete(  "DELETE FROM TB_DEPTCODE "
			+ "WHERE DEPTCODE = #{deptCode}")
	public void deleteDeptCodeByPK(DeleteDeptCodeModel deleteDeptCodeModel) throws Exception;	
	
	
	/**
	 * TB_USER 테이블의 부서명을 변경한다.
	 * TB_DEPT 테입테블의 부서를 삭제하기전 선수작업
	 * @param deleteDeptCodeModel
	 * @throws Exception
	 */
	@Update(  "	UPDATE TB_USER "
			+ "		SET DEPTCODE = #{updateDeptCode} "
			+ " WHERE DEPTCODE = #{deptCode}")
	public void updateUserDeptCode(DeleteDeptCodeModel deleteDeptCodeModel) throws Exception;
	
	
	/**
	 * TB_PROJECT 테이블의 부서명을 변경한다.
	 * TB_DEPT 테입테블의 부서를 삭제하기전 선수작업
	 * @param deleteDeptCodeModel
	 * @throws Exception
	 */
	@Update(  "	UPDATE TB_PROJECT "
			+ "		SET DEPTCODE = #{updateDeptCode} "
			+ "	WHERE DEPTCODE = #{deptCode}")
	public void updateProjectDeptCode(DeleteDeptCodeModel deleteDeptCodeModel) throws Exception;
	
	
	
}
