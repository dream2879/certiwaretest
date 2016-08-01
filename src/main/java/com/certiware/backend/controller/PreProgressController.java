package com.certiware.backend.controller;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.progress.SelectManpowerListModel;
import com.certiware.backend.model.progress.SelectManpowerListReqModel;
import com.certiware.backend.service.PreProjectService;


@RestController
@RequestMapping(value="/preProgress")
class PreProgressController {
	
	@Autowired
	PreProjectService PreProjectService;
	
	/**
	 * 프로젝트 투입인력의 목록을 조회한다.
	 * @param projectId
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectPreManpowerList")
	public SelectManpowerListModel selectManpowerList(@RequestBody SelectManpowerListReqModel selectManpowerListReqModel) throws ServletException{
		System.out.println("selectManpowerList() start...");
		
		SelectManpowerListModel selectManpowerListModel = new SelectManpowerListModel();
				
		try{
			
			// 필수값 체크
			if(selectManpowerListReqModel.getProjectId() == 0){
				
				throw new ServletException("필수값 없음");				
			}
			
			// 서비스 호출
			selectManpowerListModel = PreProjectService.selectManpowerList(selectManpowerListModel, selectManpowerListReqModel);			
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectManpowerList() end...");
		
		return selectManpowerListModel;
	}// end selectManpowerList

}
