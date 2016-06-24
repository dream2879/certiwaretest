package com.certiware.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certiware.backend.mapper.ProjectMapper;
import com.certiware.backend.model.common.ProjectModel;
import com.certiware.backend.model.project.ModifyOutsourcingModel;
import com.certiware.backend.model.project.SelectCodeModel;
import com.certiware.backend.model.project.SelectDetailModel;
import com.certiware.backend.model.project.SelectListModel;

@Service
public class ProjectService {
	
	@Autowired
	CommonService commonService;
	@Autowired
	ProjectMapper projectMapper;
	
	/**
	 * 
	 * @param selectCodeModel
	 * @return
	 * @throws Exception
	 */
	public SelectCodeModel selectCode(SelectCodeModel selectCodeModel) throws Exception{
		
		selectCodeModel.setDeptCodeModels(commonService.SelectDeptCode());
		selectCodeModel.setPartnerModels(projectMapper.selectCustomerPatner());
		selectCodeModel.setBusinessCodeModels(commonService.SelectBusinessCode());
		selectCodeModel.setRatingCodeModels(commonService.SelectRatingCode());		
		
		return selectCodeModel;
		  
	}
	
	/**
	 * 
	 * @param selectListModel
	 * @return
	 * @throws Exception
	 */
	public List<SelectListModel> selectList() throws Exception{
		
		return projectMapper.selectList();
	}
	
	/**
	 * 
	 * @param selectDetailModel
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	public SelectDetailModel selectDetail(SelectDetailModel selectDetailModel, int projectId) throws Exception{
		
		selectDetailModel.setProjectModel(projectMapper.selectProjectByProjectId(projectId));
		
		selectDetailModel.setOutsourcingModels(projectMapper.selectOutsourcingByProjectId(projectId));
		
		return selectDetailModel;
	}
	
	/**
	 * 
	 * @param projectModel
	 * @return
	 * @throws Exception
	 */
	public int insertProject(ProjectModel projectModel) throws Exception{
		return projectMapper.insertProject(projectModel).getPartnerId();
	}
	
	/**
	 * 
	 * @param projectModel
	 * @throws Exception
	 */
	public int updateProject(ProjectModel projectModel) throws Exception{
		return projectMapper.updateProjectByProjectId(projectModel);
	}
	
	/**
	 * 
	 * @param projectId
	 * @throws Exception
	 */
	public int deleteProject(int projectId) throws Exception{
		return projectMapper.deleteProjectByProjectId(projectId);
	}
	
	/**
	 * 
	 * @param modifyOutsourcingModel
	 * @return
	 * @throws Exception
	 */
	public int modifyOutsourcing(ModifyOutsourcingModel modifyOutsourcingModel) throws Exception{
		int result=0;
		
		result=+projectMapper.mergeOutsourcing(modifyOutsourcingModel.getMergeOutsourcingModels());
		result=+projectMapper.deleteOutsourcing(modifyOutsourcingModel.getDeleteOutsourcingModels());
		
		return result;
	}
	

}
