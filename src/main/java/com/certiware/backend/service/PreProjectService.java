package com.certiware.backend.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certiware.backend.component.CommonComponent;
import com.certiware.backend.mapper.PreProjectMapper;
import com.certiware.backend.model.common.ManpowerModel;
import com.certiware.backend.model.common.ProjectModel;
import com.certiware.backend.model.preproject.ModifyPreOutsourcingModel;
import com.certiware.backend.model.preproject.MovePreProjectReqModel;
import com.certiware.backend.model.preproject.PreManpowerModel;
import com.certiware.backend.model.preproject.SelectDetailModel;
import com.certiware.backend.model.preproject.SelectListReqModel;
import com.certiware.backend.model.preproject.SelectListResModel;
import com.certiware.backend.model.preproject.SelectPreOutsourcingResModel;
import com.certiware.backend.model.preproject.SelectPreProjectListReqModel;
import com.certiware.backend.model.preproject.SelectPreProjectListResModel;
import com.certiware.backend.model.preproject.UpdatePreManpowerModel;
import com.certiware.backend.model.progress.ManpowerNameModel;
import com.certiware.backend.model.progress.SelectManpowerListModel;
import com.certiware.backend.model.progress.SelectManpowerListReqModel;
import com.certiware.backend.model.progress.UpdateManpowerModel;

@Service
public class PreProjectService {
	

	@Autowired
	CommonService commonService;
	@Autowired
	PreProjectMapper preProjectMapper;	
	@Autowired
	CommonComponent commonComponent;
	@Autowired
	ProgressService progressService;
	
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
		
		return preProjectMapper.selectList(selectListReqModel);
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
		selectDetailModel.setProjectModel(preProjectMapper.selectPreProjectByPK(projectId));
		
		// 외주 정보 조회
		selectDetailModel.setSelectOutsourcingModel(preProjectMapper.selectPreOutsourcingByProjectId(projectId));
		
		return selectDetailModel;
	}
	
	/**
	 * 프로젝트 아이디로 외주 정보를 가져온다.
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	public List<SelectPreOutsourcingResModel> selectPreOutsourcingList(int projectId) throws Exception{
		return preProjectMapper.selectPreOutsourcingByProjectId(projectId);
	}
	
	/**
	 * 프로젝트 정보 입력
	 * @param projectModel
	 * @return
	 * @throws Exception
	 */
	public boolean insertPreProject(ProjectModel projectModel) throws Exception{
		preProjectMapper.insertPreProject(projectModel);
		
		
		
		
		return true;
	}
	
	/**
	 * 프로젝트 정보 변경
	 * @param projectModel
	 * @throws Exception
	 */
	public boolean updatePreProject(ProjectModel projectModel) throws Exception{
		
		preProjectMapper.updatePreProjectByProjectId(projectModel);
		
		return true;
	}
	
	/**
	 * 프로젝트 정보 삭제
	 * @param projectId
	 * @throws Exception
	 */
	public boolean deletePreProject(int projectId) throws Exception{
		
		preProjectMapper.deletePreProjectByProjectId(projectId);
		
		return true;
	}
	

	
	/**
	 * 아웃소싱 정보 입력
	 * @param outsourcingModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean insertPreOutsourcing(ModifyPreOutsourcingModel modifyOutsourcingModel) throws Exception{
		
		// 외주업체 등록
		preProjectMapper.inertPreOutsourcing(modifyOutsourcingModel);
		
		// partnerCode가 3보다크면 개인사입자/프리랜서 임으로 TB_Manpower테이블에 등록해준다.
		if(Integer.parseInt(modifyOutsourcingModel.getPartnerCode()) >= 3){
			
			// 객체생성
			PreManpowerModel manpowerModel = new PreManpowerModel();			
			manpowerModel.setProjectId(modifyOutsourcingModel.getProjectId());
			manpowerModel.setPartnerId(modifyOutsourcingModel.getPartnerId());
			manpowerModel.setManpowerName(modifyOutsourcingModel.getPartnerName());
			manpowerModel.setRatingCode(modifyOutsourcingModel.getRatingCode());
			manpowerModel.setSellingAmount(modifyOutsourcingModel.getSellingAmount());			
			manpowerModel.setOutsourcingAmount(modifyOutsourcingModel.getOutsourcingAmount());
			manpowerModel.setStartDate(modifyOutsourcingModel.getStartDate());
			manpowerModel.setEndDate(modifyOutsourcingModel.getEndDate());
			manpowerModel.setRemarks(modifyOutsourcingModel.getRemarks());
			
			this.insertManpower(manpowerModel);
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
	public boolean updatePreOutsourcing(ModifyPreOutsourcingModel modifyOutsourcingModel) throws Exception{
		
		
		// 외주업체 변경
		preProjectMapper.updatePreOutsourcing(modifyOutsourcingModel);
		
		// partnerCode가 3보다크면 개인사입자/프리랜서 임으로 TB_Manpower테이블에 변경해준다.
		if(Integer.parseInt(modifyOutsourcingModel.getPartnerCode()) >= 3){
			
			// 객체생성
			UpdatePreManpowerModel updateManpowerModel = new UpdatePreManpowerModel();			
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
			
			this.updateManpower(updateManpowerModel);
		}
		
		return true;
		
	}	
	
	/**
	 * 아웃소싱 정보 삭제
	 * @param outsourcingModel
	 * @return
	 * @throws Exception
	 */
	public boolean deletePreOutsourcing(ModifyPreOutsourcingModel modifyOutsourcingModel) throws Exception{
		
		// 아웃소싱 정보 삭제
		preProjectMapper.deletePreOutsourcing(modifyOutsourcingModel);
		
		// TB_MANPOWER 정보는 DB에서 자동삭제한다		
		
		return true;
		
	}
	
	/**
	 * 본부에 속해있는 프로젝트 목록을 가져온다.
	 * @param deptCode:본부코드
	 * @return
	 * @throws Exception
	 */
	public List<SelectPreProjectListResModel> selectPreProjectList(SelectPreProjectListReqModel projectListReqModel) throws Exception{
		
		System.out.println("호출");
		
		// 시작일과 종료일 설정해준다.
		Date date = projectListReqModel.getStartDate() == null ? Calendar.getInstance().getTime() : projectListReqModel.getStartDate();
		projectListReqModel.setStartDate(commonComponent.makeDate(date, "start"));
		projectListReqModel.setEndDate(commonComponent.makeDate(date, "end"));
		
		System.out.println(projectListReqModel.getDeptCode());
		System.out.println(projectListReqModel.getStartDate());
		System.out.println(projectListReqModel.getEndDate());
		
		return preProjectMapper.selectPreProjectByDeptCode(projectListReqModel);	
	}
	
	/**
	 * TB_MANPOWER 테이블 INSERT
	 * @param manpowerModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean insertManpower(PreManpowerModel preManpowerModel) throws Exception{	
	
			// TB_MANPWER 테이블에 정보를 입력한다.
		preProjectMapper.insertePreManpower(preManpowerModel);
		
		return true;
	}
	
	/**
	 * TB_MANPOWER 테이블 UPDATE
	 * @param modifyManpowerModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean updateManpower(UpdatePreManpowerModel updateManpowerModel) throws Exception{
						
		// TB_MANPWER 테이블에 정보를 변경한다.
		preProjectMapper.updatePreManpower(updateManpowerModel);
		
		return true;
	}
	
	
	/**
	 * TB_MANPOWER 테이블조회
	 * @param projectId:프로젝트아이디
	 * @return
	 * @throws Exception
	 */
	public SelectManpowerListModel selectManpowerList(SelectManpowerListModel selectManpowerListModel, SelectManpowerListReqModel selectManpowerListReqModel) throws Exception{
			
		// 투입인력 이름목록을 가져온다.
		selectManpowerListModel.setManpowerNameModels(preProjectMapper.selectManpowerByProjectId(selectManpowerListReqModel));		
		
		return selectManpowerListModel;
	}
	
	
	/**
	 * 사전등록 프로젝트 정보를 진행 프로젝트 정보로 옮긴다.
	 * @param movePreProjectReqModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean movePreProject(MovePreProjectReqModel movePreProjectReqModel) throws Exception{
		
		// 사전등록된 프로젝트 아이디
		int projectId = movePreProjectReqModel.getProjectId();
		
		// INSERT TB_PREPROJECT > TB_PROJECT
		preProjectMapper.insertProjectPreProject(movePreProjectReqModel);
		
		// INSERT TB_PREOUTSOURCING > TB_OUTSOURCING
		preProjectMapper.insertOutsourcingPreOutsourcing(movePreProjectReqModel.getGeneratedProjectId());		
	
		// 사전 등록된 TB_PREMANPOWER 정보를 가져온다.
		SelectManpowerListReqModel selectManpowerListReqModel = new SelectManpowerListReqModel();
		selectManpowerListReqModel.setProjectId(projectId);
		List<ManpowerNameModel> manpowerNameModels = preProjectMapper.selectManpowerByProjectId(selectManpowerListReqModel);
		
		// 사전등록 된 MANPOWER정보를 입력한다.
		for (ManpowerNameModel manpowerNameModel : manpowerNameModels) {
			
			ManpowerModel manpowerModel = new ManpowerModel();
			manpowerModel.setProjectId(projectId);
			manpowerModel.setManpowerName(manpowerNameModel.getManpowerName());
			manpowerModel.setPartnerId(manpowerNameModel.getPartnerId());
			manpowerModel.setRatingCode(manpowerNameModel.getRatingCode());
			manpowerModel.setSellingAmount(manpowerNameModel.getSellingAmount());
			manpowerModel.setOutsourcingAmount(manpowerNameModel.getOutsourcingAmount());
			manpowerModel.setStartDate(manpowerNameModel.getStartDate());
			manpowerModel.setEndDate(manpowerNameModel.getEndDate());
			manpowerModel.setRemarks(manpowerNameModel.getRemarks());	
			
			progressService.insertManpower(manpowerModel);
			
		}
		
		
	
		// DELETE TB_PREPROJECT (TB_PREOUTSOURCING 정보는 자동 삭제)
		this.deletePreProject(movePreProjectReqModel.getProjectId());
		
		return true;		
	}// end movePreProject

}
