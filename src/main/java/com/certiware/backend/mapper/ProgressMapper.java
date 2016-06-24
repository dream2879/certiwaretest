package com.certiware.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.certiware.backend.model.common.ManpowerMmModel;
import com.certiware.backend.model.common.ManpowerModel;
import com.certiware.backend.model.progress.ManpowerNameListModel;
import com.certiware.backend.model.progress.ProjectListModel;
import com.certiware.backend.model.progress.ProjectPartnerModel;

public interface ProgressMapper {
	
	/**
	 * 
	 * @param deptCode
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT PROJECTID, PROJECTNAME FROM TB_PROJECT WHERE DEPTCODE = #{param1}")
	public List<ProjectListModel> selectProjectList(String deptCode) throws Exception;
	
	/**
	 * 
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	@Select(  " SELECT B.PARTNERID, CASE WHEN B.PARTNERCODE = '3' THEN '자사' ELSE PARTNERNAME END AS PARTNERNAME "
			+ " FROM TB_OUTSOURCING A, TB_PARTNER B                                                               "
			+ " WHERE A.PROJECTID = #{param1} AND A.PARTNERID = B.PARTNERID AND B.PARTNERCODE <= 3  	          "
			+ " ORDER BY B.PARTNERID                                                                              "
			)
	public List<ProjectPartnerModel> selectProjectPartner(int projectId) throws Exception;
	
	/**
	 * 
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	@Select(  " SELECT PROJECTID, MANPOWERNAME "
			+ " FROM TB_PROJECTMANPOWER        "
			+ " WHERE PROJECTID = #{param1}    "
			+ " ORDER BY MANPOWERNAME          ")
	public List<ManpowerNameListModel> selectManpowerNameList(int projectId)throws Exception;
	
	/**
	 * 
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT * FROM TB_PROJECTMANPOWER WHERE PROJECTID = #{param1}")
	public List<ManpowerModel> selectManpowerList(int projectId) throws Exception;
	
	
	/**
	 * 
	 * @param manpowerModels
	 * @return
	 * @throws Exception
	 */
	@Insert(  " INSERT INTO TB_PROJECTMANPOWER VALUES (#{projectId}, #{manpowerName}, #{partnerId}, #{RatingCode}, #{sellingAmount}, #{outsourcingAmount}, #{startDate}, #{endDate}, #{remarks})                                              "   
			+ " ON DUPLICATE KEY UPDATE PARTNERID =#{partnerId},RATINGCODE=#{RatingCode},SELLINGAMOUNT=#{sellingAmount},OUTSOURCINGAMOUNT=#{outsourcingAmount},STARTDATE=#{startDate},ENDDATE=#{endDate},REMARKS=#{remarks} "
			)
	public void mergeManpower(List<ManpowerModel> manpowerModels) throws Exception;
	
	
	/**
	 * 
	 * @param manpowerModels
	 * @return
	 * @throws Exception
	 */
	@Delete("DELETE FROM TB_PROJECTMANPOWER WHERE PROJECTID = #{projectId} AND MANPOWERNAME = #{manpowerName} ")
	public int deleteManpower(List<ManpowerModel> manpowerModels) throws Exception;

	
	/**
	 * 
	 * @param projectId
	 * @param manpowerName
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT * FROM TB_MANPOWERMM WHERE PROJECTID = #{param1} AND MANPOWERNAME = #{param2}")
	public List<ManpowerMmModel> selectManpowerMmList(int projectId, String manpowerName) throws Exception;
	
	/**
	 * 
	 * @param manpowerMmModels
	 * @throws Exception
	 */
	@Insert("INSERT INTO TB_MANPOWERMM VALUES (#{projectId}, #{manpowerName}, #{month}, #{mm}) ON DUPLICATE KEY UPDATE MM = #{mm}")
	public void mergeManpowerMm(List<ManpowerMmModel> manpowerMmModels) throws Exception;
	
	/**
	 * 
	 * @param manpowerMmModels
	 * @return
	 * @throws Exception
	 */
	@Delete("DELETE FROM TB_MANPOWERMM WHERE PROJECTID=#{projectId} AND MANPOWERNAME=#{manpowerName} AND MONTH=#{month}")
	public int deleteManpowerMm(List<ManpowerMmModel> manpowerMmModels) throws Exception;

}
