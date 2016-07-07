package com.certiware.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.certiware.backend.model.common.ManpowerMmModel;
import com.certiware.backend.model.common.ManpowerModel;
import com.certiware.backend.model.common.QueryModel;
import com.certiware.backend.model.common.UserModel;
import com.certiware.backend.model.progress.ProjectPartnerModel;
import com.certiware.backend.model.progress.SelectPartnerNameList;
import com.certiware.backend.model.progress.SelectProgressListResModel;

public interface ProgressMapper {

	
	/**
	 * TB_OUTSOURCING 테이블조회
	 * 특정 프로젝트에 포함된 PARTNER 리스트를 조회한다.ㅣ
	 * @param projectId:프로젝트아이디
	 * @return
	 * @throws Exception
	 */
	@Select(  " SELECT B.PARTNERID, CASE WHEN B.PARTNERCODE = '3' THEN '자사' ELSE PARTNERNAME END AS PARTNERNAME "
			+ " FROM TB_OUTSOURCING A, TB_PARTNER B                                                               "
			+ " WHERE A.PROJECTID = #{param1} "
			+ " AND A.PARTNERID = B.PARTNERID "
			+ " AND B.PARTNERCODE <= 3  	          "
			+ " ORDER BY B.PARTNERID "
			)
	public List<ProjectPartnerModel> selectOutsourcingByProjectId(int projectId) throws Exception;
	/*
	/**
	 * TB_MANPOWER 테이블조회
	 * 특정 프로젝트에 포함된 투입인력 목록을 조회한다.
	 * @param projectId
	 * @return
	 * @throws Exception
	 
	@Select(  " SELECT PROJECTID, MANPOWERNAME "
			+ " FROM TB_MANPOWER        "
			+ " WHERE PROJECTID = #{param1}    "
			+ " ORDER BY MANPOWERNAME          ")
	public List<ManpowerNameListModel> selectManpowerNameList(int projectId)throws Exception;
	*/
	
	/**
	 * TB_MANPOWER 테이블조회
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	@Select(  "SELECT * "
			+ "FROM TB_MANPOWER "
			+ "WHERE PROJECTID = #{param1}")
	public List<ManpowerModel> selectManpowerList(int projectId) throws Exception;
	
	
	/**
	 * TB_MANPOWER 테이블 MERGE
	 * @param manpowerModels
	 * @return
	 * @throws Exception
	 */
	@Insert(  " INSERT INTO TB_MANPOWER VALUES " // insert
			+ "("
			+ "		#{projectId}, "
			+ "		#{manpowerName}, "
			+ "		#{partnerId}, "
			+ "		#{RatingCode}, "
			+ "		#{sellingAmount}, "
			+ "		#{outsourcingAmount}, "
			+ "		#{startDate}, "
			+ "		#{endDate}, "
			+ "		#{remarks}"
			+ ") "   
			+ " ON DUPLICATE KEY UPDATE "	// update
			+ "		PARTNERID =#{partnerId},"
			+ "		RATINGCODE=#{RatingCode},"
			+ "		SELLINGAMOUNT=#{sellingAmount},"
			+ "		OUTSOURCINGAMOUNT=#{outsourcingAmount},"
			+ "		STARTDATE=#{startDate},ENDDATE=#{endDate},"
			+ "		REMARKS=#{remarks} "
			)
	public void mergeManpower(ManpowerModel manpowerModel) throws Exception;
	
	
	/**
	 * TB_MANPOWER 테이블삭제
	 * @param manpowerModels
	 * @return
	 * @throws Exception
	 */
	@Delete(  "DELETE FROM TB_MANPOWER "
			+ "WHERE PROJECTID = #{projectId} "
			+ "AND MANPOWERNAME = #{manpowerName} "
			)
	public int deleteManpower(ManpowerModel manpowerModel) throws Exception;

	
	/**
	 * TB_MANPOWERMM 테이블 조회
	 * @param projectId
	 * @param manpowerName
	 * @return
	 * @throws Exception
	 */
	@Select(  "SELECT * "
			+ "FROM TB_MANPOWERMM "
			+ "WHERE PROJECTID = #{param1} "
			+ "AND MANPOWERNAME = #{param2} "
			)
	public List<ManpowerMmModel> selectManpowerMmList(int projectId, String manpowerName) throws Exception;
	
	/**
	 * TB_MANPOWERMM 테이블 MERGE
	 * @param manpowerMmModels
	 * @throws Exception
	 */
	@Insert(  "INSERT INTO TB_MANPOWERMM VALUES "
			+ "("
			+ "		#{projectId}, "
			+ "		#{manpowerName}, "
			+ "		#{month}, "
			+ "		#{mm}"
			+ ") "
			+ "ON DUPLICATE KEY UPDATE "
			+ "		MM = #{mm}"
			)
	public void mergeManpowerMm(ManpowerMmModel manpowerMmModel) throws Exception;
	
	/**
	 * TB_MANPOWERMM 테이블삭제
	 * @param manpowerMmModels
	 * @return
	 * @throws Exception
	 */
	@Delete(  "DELETE FROM TB_MANPOWERMM "
			+ "WHERE PROJECTID=#{projectId} "
			+ "AND MANPOWERNAME=#{manpowerName} "
			+ "AND MONTH=#{month}"
			)
	public int deleteManpowerMm(ManpowerMmModel manpowerMmModel) throws Exception;
	
	
	/**
	 * 쿼리문을 전달받아 직접날린다.
	 * 프로젝트 진행현황 로직이 복잡하여 쿼리문을 직접받도록 개발함
	 * @param queryModel
	 * @return
	 * @throws Exception
	 */
	@Select(" ${query} ")
	//public List<UserModel> selectTest(UserModel query) throws Exception;
	public List<SelectProgressListResModel> SelectQuery(QueryModel queryModel) throws Exception;
	
	/**
	 * 프로젝트 아이디를가지고 외주회사명을 가져온다.
	 * 소속회사가 없는 프리랜서인 경우 프리랜서로 출력
	 * @param partnerId
	 * @return
	 * @throws Exception
	 */
	@Select( " SELECT A.PARTNERID, B.DESCRIPTION                            																						"	
			+ " FROM TB_OUTSOURCING A,                                                                                  "
			+ "      (SELECT PARTNERID, CASE WHEN PARTNERCODE >= 3 THEN '프리랜서' ELSE PARTNERNAME END AS DESCRIPTION  "
			+ "         FROM   TB_PARTNER) B                                                                            "
			+ " WHERE A.PARTNERID = B.PARTNERID                                                                         "
			+ " AND A.PROJECTID = #{param1}                                                                             "
			+ " ORDER BY A.PARTNERID                                                                                    "
			)
	public List<SelectPartnerNameList> selectOutsourcingByPartnerId(int partnerId) throws Exception;

}
