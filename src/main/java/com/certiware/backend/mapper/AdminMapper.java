package com.certiware.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certiware.backend.model.admin.CodeModel;
import com.certiware.backend.model.admin.DeleteDeptCodeModel;
import com.certiware.backend.model.admin.SelectUserListModel;
import com.certiware.backend.model.admin.UpdateUserModel;
import com.certiware.backend.model.common.DeptCodeModel;
import com.certiware.backend.model.common.UserModel;

public interface AdminMapper {
	
	
	/******************************************** TB_USER ****************************************************/
	
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
	
	
	/******************************************** TB_DEPTCODE ****************************************************/
	
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
	 * TB_USER 테이블의 부서코드를 변경한다.
	 * TB_DEPT 테입테블의 부서를 삭제하기전 선수작업
	 * @param deleteDeptCodeModel
	 * @throws Exception
	 */
	@Update(  "	UPDATE TB_USER "
			+ "		SET DEPTCODE = #{updateDeptCode} "
			+ " WHERE DEPTCODE = #{deptCode}")
	public void updateUserDeptCode(DeleteDeptCodeModel deleteDeptCodeModel) throws Exception;
	
	
	/**
	 * TB_PROJECT 테이블의 부서코드를 변경한다.
	 * TB_DEPT 테입테블의 부서를 삭제하기전 선수작업
	 * @param deleteDeptCodeModel
	 * @throws Exception
	 */
	@Update(  "	UPDATE TB_PROJECT "
			+ "		SET DEPTCODE = #{updateDeptCode} "
			+ "	WHERE DEPTCODE = #{deptCode}")
	public void updateProjectDeptCode(DeleteDeptCodeModel deleteDeptCodeModel) throws Exception;
	
	
	
	/******************************************** TB_RANKCODE ****************************************************/
	
	/**
	 * TB_RANKCODE 테이블 입력
	 * @param codeModel
	 * @throws Exception
	 */
	@Insert(  " INSERT INTO TB_RANKCODE VALUES                                                   						"
			+ " (                                                                                           "
			+ "   (SELECT MAX(CAST(RANKCODE AS UNSIGNED)) + 1 FROM TB_RANKCODE AS TEMP1),                   "
			+ "   #{name},                                                                                  "
			+ "   (SELECT MAX(RANKCODE) + 1 FROM TB_RANKCODE AS TEMP)                                       "
			+ " )                                                                                           "
			)
	public void insertRankCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_RANKCODE 테이블 업데이트
	 * @param codeModel
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_RANKCODE SET                                                                      "
			+ "    RANKNAME = #{name},                                                                      "
			+ "    PRIORITY = #{priority}                                                                   "
			+ " WHERE RANKCODE = #{code}                                                                    "
			)
	public void updateRankCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_RANKCODE 테이블 삭제
	 * @param codeModel
	 * @throws Exception
	 */
	@Delete(  " DELETE FROM TB_RANKCODE                                                                     "
			+ " WHERE RANKCODE = #{code}                                                                    "
			)
	public void deleteRankCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_USER 테이블 업데이트
	 * TB_RANKCODE 테이블의 코드가 삭제 됨에 따라 다른 코드로 변경해준다.
	 * @param codeModel
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_USER SET                                                                          "
			+ "   RANKCODE = #{updateCode}                                                                  "
			+ " WHERE RANKCODE = #{code}                                                                    "
)
	public void updateUserRankCode(CodeModel codeModel) throws Exception;
	
	
	/******************************************** TB_BUSINESSCODE ****************************************************/
	
	/**
	 * TB_BUSINESSCODE 테이블 입력
	 * @param codeModel
	 * @throws Exception
	 */
	@Insert(  " INSERT INTO TB_BUSINESSCODE VALUES                                                          "
			+ " (                                                                                           "
			+ "   (SELECT MAX(CAST(BUSINESSCODE AS UNSIGNED)) + 1 FROM TB_BUSINESSCODE AS TEMP1),           "
			+ "   #{name},                                                                                  "
			+ "   (SELECT MAX(BUSINESSCODE) + 1 FROM TB_BUSINESSCODE AS TEMP)                               "
			+ " )                                                                                           "
			)
	public void insertBusinessCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_BUSINESSCODE 테이블 변경
	 * @param codeModel
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_BUSINESSCODE SET                                                                  "
			+ "    DESCRIPTION = #{name},                                                                   "
			+ "    PRIORITY = #{priority}                                                                   "
			+ " WHERE BUSINESSCODE = #{code}                                                                "
			)
	public void updateBusinessCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_BUSINESSCODE 테이블 삭제
	 * @param codeModel
	 * @throws Exception
	 */
	@Delete(  " DELETE FROM TB_BUSINESSCODE                                                                 "
			+ " WHERE BUSINESSCODE = #{code}                                                                "
			)
	public void deleteBusinessCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_PARTNER 테이블 변경
	 * TB_BUSINESSCODE 테이블 코드값이 삭제됨에 따라 다른 코드로 변경
	 * @param codeModel
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_PARTNER SET                                                                       "
			+ "   BUSINESSCODE = #{updateCode}                                                              "
			+ " WHERE BUSINESSCODE = #{code}                                                                "
			)
	public void updatePartnerBusinessCode(CodeModel codeModel) throws Exception;
	
	
	/******************************************** TB_PARTNERCODE ****************************************************/
	
	/**
	 * TB_PARTNERCODE 테이블 입력
	 * @param codeModel
	 * @throws Exception
	 */
	@Insert(  " INSERT INTO TB_PARTNERCODE VALUES                                                           "
			+ " (                                                                                           "
			+ "   (SELECT MAX(CAST(PARTNERCODE AS UNSIGNED)) + 1 FROM TB_PARTNERCODE AS TEMP1),             "
			+ "   #{name},                                                                                  "
			+ "   (SELECT MAX(PARTNERCODE) + 1 FROM TB_PARTNERCODE AS TEMP)                                 "
			+ " )                                                                                           "
			)
	public void insertPartnerCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_PARTNERCODE 테이블 변경
	 * @param codeModel
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_PARTNERCODE SET                                                                   "
			+ "    DESCRIPTION = #{name},                                                                   "
			+ "    PRIORITY = #{priority}                                                                   "
			+ " WHERE PARTNERCODE = #{code}                                                                 "
			)
	public void updatePartnerCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_PARTNERCODE 테이블 삭제
	 * @param codeModel
	 * @throws Exception
	 */
	@Delete(  " DELETE FROM TB_PARTNERCODE                                                                  "
			+ " WHERE PARTNERCODE = #{code}                                                                 "
			)
	public void deletePartnerCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_PARTNER 테이블 변경
	 * TB_PATNERCODE 테이블의 코드값이 삭제 됨에 따라 다른 코드로 변경
	 * @param codeModel
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_PARTNER SET                                                                       "
			+ "   PARTNERCODE = #{updateCode}                                                               "
			+ " WHERE PARTNERCODE = #{code}                                                                 "
			)
	public void updatePartnerPartnerCode(CodeModel codeModel) throws Exception;
	
	
	/******************************************** TB_OUTSOURCINGCODE ****************************************************/
	
	/**
	 * TB_OUTSOURCINGCODE 테이블 입력
	 * @param codeModel
	 * @throws Exception
	 */
	@Insert(  " INSERT INTO TB_OUTSOURCINGCODE VALUES                                                       "
			+ " (                                                                                           "
			+ "   (SELECT MAX(CAST(OUTSOURCINGCODE AS UNSIGNED)) + 1 FROM TB_OUTSOURCINGCODE AS TEMP1),     "
			+ "   #{name},                                                                                  "
			+ "   (SELECT MAX(OUTSOURCINGCODE) + 1 FROM TB_OUTSOURCINGCODE AS TEMP)                         "
			+ " )                                                                                           "
			)
	public void insertOutsourcingCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_OUTSOURCINGCODE 테이블 변경
	 * @param codeModel
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_OUTSOURCINGCODE SET                                                               "
			+ "    DESCRIPTION = #{name},                                                                   "
			+ "    PRIORITY = #{priority}                                                                   "
			+ " WHERE OUTSOURCINGCODE = #{code}                                                             "
			)
	public void updateOutsourcingCode(CodeModel codeModel) throws Exception;
		
	/**
	 * TB_OUTSOURCINGCODE 테이블 삭제
	 * @param codeModel
	 * @throws Exception
	 */
	@Delete(  " DELETE FROM TB_OUTSOURCINGCODE                                                              "
			+ " WHERE OUTSOURCINGCODE = #{code}                                                             "
			)
	public void deleteOutsourcingCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_OUTSOURCING 테이블 변경
	 * TB_OUTSOURCINGCODE 코드 삭제에 따라 다른 코드로 변경
	 * @param codeModel
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_OUTSOURCING SET                                                                   "
			+ "   OUTSOURCINGCODE = #{updateCode}                                                           "
			+ " WHERE OUTSOURCINGCODE = #{code}                                                             "
			)
	public void updateOutsourcingOutsourcingCode(CodeModel codeModel) throws Exception;
	
	
	/******************************************** TB_RATINGCODE ****************************************************/
	
	/**
	 * TB_RATINGCODE 테이블 입력
	 * @param codeModel
	 * @throws Exception
	 */
	@Insert(  " INSERT INTO TB_RATINGCODE VALUES                                                            "
			+ " (                                                                                           "
			+ "   (SELECT MAX(CAST(RATINGCODE AS UNSIGNED)) + 1 FROM TB_RATINGCODE AS TEMP1),               "
			+ "   #{name},                                                                                  "
			+ "   (SELECT MAX(RATINGCODE) + 1 FROM TB_RATINGCODE AS TEMP)                                   "
			+ " )                                                                                           "
			)
	public void insertRatingCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_RATINGCODE 테이블 변경
	 * @param codeModel
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_RATINGCODE SET                                                                    "
			+ "    DESCRIPTION = #{name},                                                                   "
			+ "    PRIORITY = #{priority}                                                                   "
			+ " WHERE RATINGCODE = #{code}                                                                  "
			)	
	public void updateRatingCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_RATINGCODE 테이블 삭제
	 * @param codeModel
	 * @throws Exception
	 */
	@Delete(  " DELETE FROM TB_RATINGCODE                                                                   "
			+ " WHERE RATINGCODE = #{code}                                                                  "
			)
	public void deleteRatingCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_MANPOWER 테이블 변경
	 * TB_RATINGCODE 테이블의 코드값이 삭제 됨에 따라 변경
	 * @param codeModel
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_MANPOWER SET                                                                      "
			+ "   RATINGCODE = #{updateCode}                                                                "
			+ " WHERE RATINGCODE = #{code}                                                                  "
			)
	public void updateManpowerRatingCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_UNITPRICE 테이블 변경
	 * TB_RATINGCODE 테이블의 코드 값이 삭제 됨에 따라 변경
	 * @param codeModel
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_UNITPRICE SET                                                                     "
			+ "   RATINGCODE = #{updateCode}                                                                "
			+ " WHERE RATINGCODE = #{code}                                                                  "
			)
	public void updateUnitPriceRatingCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_UNITPRICE 테이블 변경
	 * TB_RATINGCODE 테이블의 코드 값이 삭제 됨에 따라 변경
	 * @param codeModel
	 * @throws Exception
	 */
	@Update(  " UPDATE tb_outsourcing SET                                                                     "
			+ "   RATINGCODE = #{updateCode}                                                                "
			+ " WHERE RATINGCODE = #{code}                                                                  "
			)
	public void updateOutSourcingRatingCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_UNITPRICE 테이블 변경
	 * TB_RATINGCODE 테이블의 코드 값이 삭제 됨에 따라 변경
	 * @param codeModel
	 * @throws Exception
	 */
	@Update(  " UPDATE tb_preoutsourcing SET                                                                     "
			+ "   RATINGCODE = #{updateCode}                                                                "
			+ " WHERE RATINGCODE = #{code}                                                                  "
			)
	public void updatePreOutSourcingRatingCode(CodeModel codeModel) throws Exception;
	
	/**
	 * TB_UNITPRICE 테이블 변경
	 * TB_RATINGCODE 테이블의 코드 값이 삭제 됨에 따라 변경
	 * @param codeModel
	 * @throws Exception
	 */
	@Delete(  " DELETE FROM TB_UNITPRICE                                                              "
			+ " WHERE RATINGCODE = #{code}                                                             "
			)
	public void deleteUnitPriceRatingCode(CodeModel codeModel) throws Exception;
	
	
}
