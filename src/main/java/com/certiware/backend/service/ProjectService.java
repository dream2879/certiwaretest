package com.certiware.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certiware.backend.mapper.ProjectMapper;
import com.certiware.backend.model.common.ManpowerModel;
import com.certiware.backend.model.common.OutsourcingModel;
import com.certiware.backend.model.common.ProjectModel;
import com.certiware.backend.model.project.InsertOutsourcingModel;
import com.certiware.backend.model.project.ModifyOutsourcingModel;
import com.certiware.backend.model.project.SelectDetailModel;
import com.certiware.backend.model.project.SelectListModel;
import com.certiware.backend.model.project.SelectOutsourcingModel;
import com.certiware.backend.model.project.SelectProjectListModel;

@Service
public class ProjectService {
	
	@Autowired
	CommonService commonService;
	@Autowired
	ProjectMapper projectMapper;
	@Autowired
	ProgressService progressService;
	
	/**
	 * TB_PROJECT 조회
	 * 조건에 따라 수행되는 쿼리문이 다르다.
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
		selectDetailModel.setSelectOutsourcingModel(projectMapper.selectOutsourcingByProjectId(projectId));
		
		return selectDetailModel;
	}
	
	/**
	 * 프로젝트 아이디로 외주 정보를 가져온다.
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	public List<SelectOutsourcingModel> selectOutsourcingList(int projectId) throws Exception{
		return projectMapper.selectOutsourcingByProjectId(projectId);
	}
	
	/**
	 * 프로젝트 정보 입력
	 * @param projectModel
	 * @return
	 * @throws Exception
	 */
	public boolean insertProject(ProjectModel projectModel) throws Exception{
		projectMapper.insertProject(projectModel);
		return true;
	}
	
	/**
	 * 프로젝트 정보 변경
	 * @param projectModel
	 * @throws Exception
	 */
	public boolean updateProject(ProjectModel projectModel) throws Exception{
		
		projectMapper.updateProjectByProjectId(projectModel);
		
		return true;
	}
	
	/**
	 * 프로젝트 정보 삭제
	 * @param projectId
	 * @throws Exception
	 */
	public boolean deleteProject(int projectId) throws Exception{
		
		projectMapper.deleteProjectByProjectId(projectId);
		
		return true;
	}
	
	/**
	 * 외주 정보 수정
	 * @param modifyOutsourcingModel
	 * @return
	 * @throws Exception
	*/
	@Transactional
	public boolean modifyOutsourcing(ModifyOutsourcingModel modifyOutsourcingModel) throws Exception{
			
//		//merge
//		for (SelectOutsourcingModel item : modifyOutsourcingModel.getMergeOutsourcingModels()) {			
//			projectMapper.mergeOutsourcing(item);			
//		}
//		
//		//delete
//		for (SelectOutsourcingModel item : modifyOutsourcingModel.getDeleteOutsourcingModels()) {
//			projectMapper.deleteOutsourcing(item);		
//		}				
		return true;
	}
	
	/**
	 * 아웃소싱 정보 입력
	 * @param outsourcingModel
	 * @return
	 * @throws Exception
	 */
	public boolean insertOutsourcing(InsertOutsourcingModel insertOutsourcingModel) throws Exception{
		
		// 외주 업체 등록
		projectMapper.inertOutsourcing(insertOutsourcingModel);
		
		// partnerCode가 3보다크면 개인사입자/프리랜서 임으로 TB_Manpower테이블에 등록해준다.
		if(Integer.parseInt(insertOutsourcingModel.getPartnerCode()) >= 3){
			ManpowerModel manpowerModel = new ManpowerModel();
			
			manpowerModel.setProjectId(insertOutsourcingModel.getProjectId());
			
			progressService.insertManpower(manpowerModel);
		}
		
		return true;
		
	}
	
	/**
	 * 아웃소싱 정보 업데이터
	 * @param outsourcingModel
	 * @return
	 * @throws Exception
	 */
	public boolean updateOutsourcing(OutsourcingModel outsourcingModel) throws Exception{
		
		projectMapper.updateOutsourcing(outsourcingModel);
		
		return true;
		
	}
	
	
	/**
	 * 아웃소싱 정보 삭제
	 * @param outsourcingModel
	 * @return
	 * @throws Exception
	 */
	public boolean deleteOutsourcing(OutsourcingModel outsourcingModel) throws Exception{
		
		// 아웃소싱 정보 삭제
		projectMapper.deleteOutsourcing(outsourcingModel);
		
		// 
		
		
		return true;
		
	}
	
	/**
	 * 본부에 속해있는 프로젝트 목록을 가져온다.
	 * @param deptCode:본부코드
	 * @return
	 * @throws Exception
	 */
	public List<SelectProjectListModel> selectProjectList(String deptCode) throws Exception{
		
		System.out.println("호출");
		
		return projectMapper.selectProjectByDeptCode(deptCode);	
	}
}
