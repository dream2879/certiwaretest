package com.certiware.backend.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certiware.backend.component.CommonComponent;
import com.certiware.backend.mapper.ProjectMapper;
import com.certiware.backend.model.common.ManpowerModel;
import com.certiware.backend.model.common.ProjectModel;
import com.certiware.backend.model.progress.UpdateManpowerModel;
import com.certiware.backend.model.project.ModifyOutsourcingModel;
import com.certiware.backend.model.project.SelectDetailModel;
import com.certiware.backend.model.project.SelectListReqModel;
import com.certiware.backend.model.project.SelectListResModel;
import com.certiware.backend.model.project.SelectOutsourcingResModel;
import com.certiware.backend.model.project.SelectProjectListReqModel;
import com.certiware.backend.model.project.SelectProjectListResModel;

@Service
public class ProjectService {
	
	@Autowired
	CommonService commonService;
	@Autowired
	ProjectMapper projectMapper;
	@Autowired
	ProgressService progressService;
	@Autowired
	CommonComponent commonComponent;
	
	/**
	 * TB_PROJECT 조회
	 * 조건에 따라 수행되는 쿼리문이 다르다.
	 * @param selectListModel
	 * @return
	 * @throws Exception
	 */
	public List<SelectListResModel> selectList(SelectListReqModel selectListReqModel) throws Exception{			
		
		// 시작일 종료일 설정
		Date date = selectListReqModel.getStartDate() == null ? Calendar.getInstance().getTime() : selectListReqModel.getStartDate();
		selectListReqModel.setStartDate(commonComponent.makeDate(date, "start"));
		selectListReqModel.setEndDate(commonComponent.makeDate(date, "end"));		
		
		return projectMapper.selectList(selectListReqModel);
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
	public List<SelectOutsourcingResModel> selectOutsourcingList(int projectId) throws Exception{
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
	 * 아웃소싱 정보 입력
	 * @param outsourcingModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean insertOutsourcing(ModifyOutsourcingModel modifyOutsourcingModel) throws Exception{
		
		// 외주업체 등록
		projectMapper.inertOutsourcing(modifyOutsourcingModel);
		
		// partnerCode가 3보다크면 개인사입자/프리랜서 임으로 TB_Manpower테이블에 등록해준다.
		if(Integer.parseInt(modifyOutsourcingModel.getPartnerCode()) >= 3){
			
			// 객체생성
			ManpowerModel manpowerModel = new ManpowerModel();			
			manpowerModel.setProjectId(modifyOutsourcingModel.getProjectId());
			manpowerModel.setPartnerId(modifyOutsourcingModel.getPartnerId());
			manpowerModel.setManpowerName(modifyOutsourcingModel.getPartnerName());
			manpowerModel.setRatingCode(modifyOutsourcingModel.getRatingCode());
			manpowerModel.setSellingAmount(modifyOutsourcingModel.getSellingAmount());			
			manpowerModel.setOutsourcingAmount(modifyOutsourcingModel.getOutsourcingAmount());
			manpowerModel.setStartDate(modifyOutsourcingModel.getStartDate());
			manpowerModel.setEndDate(modifyOutsourcingModel.getEndDate());
			manpowerModel.setRemarks(modifyOutsourcingModel.getRemarks());
			
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
	@Transactional
	public boolean updateOutsourcing(ModifyOutsourcingModel modifyOutsourcingModel) throws Exception{
		
		
		// 외주업체 변경
		projectMapper.updateOutsourcing(modifyOutsourcingModel);
		
		// partnerCode가 3보다크면 개인사입자/프리랜서 임으로 TB_Manpower테이블에 변경해준다.
		if(Integer.parseInt(modifyOutsourcingModel.getPartnerCode()) >= 3){
			
			// 객체생성
			UpdateManpowerModel updateManpowerModel = new UpdateManpowerModel();			
			updateManpowerModel.setProjectId(modifyOutsourcingModel.getProjectId());
			updateManpowerModel.setPartnerId(modifyOutsourcingModel.getPartnerId());
			updateManpowerModel.setManpowerName(modifyOutsourcingModel.getPartnerName());
			updateManpowerModel.setRatingCode(modifyOutsourcingModel.getRatingCode());
			updateManpowerModel.setSellingAmount(modifyOutsourcingModel.getSellingAmount());			
			updateManpowerModel.setOutsourcingAmount(modifyOutsourcingModel.getOutsourcingAmount());
			updateManpowerModel.setStartDate(modifyOutsourcingModel.getStartDate());
			updateManpowerModel.setEndDate(modifyOutsourcingModel.getEndDate());
			updateManpowerModel.setRemarks(modifyOutsourcingModel.getRemarks());
			updateManpowerModel.setPk1(modifyOutsourcingModel.getProjectId());
			updateManpowerModel.setPk2(modifyOutsourcingModel.getPartnerName());
			
			progressService.updateManpower(updateManpowerModel);
		}		
		
		return true;
		
	}	
	
	/**
	 * 아웃소싱 정보 삭제
	 * @param outsourcingModel
	 * @return
	 * @throws Exception
	 */
	public boolean deleteOutsourcing(ModifyOutsourcingModel modifyOutsourcingModel) throws Exception{
		
		// 아웃소싱 정보 삭제
		projectMapper.deleteOutsourcing(modifyOutsourcingModel);
		
		// TB_MANPOWER 정보는 DB에서 자동삭제한다		
		
		return true;
		
	}
	
	/**
	 * 본부에 속해있는 프로젝트 목록을 가져온다.
	 * @param deptCode:본부코드
	 * @return
	 * @throws Exception
	 */
	public List<SelectProjectListResModel> selectProjectList(SelectProjectListReqModel projectListReqModel) throws Exception{
		
		System.out.println("호출");
		
		// 시작일과 종료일 설정해준다.
		Date date = projectListReqModel.getStartDate() == null ? Calendar.getInstance().getTime() : projectListReqModel.getStartDate();
		projectListReqModel.setStartDate(commonComponent.makeDate(date, "start"));
		projectListReqModel.setEndDate(commonComponent.makeDate(date, "end"));
		
		System.out.println(projectListReqModel.getDeptCode());
		System.out.println(projectListReqModel.getStartDate());
		System.out.println(projectListReqModel.getEndDate());
		
		return projectMapper.selectProjectByDeptCode(projectListReqModel);	
	}
	
	
	
	
}
