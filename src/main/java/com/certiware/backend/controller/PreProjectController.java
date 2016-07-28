package com.certiware.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.common.ProjectModel;
import com.certiware.backend.model.common.ResultModel;
import com.certiware.backend.model.preproject.ModifyPreOutsourcingModel;
import com.certiware.backend.model.preproject.MovePreProjectReqModel;
import com.certiware.backend.model.preproject.SelectDetailModel;
import com.certiware.backend.model.preproject.SelectListReqModel;
import com.certiware.backend.model.preproject.SelectListResModel;
import com.certiware.backend.model.preproject.SelectPreOutsourcingResModel;
import com.certiware.backend.model.preproject.SelectPreProjectListReqModel;
import com.certiware.backend.model.preproject.SelectPreProjectListResModel;
import com.certiware.backend.service.PreProjectService;

@RestController
@RequestMapping(value="/preProject")
public class PreProjectController {
	
	@Autowired
	PreProjectService preProjectService;
	
	/**
	 * 프로젝트 리스트를 조회한다.
	 * 프로젝트명, 매출처명, 본부코드에 따라서 조회조건이 달라진다.
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/selectList")
	public List<SelectListResModel> selectList(@RequestBody SelectListReqModel selectListReqModel) throws ServletException{
		
		System.out.println("selectList() start...");
		
		List<SelectListResModel> selectListModels = null;
		
		try{
			
			selectListModels  = preProjectService.selectList(selectListReqModel);
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectList() end...");
		
		return selectListModels;
		
	}//end selectList()
	
	/**
	 * 프로젝트아이디를가지고 해당 프로젝트의 상세 정보 조회
	 * 프로젝트정보 + 외주정보
	 * @param projectId
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/selectDetail", method=RequestMethod.POST)
	public SelectDetailModel selectDetail(@RequestBody Map<String, String> json) throws ServletException{
		
		System.out.println("selectDetail() start...");		
		
		int projectId;
		SelectDetailModel selectDetailModel = new SelectDetailModel();
		
		try{
			
			projectId = Integer.parseInt(json.get("projectId"));
			
			selectDetailModel = preProjectService.selectDetail(selectDetailModel, projectId);
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectDetail() start...");
		
		return selectDetailModel;
				
		
	}//end selectDetail
	
	/**
	 * 프로젝트 아이디로 거래처 정보를 가져온다.
	 * @param json
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/selectPreOutsourcingList")
	public List<SelectPreOutsourcingResModel> selectPreOutsourcingList(@RequestBody Map<String, String> json) throws ServletException{
		System.out.println("selectPreOutsourcingList() start...");		
		
		int projectId;
		List<SelectPreOutsourcingResModel> selectOutsourcingModels = new ArrayList<>();
		
		try{
			
			projectId = Integer.parseInt(json.get("projectId"));
			
			selectOutsourcingModels = preProjectService.selectPreOutsourcingList(projectId);
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectPreOutsourcingList() start...");
		
		return selectOutsourcingModels;
				
		
	}//end selectOutsourcingList
	
	
	
	/**
	 * 프로젝트 정보를 입력한다.
	 * @param projectDetailDataModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/insertPreProject", method=RequestMethod.POST)
	public ResultModel insertPreProject(@RequestBody ProjectModel projectModel) throws ServletException{
		
		System.out.println("insertPreProject() start...");
		
		ResultModel resultModel = new ResultModel();		
		
		try{
			
			resultModel.setResult(preProjectService.insertPreProject(projectModel));
			
		}catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			
			System.out.println("error!! :" + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("insertPreProject() end... ");
		return resultModel;
	}//end insertProject()
	
	/**
	 * 
	 * @param updateProjectModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/updatePreProject", method=RequestMethod.POST)
	public ResultModel updateProject(@RequestBody ProjectModel projectModel) throws ServletException{
		
		System.out.println("updatePreProject() start... ");		
		ResultModel resultModel= new ResultModel();
		
		try{
			resultModel.setResult(preProjectService.updatePreProject(projectModel));			
		
		}catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			
			System.out.println("error!! :" + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("updatePreProject() end... ");
		return resultModel;
		
	}//end updateProject
	
	/**
	 * 
	 * @param projectId
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/deletePreProject", method=RequestMethod.POST)
	public ResultModel deletePreProject(@RequestBody Map<String, String> json) throws ServletException {
		
		System.out.println("deletePreProject() start... ");	
		int projectId;
		ResultModel resultModel = new ResultModel();
		
		try {
			
			projectId = Integer.parseInt(json.get("projectId"));
			
			resultModel.setResult(preProjectService.deletePreProject(projectId));
			
		}catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error!! :" + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("deletePreProject() end... ");
		return resultModel;
		
	}//end deleteProject()
	
	
	/**
	 * 외주정보 입력
	 * @param outsourcingModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/insertPreOutsourcing")
	public ResultModel insertPreOutsourcing(@RequestBody ModifyPreOutsourcingModel insertOutsourcingModel) throws ServletException{
		System.out.println("insertPreOutsourcing() start... ");		
		ResultModel resultModel = new ResultModel();
		
		try{
			
			resultModel.setResult(preProjectService.insertPreOutsourcing(insertOutsourcingModel));
			
		}
		catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error!! :" + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("insertPreOutsourcing() end... ");
		return resultModel;
		
	}//end insertOutsourcing	
	
	/**
	 * 외주정보 변경
	 * @param outsourcingModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/updatePreOutsourcing")
	public ResultModel updatePreOutsourcing(@RequestBody ModifyPreOutsourcingModel modifyOutsourcingModel) throws ServletException{
		System.out.println("updatePreOutsourcing() start... ");		
		ResultModel resultModel = new ResultModel();
		
		try{
			
			resultModel.setResult(preProjectService.updatePreOutsourcing(modifyOutsourcingModel));
			
		}
		catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error!! :" + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("updatePreOutsourcing() end... ");
		return resultModel;
		
	}//end updateOutsourcing
	
	
	/**
	 * 외주정보 삭제
	 * @param outsourcingModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/deletePreOutsourcing")
	public ResultModel deletePreOutsourcing(@RequestBody ModifyPreOutsourcingModel modifyOutsourcingModel) throws ServletException{
		System.out.println("deletePreOutsourcing() start... ");		
		ResultModel resultModel = new ResultModel();
		
		try{
			
			resultModel.setResult(preProjectService.deletePreOutsourcing(modifyOutsourcingModel));
			
		}
		catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error!! :" + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("deletePreOutsourcing() end... ");
		return resultModel;
		
	}//end deleteOutsourcing
	
	
	/**
	 * 본부에 속해있는 프로젝트 목록을 가져온다.
	 * @param deptCode:본부코드
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectPreProjectList")
	public List<SelectPreProjectListResModel> selectPreProjectList(@RequestBody SelectPreProjectListReqModel projectListReqModel) throws ServletException{
		System.out.println("selectPreProjectList() start... ");
		List<SelectPreProjectListResModel> selectProjectListModels = null; 
		
		try{		
			
			// 서비스 호출
			selectProjectListModels=preProjectService.selectPreProjectList(projectListReqModel);
			
		}
		catch(Exception e)
		{
			System.out.println("error!! :" + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectPreProjectList() end... ");
		return selectProjectListModels;
		
	}// end selectProjectList
	
	
	/**
	 * 사전등록 프로젝트 정보를 프로젝트로 이동한다.
	 * @param movePreProjectReqModel
	 * @return
	 * @throws Exception
	 */
	public ResultModel movePreProject(@RequestBody MovePreProjectReqModel movePreProjectReqModel) throws Exception{
		
		System.out.println("movePreProject() start...");
		ResultModel resultModel = new ResultModel();
		
		try{
			
			// 서비스 호출
			resultModel.setResult(preProjectService.movePreProject(movePreProjectReqModel));
			
		}
		catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error!! :" + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("movePreProject() end... ");
		
		return resultModel;
	
	}// end movePreProject

}// end class
