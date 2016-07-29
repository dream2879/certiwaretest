package com.certiware.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certiware.backend.model.common.ProjectModel;
import com.certiware.backend.model.project.ModifyOutsourcingModel;
import com.certiware.backend.model.project.SelectListReqModel;
import com.certiware.backend.model.project.SelectListResModel;
import com.certiware.backend.model.project.SelectOutsourcingResModel;
import com.certiware.backend.model.project.SelectProjectListReqModel;
import com.certiware.backend.model.project.SelectProjectListResModel;

public interface ProjectMapper {
	
	/**
	 * TB_PROJECT 테이블조회
	 * 해당부서가 담당하고 있는 프로젝트 리스틀 조회한다.
	 * 부등호사용시에는 <![CDATA[ ]]> 로 묶어줘야한다 xml 충돌방지
	 * @param deptCode
	 * @return
	 * @throws Exception
	 */
	@Select(  "<script>"
			+ "SELECT PROJECTID, PROJECTNAME "
			+ "FROM TB_PROJECT "
			+ " WHERE STARTDATE <![CDATA[<=]]> #{endDate} AND ENDDATE <![CDATA[>=]]> #{startDate} "			
			+ "<if test=\"deptCode != null and deptCode != '' \"> "
			+ "AND DEPTCODE = #{deptCode}"
			+ "</if>"
			+ "</script>"
			)
	public List<SelectProjectListResModel> selectProjectByDeptCode(SelectProjectListReqModel selectProjectListReqModel) throws Exception;	

	/**
	 * TB_PROJECT 테이블조회
	 * 프로젝트리스를 조회한다.
	 * @return
	 * @throws Exception
	 */	
	@Select(  "<script>"
			+ " SELECT A.PROJECTID, A.PROJECTNAME, A.DEPTCODE, B.PARTNERID, B.PARTNERNAME, A.CONTRACTAMOUNT, A.OUTSOURCINGAMOUNT, A.NETAMOUNT, A.STARTDATE, A.ENDDATE "
			+ " FROM TB_PROJECT A, TB_PARTNER B  "
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
	 * TB_PROJECT 테이블조회.
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	@Select(  "SELECT * "
			+ "FROM TB_PROJECT "
			+ "WHERE PROJECTID = #{param1}"
			)
	public ProjectModel selectProjectByPK(int projectId) throws Exception;
	
	/**
	 * TB_OUTSOURCING 테이블 조회
	 * 특정 프로젝트에 해당하는 리스트 조회.
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	@Select(  " SELECT A.*, B.PARTNERNAME, B.PARTNERCODE  "
			+ " FROM TB_OUTSOURCING A, TB_PARTNER B  "
			+ " WHERE A.PARTNERID = B.PARTNERID   "
			+ " AND A.PROJECTID = #{param1}"
			)
	public List<SelectOutsourcingResModel> selectOutsourcingByProjectId(int projectId) throws Exception;
	
	/**
	 * TB_PROJECT 테이블 입력
	 * @param projectModel
	 * @return
	 * @throws Exception
	 */
	@Insert(  " INSERT INTO TB_PROJECT                                                                                                                 "
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
	public void insertProject(ProjectModel projectModel) throws Exception;	
	
	/**
	 * TB_PROJECT 변경
	 * @param projectModel
	 * @return 성공:1,실패:0
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_PROJECT "
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
	public int updateProjectByProjectId(ProjectModel projectModel) throws Exception;
	
	/**
	 * TB_PROJECT 테이블삭제.
	 * @param projectId
	 * @return 삭제한 로우 수
	 * @throws Exception
	 */
	@Delete(  "DELETE FROM TB_PROJECT "
			+ "WHERE PROJECTID = #{param1}"
			)
	public int deleteProjectByProjectId(int projectId) throws Exception;
	
	
	/**
	 * TB_OUTSOURCING 테이블 INSERT
	 * @param outsourcingModel
	 * @return
	 * @throws Exception
	 */
	@Insert(  "INSERT INTO TB_OUTSOURCING VALUES " 			 
			+ "(	"
			+ "		#{projectId}, "
			+ "		#{partnerId}, "
			+ "		#{outsourcingCode}, "
			+ "		#{outsourcingAmount}, "
			+ "		#{ratingCode}, "
			+ "		#{product}, "
			+ "		#{locale}, "
			+ "		#{startDate}, "
			+ "		#{endDate}"
			+ " )"
			)
	public void inertOutsourcing(ModifyOutsourcingModel insertOutsourcingModel) throws Exception;
	
	
	
	/**
	 * TB_OUTSOURCING UPDATE
	 * @param outsourcingModel
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_OUTSOURCING SET                                                                            "
			+ "     OUTSOURCINGAMOUNT=#{outsourcingAmount},                                                          "
			+ "     RATINGCODE=#{ratingCode},                                                                        "
			+ "     PRODUCT=#{product},                                                                              "
			+ "     LOCALE=#{locale},																			"
			+ "     STARTDATE=#{startDate},                                                                          "
			+ "     ENDDATE=#{endDate}                                                                               "
			+ " WHERE PROJECTID = #{projectId} AND PARTNERID = #{partnerId} AND OUTSOURCINGCODE = #{outsourcingCode} "
			)
	public void updateOutsourcing(ModifyOutsourcingModel modifyOutsourcingModel) throws Exception;
	
	
	
	/**
	 * TB_OUTSOURCING 테이블삭제
	 * @param outsourcingModels
	 * @return
	 * @throws Exception
	 */
	@Delete(  "DELETE FROM TB_OUTSOURCING "
			+ "WHERE PROJECTID =#{projectId} "
			+ "AND PARTNERID = #{partnerId} "
			+ "AND OUTSOURCINGCODE = #{outsourcingCode}"
			)
	public int deleteOutsourcing(ModifyOutsourcingModel modifyOutsourcingModel) throws Exception;

}
