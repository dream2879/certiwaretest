package com.certiware.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.certiware.backend.model.common.ProjectModel;
import com.certiware.backend.model.preproject.ModifyPreOutsourcingModel;
import com.certiware.backend.model.preproject.MovePreProjectReqModel;
import com.certiware.backend.model.preproject.PreManpowerModel;
import com.certiware.backend.model.preproject.SelectListReqModel;
import com.certiware.backend.model.preproject.SelectListResModel;
import com.certiware.backend.model.preproject.SelectPreOutsourcingResModel;
import com.certiware.backend.model.preproject.SelectPreProjectListReqModel;
import com.certiware.backend.model.preproject.SelectPreProjectListResModel;
import com.certiware.backend.model.preproject.UpdatePreManpowerModel;
import com.certiware.backend.model.progress.ManpowerNameModel;
import com.certiware.backend.model.progress.SelectManpowerListReqModel;

public interface PreProjectMapper {
	
	/**
	 * TB_PREPROJECT 테이블조회
	 * 해당부서가 담당하고 있는 프로젝트 리스틀 조회한다.
	 * 부등호사용시에는 <![CDATA[ ]]> 로 묶어줘야한다 xml 충돌방지
	 * @param deptCode
	 * @return
	 * @throws Exception
	 */
	@Select(  "<script>"
			+ "SELECT PROJECTID, PROJECTNAME "
			+ "FROM TB_PREPROJECT "
			+ " WHERE STARTDATE <![CDATA[<=]]> #{endDate} AND ENDDATE <![CDATA[>=]]> #{startDate} "			
			+ "<if test=\"deptCode != null and deptCode != '' \"> "
			+ "AND DEPTCODE = #{deptCode}"
			+ "</if>"
			+ "</script>"
			)
	public List<SelectPreProjectListResModel> selectPreProjectByDeptCode(SelectPreProjectListReqModel selectProjectListReqModel) throws Exception;	

	/**
	 * TB_PREPROJECT 테이블조회
	 * 프로젝트리스를 조회한다.
	 * @return
	 * @throws Exception
	 */	
	@Select(  "<script>"
			+ " SELECT A.PROJECTID, A.PROJECTNAME, A.DEPTCODE, B.PARTNERID, B.PARTNERNAME, A.CONTRACTAMOUNT, A.OUTSOURCINGAMOUNT, A.NETAMOUNT, A.STARTDATE, A.ENDDATE "
			+ " FROM TB_PREPROJECT A, TB_PARTNER B  "
			+ " WHERE A.PARTNERID = B.PARTNERID "
			+ " AND A.STARTDATE <![CDATA[<=]]> #{endDate} AND A.ENDDATE <![CDATA[>=]]> #{startDate} "
			// projectName
			+ "<if test=\"projectName != null and projectName != '' \"> "
			+ " AND A.PROJECTNAME LIKE CONCAT('%',#{projectName}, '%') "
			+ "</if>"
			// partnerName
			+ "<if test=\"partnerName != null and partnerName != '' \"> "
			+ " AND B.PARTNERNAME LIKE CONCAT('%',#{partnerName}, '%') "
			+ "</if>"
			// deptCode
			+ "<if test=\"deptCode != null and deptCode != '' \"> "
			+ " AND A.DEPTCODE = #{deptCode}  "
			+ "</if>"			
			+ "</script>"
			)
	public List<SelectListResModel> selectList(SelectListReqModel selectListReqModel) throws Exception;

	/**
	 * TB_PREPROJECT 테이블조회.
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	@Select(  "SELECT * "
			+ "FROM TB_PREPROJECT "
			+ "WHERE PROJECTID = #{param1}"
			)
	public ProjectModel selectPreProjectByPK(int projectId) throws Exception;	
	
	/**
	 * TB_PREPROJECT 테이블 입력
	 * @param projectModel
	 * @return
	 * @throws Exception
	 */
	@Insert(  " INSERT INTO TB_PREPROJECT                                                                                                                 "
			+ " (PROJECTNAME, DEPTCODE, PARTNERID, STARTDATE, ENDDATE, CONTRACTAMOUNT, SUPPLYAMOUNT, VTAAMOUNT, OUTSOURCINGAMOUNT, NETAMOUNT, REMARKS) "
			+ " VALUES                                                                                                                                 "
			+ " (	#{projectName}, "
			+ "		#{deptCode}, "
			+ "		#{partnerId}, "
			+ "		#{startDate}, "
			+ "		#{endDate}, "
			+ "		#{contractAmount}, "
			+ "		#{supplyAmount}, "					  
			+ "		#{vtaAmount}, "
			+ "		#{outsourcingAmount}, "
			+ "		#{netAmount}, "
			+ "		#{remarks}"
			+ ") "
		    )
	@Options(useGeneratedKeys = true, keyProperty="projectId")
	public void insertPreProject(ProjectModel projectModel) throws Exception;	
	
	/**
	 * TB_PREPROJECT 변경
	 * @param projectModel
	 * @return 성공:1,실패:0
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_PREPROJECT "
			+ " SET PROJECTNAME = #{projectName},"
			+ "		DEPTCODE =#{deptCode},"
			+ "		PARTNERID=#{partnerId},"
			+ "		STARTDATE=#{startDate},"
			+ "		ENDDATE=#{endDate}, "
			+ " 	CONTRACTAMOUNT=#{contractAmount},"
			+ "		SUPPLYAMOUNT=#{supplyAmount},"
			+ "		VTAAMOUNT=#{vtaAmount},"
			+ "		OUTSOURCINGAMOUNT=#{outsourcingAmount},"
			+ "		NETAMOUNT=#{netAmount},"
			+ "		REMARKS=#{remarks} "
			+ " WHERE PROJECTID = #{projectId} "
			)
	public int updatePreProjectByProjectId(ProjectModel projectModel) throws Exception;
	
	
	/**
	 * TB_PREPROJECT 테이블삭제.
	 * @param projectId
	 * @return 삭제한 로우 수
	 * @throws Exception
	 */
	@Delete(  "DELETE FROM TB_PREPROJECT "
			+ "WHERE PROJECTID = #{param1}"
			)
	public int deletePreProjectByProjectId(int projectId) throws Exception;
	
	/******************************************************************************************************************************/
	
	
	/**
	 * TB_PREOUTSOURCING 테이블 조회
	 * 특정 프로젝트에 해당하는 리스트 조회.
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	@Select(  " SELECT A.*, B.PARTNERNAME, B.PARTNERCODE  "
			+ " FROM TB_PREOUTSOURCING A, TB_PARTNER B  "
			+ " WHERE A.PARTNERID = B.PARTNERID   "
			+ " AND A.PROJECTID = #{param1}"
			)
	public List<SelectPreOutsourcingResModel> selectPreOutsourcingByProjectId(int projectId) throws Exception;
	
	
	/**
	 * TB_PREOUTSOURCING 테이블 INSERT
	 * @param outsourcingModel
	 * @return
	 * @throws Exception
	 */
	@Insert(  "INSERT INTO TB_PREOUTSOURCING VALUES " 			 
			+ "(	"
			+ "		#{projectId}, "
			+ "		#{partnerId}, "
			+ "		#{outsourcingCode}, "
			+ "		#{outsourcingAmount}, "
			+ "		#{ratingCode}, "
			+ "		#{product}, "
			+ "		#{locale}, "
			+ "		#{startDate}, "
			+ "		#{endDate},"
			+ "		#{remarks} "
			+ " )"
			)
	public void inertPreOutsourcing(ModifyPreOutsourcingModel insertOutsourcingModel) throws Exception;
	
	
	/**
	 * TB_PREOUTSOURCING UPDATE
	 * @param outsourcingModel
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_PREOUTSOURCING SET                                                                            "
			+ "     OUTSOURCINGAMOUNT=#{outsourcingAmount},                                                          "
			+ "     RATINGCODE=#{ratingCode},                                                                        "
			+ "     PRODUCT=#{product},                                                                              "
			+ "     LOCALE=#{locale},																			"
			+ "     STARTDATE=#{startDate},                                                                          "
			+ "     ENDDATE=#{endDate}                                                                               "
			+ "     REMARKS=#{remarks},                                                                          "
			+ " WHERE PROJECTID = #{projectId} AND PARTNERID = #{partnerId} AND OUTSOURCINGCODE = #{outsourcingCode} "
			)
	public void updatePreOutsourcing(ModifyPreOutsourcingModel modifyOutsourcingModel) throws Exception;
	
	
	
	/**
	 * TB_PREOUTSOURCING 테이블삭제
	 * @param outsourcingModels
	 * @return
	 * @throws Exception
	 */
	@Delete(  "DELETE FROM TB_PREOUTSOURCING "
			+ "WHERE PROJECTID =#{projectId} "
			+ "AND PARTNERID = #{partnerId} "
			+ "AND OUTSOURCINGCODE = #{outsourcingCode}"
			)
	public void deletePreOutsourcing(ModifyPreOutsourcingModel modifyOutsourcingModel) throws Exception;
	
	/**
	 * TB_PREMANPOWER 테이블 insert
	 * @param manpowerModels
	 * @return
	 * @throws Exception
	 */
	@Insert(  " INSERT INTO TB_PREMANPOWER VALUES " // insert
			+ "("
			+ "		#{projectId}, "
			+ "		#{manpowerName}, "
			+ "		#{partnerId}, "
			+ "		#{ratingCode}, "
			+ "		#{sellingAmount}, "
			+ "		#{outsourcingAmount}, "
			+ "		#{startDate}, "
			+ "		#{endDate}, "
			+ "		#{remarks}"
			+ ") "   
			)
	public void insertePreManpower(PreManpowerModel manpowerModel) throws Exception;
	
	
	/**
	 * TB_PREMANPOWER 테이블 변경
	 * @param updateManpowerModel
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_PREMANPOWER SET      "
			+ "   MANPOWERNAME = #{manpowerName},        "
			+ "   PARTNERID = #{partnerId},            "
			+ "   RATINGCODE = #{ratingCode},          "
			+ "   SELLINGAMOUNT = #{sellingAmount},        "
			+ "   OUTSOURCINGAMOUNT = #{outsourcingAmount},    "
			+ "   STARTDATE = #{startDate},           "
			+ "   ENDDATE = #{endDate},             "
			+ "   REMARKS = #{remarks}              "
			+ " WHERE PROJECTID = #{pk1}     "
			+ " AND MANPOWERNAME = #{pk2} "
			)
	public void updatePreManpower(UpdatePreManpowerModel updateManpowerModel) throws Exception;	
	
	@Select(  "<script>"
			+ " SELECT  A.PROJECTID, A.MANPOWERNAME, A.PARTNERID,																									"
			+ "         CASE WHEN B.PARTNERCODE >= 3 THEN 'A' ELSE A.PARTNERID END AS PARTNERGUBUN,      "
			+ "			A.RATINGCODE, A.SELLINGAMOUNT, A.OUTSOURCINGAMOUNT, A.STARTDATE, A.ENDDATE, A.REMARKS "
			+ " FROM TB_PREMANPOWER A, TB_PARTNER B                                                    "
			+ " WHERE A.PARTNERID = B.PARTNERID                                                     "
			+ " AND A.PROJECTID = #{projectId}                                                               "
			
			+ "<if test=\"manpowerName != null and manpowerName != '' \"> "
			+ " AND A.MANPOWERNAME = #{manpowerName}"
			+ "</if>"
			
			+ "</script>"
			)
	public List<ManpowerNameModel> selectManpowerByProjectId(SelectManpowerListReqModel selectManpowerListReqModel) throws Exception;
	
	
	/**
	 * TB_PREPROJECT 정보를 TB_PROJECT 테이블로 옮긴다.
	 * @param projectId : TB_PREPROJECT 테이블의 prejectId
	 * @return TB_PROJECT에 INSERT 할 할때 생성된 projectId
	 * @throws Exception
	 */
	@Insert(  " INSERT INTO TB_PROJECT (PROJECTNAME, DEPTCODE, PARTNERID,  STARTDATE, ENDDATE, CONTRACTAMOUNT, SUPPLYAMOUNT, VTAAMOUNT, OUTSOURCINGAMOUNT, NETAMOUNT, REMARKS) 	"
			+ " SELECT PROJECTNAME, DEPTCODE, PARTNERID,  STARTDATE, ENDDATE, CONTRACTAMOUNT, SUPPLYAMOUNT, VTAAMOUNT, OUTSOURCINGAMOUNT, NETAMOUNT, REMARKS                    "
			+ " FROM TB_PREPROJECT                                                                                                                                              "
			+ " WHERE PROJECTID = #{projectId}                                                                                                                                           "
			)
	@Options(useGeneratedKeys = true, keyProperty = "projectId")
	@SelectKey(keyProperty = "generatedProjectId", resultType = int.class, before = false, statement = { "SELECT LAST_INSERT_ID();" })
	public int insertProjectPreProject(MovePreProjectReqModel movePreProjectReqModel) throws Exception;
	
	
	/**
	 * TB_PREOUTSOURCING 정보를 TB_OUTSOURCING 테이블로 옮긴다.
	 * @param projectId : TB_PROJECT에 INSERT 할 할때 생성된 projectId
	 * @throws Exception
	 */
	@Insert(  "INSERT INTO TB_OUTSOURCING "
			+ "SELECT #{projectId}, PARTNERID, OUTSOURCINGCODE, OUTSOURCINGAMOUNT, RATINGCODE, PRODUCT, LOCALE, STARTDATE, ENDDATE, REMARKS "
			+ "FROM TB_PREOUTSOURCING ")
	public void insertOutsourcingPreOutsourcing(@Param("projectId") int projectId) throws Exception;


}
