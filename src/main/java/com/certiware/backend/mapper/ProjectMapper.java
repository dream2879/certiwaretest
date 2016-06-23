package com.certiware.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certiware.backend.model.common.OutsourcingModel;
import com.certiware.backend.model.common.ProjectModel;
import com.certiware.backend.model.project.SelectListModel;

public interface ProjectMapper {
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */	
	@Select(  " SELECT A.PROJECTID, A.PROJECTNAME, B.PARTNERID, B.PARTNERNAME, A.CONTRACTAMOUNT, A.NETAMOUNT, A.STARTDATE, A.ENDDATE "
			+ " FROM TB_PROJECT A, TB_PARTNER B  "
			+ " WHERE A.PARTNERID = B.PARTNERID "
			)
	public List<SelectListModel> selectList() throws Exception;
	
	/**
	 * 
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT * FROM TB_PROJECT WHERE PROJECTID = #{param1}")
	public ProjectModel selectProjectByProjectId(int projectId) throws Exception;
	
	/**
	 * 
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	@Select("SELECT * FROM TB_OUTSOURCING WHERE PROJECTID = #{param1}")
	public List<OutsourcingModel> selectOutsourcingByProjectId(int projectId) throws Exception;
	
	/**
	 * 
	 * @param projectModel
	 * @return
	 * @throws Exception
	 */
	@Insert(  " INSERT INTO TB_PROJECT                                                                                                                 "
			+ " (PROJECTNAME, DEPTCODE, PARTNERID, STARTDATE, ENDDATE, CONTRACTAMOUNT, SUPPLYAMOUNT, VTAAMOUNT, OUTSOURCINGAMOUNT, NETAMOUNT, REMARKS) "
			+ " VALUES                                                                                                                                 "
			+ " (#{projectName}, #{deptCode}, #{partnerID}, #{startDate}, #{endDate}, #{contractAmount}, #{supplyAmount}, #{vtaAmount}, #{outsourcingAmount}, #{netAmount}, #{remarks}) "
		    )
	@Options(useGeneratedKeys = true, keyProperty="projectId")
	public ProjectModel insertProject(ProjectModel projectModel) throws Exception;	
	
	/**
	 * 
	 * @param outsourcingModels
	 * @throws Exception
	 */
	@Insert("INSERT INTO TB_OUTSOURCING VALUES (#{projectId}, #{partnerId}, #{outsourcingCode}, #{outsourcingAmount}, #{rating}, #{product}, #{startDate}, #{endDate})")
	public int insertOutsourcing(List<OutsourcingModel> outsourcingModels) throws Exception;
	
	/**
	 * 
	 * @param projectModel
	 * @return 성공:1,실패:0
	 * @throws Exception
	 */
	@Update(  " UPDATE TB_PROJECT "
			+ " SET PROJECTNAME = #{projectName},DEPTCODE =#{deptCode},PARTNERID=#{partnerId},STARTDATE=#{startDate},ENDDATE=#{endDate}, "
			+ " 		CONTRACTAMOUNT=#{contractAmount},SUPPLYAMOUNT=#{supplyAumount},VTAAMOUNT=#{vtaAmount},OUTSOURCINGAMOUNT=#{outsourcing_aomut},NETAMOUNT=#{netAmount},REMARKS=#{remarks} "
			+ " WHERE PROJECTID = #{projectId} "

			)
	public int updateProjectByProjectId(ProjectModel projectModel) throws Exception;
	
	/**
	 * 
	 * @param projectId
	 * @return 삭제한 로우 수
	 * @throws Exception
	 */
	@Delete("DELETE FROM TB_PROJECT WHERE PROJECTID = #{param1}")
	public int deleteProjectByProjectId(int projectId) throws Exception;

}
