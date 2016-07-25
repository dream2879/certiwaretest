package com.certiware.backend.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.certiware.backend.model.common.ManpowerMmModel;
import com.certiware.backend.model.common.ManpowerModel;
import com.certiware.backend.model.common.ResultModel;
import com.certiware.backend.model.progress.SelectPartnerNameList;
import com.certiware.backend.model.progress.SelectProgressListReqModel;
import com.certiware.backend.model.progress.SelectProgressListResModel;
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
	public List<ManpowerModel> selectManpowerList(@RequestBody Map<String, String> json) throws ServletException{
		System.out.println("selectManpowerList() start...");
		int projectId;
		List<ManpowerModel> manpowerModels = null;
				
		try{
			projectId = Integer.parseInt(json.get("projectId"));
			
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
	@RequestMapping("/modifyManpowerMm")
	public ResultModel modifyManpowerMm(@RequestBody List<ManpowerMmModel> manpowerMmModels) throws ServletException{
		System.out.println("modifyManpowerMm() start...");
		ResultModel resultModel = new ResultModel();		
				
		try{
			
			resultModel.setResult(progressService.modifyManpowerMm(manpowerMmModels));
			
		}catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("modifyManpowerMm() end...");
		
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
	 * 프로젝트아이디로 외주정보를 가져온다.
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
			
			// 버터를 닫는다
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
	
	
	//콤마추가
		private String insertComma(long num) {
			DecimalFormat decFormat = new DecimalFormat("###,###,###,###,###,###,###");
			return decFormat.format(num).toString();
		}

}
