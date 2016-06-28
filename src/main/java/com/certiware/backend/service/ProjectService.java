package com.certiware.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certiware.backend.mapper.ProjectMapper;
import com.certiware.backend.model.common.PartnerModel;
import com.certiware.backend.model.common.ProjectModel;
import com.certiware.backend.model.project.ModifyOutsourcingModel;
import com.certiware.backend.model.project.SelectDetailModel;
import com.certiware.backend.model.project.SelectListModel;
import com.certiware.backend.model.project.SelectProjectListModel;

@Service
public class ProjectService {
	
	@Autowired
	CommonService commonService;
	@Autowired
	ProjectMapper projectMapper;
	
	/**
	 * 
	 * @param selectListModel
	 * @return
	 * @throws Exception
	 */
	public List<SelectListModel> selectList(SelectListModel selectListModel) throws Exception{
		
		return projectMapper.selectList(selectListModel);
	}
	
	/**
	 * 프로젝트 디테일 정보
	 * 프로젝트정보 + 외주정보
	 * @param selectDetailModel
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	public SelectDetailModel selectDetail(SelectDetailModel selectDetailModel, int projectId) throws Exception{
		
		// 프로젝트 정보 조회
		selectDetailModel.setProjectModel(projectMapper.selectProjectByPK(projectId));
		
		// 외주 정보 조회
		selectDetailModel.setOutsourcingModels(projectMapper.selectOutsourcingByProjectId(projectId));
		
		return selectDetailModel;
	}
	
	/**
	 * 프로젝트 정보 입력
	 * @param projectModel
	 * @return
	 * @throws Exception
	 */
	public int insertProject(ProjectModel projectModel) throws Exception{
		projectMapper.insertProject(projectModel);
		return 0;
	}
	
	/**
	 * 프로젝트 정보 변경
	 * @param projectModel
	 * @throws Exception
	 */
	public int updateProject(ProjectModel projectModel) throws Exception{
		return projectMapper.updateProjectByProjectId(projectModel);
	}
	
	/**
	 * 프로젝트 정보 삭제
	 * @param projectId
	 * @throws Exception
	 */
	public int deleteProject(int projectId) throws Exception{
		return projectMapper.deleteProjectByProjectId(projectId);
	}
	
	/**
	 * 외주 정보 수정
	 * @param modifyOutsourcingModel
	 * @return
	 * @throws Exception
	 */
	public int modifyOutsourcing(ModifyOutsourcingModel modifyOutsourcingModel) throws Exception{
		int result=0;
		
		//merge
		result=+projectMapper.mergeOutsourcing(modifyOutsourcingModel.getMergeOutsourcingModels());
		
		//delete
		result=+projectMapper.deleteOutsourcing(modifyOutsourcingModel.getDeleteOutsourcingModels());
		
		return result;
	}
	
	/**
	 * 본부에 속해있는 프로젝트 목록을 가져온다.
	 * @param deptCode:본부코드
	 * @return
	 * @throws Exception
	 */
	public List<SelectProjectListModel> selectProjectList(String deptCode) throws Exception{
		
		return projectMapper.selectProjectByDeptCode(deptCode);	
	}
}
