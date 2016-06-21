package com.certiware.backend.controller;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.common.OutsourcingModel;
import com.certiware.backend.model.project.ProjectDetailCodeModel;
import com.certiware.backend.model.project.ProjectDetailDataModel;
import com.certiware.backend.service.ProjectService;

import scala.collection.parallel.ParIterableLike.Foreach;

@RestController
@RequestMapping(value="/project")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@RequestMapping(value="/projectDetailCode")
	public ProjectDetailCodeModel projectDetailCode() throws ServletException{
		ProjectDetailCodeModel projectDetailCodeModel = new ProjectDetailCodeModel();	
		
		try{
			
			projectDetailCodeModel.setPartnerCodeModels(projectService.SelectPartnerCode());
			
		}catch(Exception e)
		{
			System.out.println("error!! :" + e.toString());
			throw new ServletException(e.toString());
		}
		
		return projectDetailCodeModel;
	}
	
	@RequestMapping(value="/projectDetailSave")
	public String projectDetailSave(@RequestBody ProjectDetailDataModel projectDetailDataModel) throws ServletException{
		
		int projectId;
		
		try{
			
			projectId = projectService.insertProject(projectDetailDataModel.getProjectModel()).getProjectId();
			
			for (OutsourcingModel outsourcingModel : projectDetailDataModel.getOutsourcings()) {				
				outsourcingModel.setProjectId(projectId);				
			}
			
			projectService.insertOutsourcing(projectDetailDataModel.getOutsourcings());
			
		}catch(Exception e)
		{
			System.out.println("error!! :" + e.toString());
			throw new ServletException(e.toString());
		}
		
		return "projectDetailSave Success!";
	}
	
}
