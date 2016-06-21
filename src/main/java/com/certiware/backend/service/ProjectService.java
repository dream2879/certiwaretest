package com.certiware.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certiware.backend.mapper.ProjectMapper;
import com.certiware.backend.model.common.OutsourcingModel;
import com.certiware.backend.model.common.PartnerCodeModel;
import com.certiware.backend.model.common.ProjectModel;

@Service
public class ProjectService {
	
	@Autowired
	CommonService commonService;
	@Autowired
	ProjectMapper projectMapper;
	
	public List<PartnerCodeModel> SelectPartnerCode() throws Exception{
		 return commonService.SelectPartnerCode();
		  
	}
	
	public ProjectModel insertProject(ProjectModel projectModel) throws Exception{
		return projectMapper.insertProject(projectModel);
	}
	
	public void insertOutsourcing(List<OutsourcingModel> outsourcingModels) throws Exception{
		projectMapper.insertOutsourcing(outsourcingModels);
	}
}
