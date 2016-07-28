package com.certiware.backend.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certiware.backend.component.CommonComponent;
import com.certiware.backend.mapper.PreProjectMapper;
import com.certiware.backend.model.common.ProjectModel;
import com.certiware.backend.model.preproject.ModifyPreOutsourcingModel;
import com.certiware.backend.model.preproject.MovePreProjectReqModel;
import com.certiware.backend.model.preproject.SelectDetailModel;
import com.certiware.backend.model.preproject.SelectListReqModel;
import com.certiware.backend.model.preproject.SelectListResModel;
import com.certiware.backend.model.preproject.SelectPreOutsourcingResModel;
import com.certiware.backend.model.preproject.SelectPreProjectListReqModel;
import com.certiware.backend.model.preproject.SelectPreProjectListResModel;

@Service
public class PreProjectService {
	

	@Autowired
	CommonService commonService;
	@Autowired
	PreProjectMapper preProjectMapper;	
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
	 * 사전등록 프로젝트 정보를 진행 프로젝트 정보로 옮긴다.
	 * @param movePreProjectReqModel
	 * @return
	 * @throws Exception
	 */
	public boolean movePreProject(MovePreProjectReqModel movePreProjectReqModel) throws Exception{		
		
		// INSERT TB_PREPROJECT > TB_PROJECT
		preProjectMapper.insertProjectPreProject(movePreProjectReqModel);
		
		// INSERT TB_PREOUTSOURCING > TB_OUTSOURCING
		preProjectMapper.insertOutsourcingPreOutsourcing(movePreProjectReqModel.getGeneratedProjectId());		
	
		// DELETE TB_PREPROJECT (TB_PREOUTSOURCING 정보는 자동 삭제)
		this.deletePreProject(movePreProjectReqModel.getProjectId());
		
		return true;		
	}// end movePreProject

}
