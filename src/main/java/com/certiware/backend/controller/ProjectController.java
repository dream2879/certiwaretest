package com.certiware.backend.controller;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.common.ProjectModel;
import com.certiware.backend.model.project.SelectCodeModel;
import com.certiware.backend.model.project.SelectDetailModel;
import com.certiware.backend.model.project.SelectListModel;
import com.certiware.backend.service.ProjectService;

@RestController
@RequestMapping(value="/project")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	/**
	 * 
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/selectCode")
	public SelectCodeModel selectCode() throws ServletException{
		
		SelectCodeModel selectCodeModel = new SelectCodeModel();	
		
		try{
			
			selectCodeModel = projectService.selectCode(selectCodeModel);
			
		}catch(Exception e)
		{
			System.out.println("error!! :" + e.toString());
			throw new ServletException(e.toString());
		}
		
		return selectCodeModel;
	}//end selectCode()
	
	/**
	 * 
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/selectList")
	public List<SelectListModel> selectList() throws ServletException{
		
		System.out.println("selectList() start...");
		
		List<SelectListModel> selectListModels = null;
		
		try{
			
			selectListModels  = projectService.selectList();
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectList() end...");
		
		return selectListModels;
		
	}//end selectList()
	
	
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
	 * 
	 * @param projectDetailDataModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/insertProject", method=RequestMethod.POST)
	public String insertProject(@RequestBody ProjectModel projectModel) throws ServletException{
		
		System.out.println("insertProject() start...");
		
		int projectId;
		
		try{
			
			projectId = projectService.insertProject(projectModel);
			
			/*
			for (OutsourcingModel outsourcingModel : projectDetailDataModel.getOutsourcings()) {				
				outsourcingModel.setProjectId(projectId);				
			}
			
			projectService.insertOutsourcing(projectDetailDataModel.getOutsourcings());
			*/
			
		}catch(Exception e)
		{
			System.out.println("error!! :" + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("insertProject() end... " + projectId);
		return "projectDetailSave Success!";
	}//end insertProject()
	
	/**
	 * 
	 * @param updateProjectModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/updateProject", method=RequestMethod.POST)
	public int updateProject(@RequestBody ProjectModel projectModel) throws ServletException{
		
		System.out.println("updateProject() start... ");		
		int result=0;
		
		try{
			result = projectService.updateProject(projectModel);			
		
		}catch(Exception e)
		{
			System.out.println("error!! :" + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("updateProject() end... ");
		return result;
		
	}//end updateProject
	
	/**
	 * 
	 * @param projectId
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/deleteProject", method=RequestMethod.POST)
	public int deleteProject(@RequestBody int projectId) throws ServletException {
		
		System.out.println("deleteProject() start... ");		
		int result=0;
		
		try {
			
			result=projectService.deleteProject(projectId);
			
		}catch(Exception e)
		{
			System.out.println("error!! :" + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("deleteProject() end... ");
		return result;
		
	}//end deleteProject()
	
}//end class
