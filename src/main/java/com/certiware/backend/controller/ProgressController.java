package com.certiware.backend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.common.ManpowerMmModel;
import com.certiware.backend.model.common.ManpowerModel;
import com.certiware.backend.model.common.ResultModel;
import com.certiware.backend.model.progress.ProjectPartnerModel;
import com.certiware.backend.model.progress.SelectManpowerListModel;
import com.certiware.backend.model.progress.SelectManpowerListReqModel;
import com.certiware.backend.model.progress.SelectManpowerMMHistoryReqModel;
import com.certiware.backend.model.progress.SelectManpowerMMHistoryResModel;
import com.certiware.backend.model.progress.SelectPartnerNameList;
import com.certiware.backend.model.progress.SelectProgressListReqModel;
import com.certiware.backend.model.progress.SelectProgressListResModel;
import com.certiware.backend.model.progress.UpdateManpowerMmReqModel;
import com.certiware.backend.model.progress.UpdateManpowerModel;
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
	public SelectManpowerListModel selectManpowerList(@RequestBody SelectManpowerListReqModel selectManpowerListReqModel) throws ServletException{
		System.out.println("selectManpowerList() start...");
		
		SelectManpowerListModel selectManpowerListModel = new SelectManpowerListModel();
				
		try{
			
			// 필수값 체크
			if(selectManpowerListReqModel.getProjectId() == 0){
				
				throw new ServletException("필수값 없음");				
			}
			
			// 서비스 호출
			selectManpowerListModel = progressService.selectManpowerList(selectManpowerListModel, selectManpowerListReqModel);			
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectManpowerList() end...");
		
		return selectManpowerListModel;
	}// end selectManpowerList
	
	/**
	 * 투입인력 정보 입력
	 * @param manpowerModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/insertManpower")
	public ResultModel insertManpower(@RequestBody ManpowerModel manpowerModel) throws ServletException{
		System.out.println("insertManpower() start...");
		ResultModel resultModel = new ResultModel();
				
		try{
			resultModel.setResult(progressService.insertManpower(manpowerModel));
			
			
		}catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("insertManpower() end...");
		
		return resultModel;
	}// end insertManpower
	
	
	/**
	 * 프로젝트 투입인력 정보를 수정한다.
	 * @param modifyManpowerModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/updateManpower")
	public ResultModel updateManpower(@RequestBody UpdateManpowerModel updateManpowerModel) throws ServletException{
		System.out.println("modifyManpower() start...");
		ResultModel resultModel = new ResultModel();
				
		try{
			// 서비스호출
			resultModel.setResult(progressService.updateManpower(updateManpowerModel));
			
			
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
	 * 프로젝트 투입인력 삭제한다.
	 * @param modifyManpowerModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/deleteManpower")
	public ResultModel deleteManpower(@RequestBody ManpowerModel manpowerModel) throws ServletException{
		System.out.println("deleteManpower() start...");
		ResultModel resultModel = new ResultModel();
				
		try{
			resultModel.setResult(progressService.deleteManpower(manpowerModel));
			
			
		}catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("deleteManpower() end...");
		
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
		
		int projectId;
		String manpowerName;
		List<ManpowerMmModel> manpowerMmModels = null;
				
		try{
			
			projectId = Integer.parseInt(json.get("projectId"));
			manpowerName = json.get("manpowerName");
			
			manpowerMmModels = progressService.selectManpowerMmList(projectId, manpowerName);	
			
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
	@RequestMapping("/updateManpowerMm")
	public ResultModel updateManpowerMm(@RequestBody UpdateManpowerMmReqModel updateManpowerMmReqModel) throws ServletException{
		System.out.println("updateManpowerMm() start...");
		ResultModel resultModel = new ResultModel();		
				
		try{
			// 서비스 호출
			resultModel.setResult(progressService.updateManpowerMm(updateManpowerMmReqModel)); 
			
		}catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("updateManpowerMm() end...");
		
		return resultModel;
	}// end insertUser
	
	/**
	 * 프로젝트 진행현황을 조회한다.
	 * @param selectProgressListReqModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="selectProgressList")
	public List<SelectProgressListResModel> selectProgressList(@RequestBody SelectProgressListReqModel selectProgressListReqModel) throws ServletException{
		System.out.println("selectProgressList() end...");
		List<SelectProgressListResModel> selectProgressListResModels = new ArrayList<>();
		
		try{
			
			selectProgressListResModels =  progressService.selectProgressList(selectProgressListReqModel);
			
		}
		catch(Exception e)
		{			
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("---------selectProgressListResModel toString----------------------------");
		for (int i = 0; i < selectProgressListResModels.size(); i++) {			
			System.out.println(selectProgressListResModels.get(i).toString());
		}
		System.out.println("------------------------------------------------------------------------");
		System.out.println("selectProgressList() end...");
		
		return selectProgressListResModels;
	}
	
	
	/**
	 * 프로젝트아이디로 외주정보를 가져온다.
	 * @param json
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/SelectPartnerNameList")
	public List<SelectPartnerNameList> selectPartnerNameList(@RequestBody Map<String, String> json) throws ServletException{
		System.out.println("selectManpowerMmList() start...");
		
		int projectId;		
		List<SelectPartnerNameList> selectPartnerNameLists = null;
		
				
		try{
			
			projectId = Integer.parseInt(json.get("projectId"));			
			
			selectPartnerNameLists = progressService.SelectPartnerNameList(projectId);
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}

		System.out.println("selectManpowerMmList() end...");
		
		return selectPartnerNameLists;
	}
	
	/**
	 * 프로젝트 진행 현황 화면을 엑셀로 출력한다.
	 * @param json
	 * @return
	 * @throws ServletException
	 * @throws IOException 
	 */	
	@RequestMapping(value = "/excelDownload" , method = RequestMethod.POST)
	public void excelDownload(@RequestBody SelectProgressListReqModel selectProgressListReqModel, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("excelDownload() start...");			

		
		HSSFWorkbook wb = new HSSFWorkbook();   // workbook 생성
				
		try{	
			
			// 서비스 호출
			wb = progressService.excelDownload(selectProgressListReqModel);				
            				
			// response 헤더 설정
			String mimeType = "application/vnd.ms-excel;charset=charset=utf-8";			
			response.setContentType(mimeType);		
			response.setHeader( "Content-disposition", "attachment; filename=myfile.xlsx" );
			
			// clinet로 파일 전달.
			wb.write(response.getOutputStream());
			
			// 버퍼를 닫는다
			response.flushBuffer();	
	
            
		}catch(Exception e)
		{
			System.out.println("error : " + e.getStackTrace());
			throw new ServletException(e.toString());
		}
		finally {
			
		}
		
		System.out.println("excelDownload() end...");		
	}
	
	/**
	 * 외주업체 목록을 조회해온다.
	 * 개인사업자/프리랜서 제외
	 * @param json
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectOutsourcingList")
	public List<ProjectPartnerModel> selectOutsourcingList(@RequestBody Map<String, String> json) throws ServletException{
		System.out.println("selectOutsourcingList() start...");		
		
		int projectId;
		List<ProjectPartnerModel> projectPartnerModels = new ArrayList<>();
		
		try{
			// 프로젝트 아이디 추출
			projectId = Integer.parseInt(json.get("projectId"));
			
			projectPartnerModels = progressService.selectOutsourcingList(projectId);
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectOutsourcingList() start...");
		
		return projectPartnerModels;				
		
	}//end selectOutsourcingList
	
	
	/**
	 * M/M 수정이력을 가져온다.
	 * @param selectManpowerMMHistoryReqModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectManpowerMMHistory")
	public List<SelectManpowerMMHistoryResModel> selectManpowerMMHistory(@RequestBody SelectManpowerMMHistoryReqModel selectManpowerMMHistoryReqModel) throws Exception{
		
		System.out.println("selectManpowerMMHistory() start...");
		
		List<SelectManpowerMMHistoryResModel> selectManpowerMMHistoryResModels = new ArrayList<>();
		
		try{
			
			// 서비스 호출
			selectManpowerMMHistoryResModels = progressService.selectManpowerMMHistory(selectManpowerMMHistoryReqModel);
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectManpowerMMHistory() start...");
		
		return selectManpowerMMHistoryResModels;	
	
	}
}
