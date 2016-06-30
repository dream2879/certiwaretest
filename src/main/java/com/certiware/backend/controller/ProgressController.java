package com.certiware.backend.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.common.ManpowerMmModel;
import com.certiware.backend.model.common.ManpowerModel;
import com.certiware.backend.model.common.ResultModel;
import com.certiware.backend.model.progress.ModifyManpowerMmModel;
import com.certiware.backend.model.progress.ModifyManpowerModel;
import com.certiware.backend.service.ProgressService;

@RestController
@RequestMapping(value="/progress")
public class ProgressController {
	
	@Autowired
	ProgressService progressService;
	
	/**
	 * 프로젝트 투입인력의 목록을 조회한다.
	 * @param projectId
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectManpowerList")
	public List<ManpowerModel> selectManpowerList(@RequestBody int projectId) throws ServletException{
		System.out.println("selectManpowerList() start...");
		List<ManpowerModel> manpowerModels = null;
				
		try{
			
			manpowerModels = progressService.selectManpowerList(projectId);			
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectManpowerList() end...");
		
		return manpowerModels;
	}// end selectManpowerList
	
	
	/**
	 * 프로젝트 투입인력 정보를 수정한다.
	 * @param modifyManpowerModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/modifyManpower")
	public ResultModel modifyManpower(@RequestBody ModifyManpowerModel modifyManpowerModel) throws ServletException{
		System.out.println("modifyManpower() start...");
		ResultModel resultModel = new ResultModel();
				
		try{
			resultModel.setResult(progressService.modifyManpower(modifyManpowerModel));
			
			
		}catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("modifyManpower() end...");
		
		return resultModel;
	}// end modifyManpower
	
	/**
	 * 프로젝트 투입인력의 M/M 정보를 가져온다.
	 * @param json
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectManpowerMmList")
	public List<ManpowerMmModel> selectManpowerMmList(@RequestBody Map<String, String> json) throws ServletException{
		System.out.println("selectManpowerMmList() start...");
		List<ManpowerMmModel> manpowerMmModels = null;
				
		try{
			
			manpowerMmModels = progressService.selectManpowerMmList(Integer.parseInt(json.get("projectId")), json.get("manpowerName"));	
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectManpowerMmList() end...");
		
		return manpowerMmModels;
	}// end selectManpowerMmList
	
	
	/**
	 * 프로젝트 투입인력의 M/M 정보를 수정한다.
	 * @param modifyManpowerMmModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/modifyManpowerMm")
	public ResultModel modifyManpowerMm(@RequestBody ModifyManpowerMmModel modifyManpowerMmModel) throws ServletException{
		System.out.println("modifyManpowerMm() start...");
		ResultModel resultModel = new ResultModel();		
				
		try{
			
			resultModel.setResult(progressService.modifyManpowerMm(modifyManpowerMmModel));
			
		}catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("modifyManpowerMm() end...");
		
		return resultModel;
	}// end insertUser

}
