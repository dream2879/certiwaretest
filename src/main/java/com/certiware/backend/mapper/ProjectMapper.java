package com.certiware.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.certiware.backend.model.common.OutsourcingModel;
import com.certiware.backend.model.common.ProjectModel;

public interface ProjectMapper {
	
	@Insert(  " INSERT INTO TB_PROJECT                                                                                                                 "
			+ " (PROJECTNAME, DEPTCODE, PARTNERID, STARTDATE, ENDDATE, CONTRACTAMOUNT, SUPPLYAMOUNT, VTAAMOUNT, OUTSOURCINGAMOUNT, NETAMOUNT, REMARKS) "
			+ " VALUES                                                                                                                                 "
			+ " (#{projectName}, #{deptCode}, #{partnerID}, #{startDate}, #{endDate}, #{contractAmount}, #{supplyAmount}, #{vtaAmount}, #{outsourcingAmount}, #{netAmount}, #{remarks}) "
		    )
	@Options(useGeneratedKeys = true, keyProperty="projectId")
	public ProjectModel insertProject(ProjectModel projectModel) throws Exception;
	
	
	@Insert("INSERT INTO TB_OUTSOURCING VALUES (#{projectId}, #{partnerId}, #{outsourcingCode}, #{outsourcingAmount}, #{rating}, #{product}, #{startDate}, #{endDate})")
	public void insertOutsourcing(List<OutsourcingModel> outsourcingModels) throws Exception;

}
