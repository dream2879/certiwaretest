package com.certiware.backend.controller;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.common.PartnerModel;
import com.certiware.backend.model.common.ProjectModel;
import com.certiware.backend.model.common.ResultModel;
import com.certiware.backend.model.project.ModifyOutsourcingModel;
import com.certiware.backend.model.project.SelectDetailModel;
import com.certiware.backend.model.project.SelectListModel;
import com.certiware.backend.model.project.SelectProjectListModel;
import com.certiware.backend.service.ProjectService;

@RestController
@RequestMapping(value="/project")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	/**
	 * 프로젝트 리스트를 조회한다.
	 * 프로젝트명, 매출처명, 본부코드에 따라서 조회조건이 달라진다.
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/selectList")
	public List<SelectListModel> selectList(@RequestBody SelectListModel selectListModel) throws ServletException{
		
		System.out.println("selectList() start...");
		
		List<SelectListModel> selectListModels = null;
		
		try{
			
			selectListModels  = projectService.selectList(selectListModel);
			
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
	public SelectDetailModel selectDetail(@RequestBody int projectId) throws ServletException{
		
		System.out.println("selectDetail() start...");
		
		SelectDetailModel selectDetailModel = new SelectDetailModel();
		
		try{
			
			selectDetailModel = projectService.selectDetail(selectDetailModel, projectId);
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectDetail() start...");
		
		return selectDetailModel;
				
		
	}//end selectDetail
	
	/**
	 * 프로젝트 정보를 입력한다.
	 * @param projectDetailDataModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/insertProject", method=RequestMethod.POST)
	public ResultModel insertProject(@RequestBody ProjectModel projectModel) throws ServletException{
		
		System.out.println("insertProject() start...");
		
		ResultModel resultModel = new ResultModel();		
		
		try{
			
			resultModel.setResult(projectService.insertProject(projectModel));
			
		}catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			
			System.out.println("error!! :" + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("insertProject() end... ");
		return resultModel;
	}//end insertProject()
	
	/**
	 * 
	 * @param updateProjectModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/updateProject", method=RequestMethod.POST)
	public ResultModel updateProject(@RequestBody ProjectModel projectModel) throws ServletException{
		
		System.out.println("updateProject() start... ");		
		ResultModel resultModel= new ResultModel();
		
		try{
			resultModel.setResult(projectService.updateProject(projectModel));			
		
		}catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			
			System.out.println("error!! :" + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("updateProject() end... ");
		return resultModel;
		
	}//end updateProject
	
	/**
	 * 
	 * @param projectId
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/deleteProject", method=RequestMethod.POST)
	public ResultModel deleteProject(@RequestBody int projectId) throws ServletException {
		
		System.out.println("deleteProject() start... ");		
		ResultModel resultModel = new ResultModel();
		
		try {
			
			resultModel.setResult(projectService.deleteProject(projectId));
			
		}catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error!! :" + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("deleteProject() end... ");
		return resultModel;
		
	}//end deleteProject()
	
	/**
	 * 
	 * @param modifyOutsourcingModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/modifyOutsourcing")
	public ResultModel modifyOutsourcing(@RequestBody ModifyOutsourcingModel modifyOutsourcingModel) throws ServletException{
		System.out.println("modifyOutsourcing() start... ");		
		ResultModel resultModel = new ResultModel();
		
		try{
			
			resultModel.setResult(projectService.modifyOutsourcing(modifyOutsourcingModel));
			
		}
		catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error!! :" + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("modifyOutsourcing() end... ");
		return resultModel;
		
	}//end modifyOutsourcing
	
	/**
	 * 본부에 속해있는 프로젝트 목록을 가져온다.
	 * @param deptCode:본부코드
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectProjectList")
	public List<SelectProjectListModel> selectProjectList(@RequestBody String deptCode) throws ServletException{
		System.out.println("selectProjectList() start... ");
		List<SelectProjectListModel> selectProjectListModels = null; 
		
		try{
			
			selectProjectListModels=projectService.selectProjectList(deptCode);
			
		}
		catch(Exception e)
		{
			System.out.println("error!! :" + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectProjectList() end... ");
		return selectProjectListModels;
		
	}// end selectProjectList

	
}//end class
