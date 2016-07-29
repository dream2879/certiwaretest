package com.certiware.backend.controller;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.common.ProjectModel;
import com.certiware.backend.model.common.ResultModel;
import com.certiware.backend.model.preproject.MakeContractReqModel;
import com.certiware.backend.model.project.ModifyOutsourcingModel;
import com.certiware.backend.model.project.SelectDetailModel;
import com.certiware.backend.model.project.SelectListReqModel;
import com.certiware.backend.model.project.SelectListResModel;
import com.certiware.backend.model.project.SelectOutsourcingResModel;
import com.certiware.backend.model.project.SelectProjectListReqModel;
import com.certiware.backend.model.project.SelectProjectListResModel;
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
	public List<SelectListResModel> selectList(@RequestBody SelectListReqModel selectListReqModel) throws ServletException{
		
		System.out.println("selectList() start...");
		
		List<SelectListResModel> selectListModels = null;
		
		try{
			
			selectListModels  = projectService.selectList(selectListReqModel);
			
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
	 * 프로젝트 아이디로 거래처 정보를 가져온다.
	 * @param json
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/selectOutsourcingList")
	public List<SelectOutsourcingResModel> selectOutsourcingList(@RequestBody Map<String, String> json) throws ServletException{
		System.out.println("selectOutsourcingList() start...");		
		
		int projectId;
		List<SelectOutsourcingResModel> selectOutsourcingModels = new ArrayList<>();
		
		try{
			
			projectId = Integer.parseInt(json.get("projectId"));
			
			selectOutsourcingModels = projectService.selectOutsourcingList(projectId);
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectOutsourcingList() start...");
		
		return selectOutsourcingModels;
				
		
	}//end selectOutsourcingList
	
	
	
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
	public ResultModel deleteProject(@RequestBody Map<String, String> json) throws ServletException {
		
		System.out.println("deleteProject() start... ");	
		int projectId;
		ResultModel resultModel = new ResultModel();
		
		try {
			
			projectId = Integer.parseInt(json.get("projectId"));
			
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
	 * 외주정보 입력
	 * @param outsourcingModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/insertOutsourcing")
	public ResultModel insertOutsourcing(@RequestBody ModifyOutsourcingModel insertOutsourcingModel) throws ServletException{
		System.out.println("insertOutsourcing() start... ");		
		ResultModel resultModel = new ResultModel();
		
		try{
			
			resultModel.setResult(projectService.insertOutsourcing(insertOutsourcingModel));
			
		}
		catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error!! :" + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("insertOutsourcing() end... ");
		return resultModel;
		
	}//end insertOutsourcing	
	
	/**
	 * 외주정보 변경
	 * @param outsourcingModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/updateOutsourcing")
	public ResultModel updateOutsourcing(@RequestBody ModifyOutsourcingModel modifyOutsourcingModel) throws ServletException{
		System.out.println("updateOutsourcing() start... ");		
		ResultModel resultModel = new ResultModel();
		
		try{
			
			resultModel.setResult(projectService.updateOutsourcing(modifyOutsourcingModel));
			
		}
		catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error!! :" + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("updateOutsourcing() end... ");
		return resultModel;
		
	}//end updateOutsourcing
	
	
	/**
	 * 외주정보 삭제
	 * @param outsourcingModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/deleteOutsourcing")
	public ResultModel deleteOutsourcing(@RequestBody ModifyOutsourcingModel modifyOutsourcingModel) throws ServletException{
		System.out.println("deleteOutsourcing() start... ");		
		ResultModel resultModel = new ResultModel();
		
		try{
			
			resultModel.setResult(projectService.deleteOutsourcing(modifyOutsourcingModel));
			
		}
		catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error!! :" + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("deleteOutsourcing() end... ");
		return resultModel;
		
	}//end deleteOutsourcing
	
	
	/**
	 * 본부에 속해있는 프로젝트 목록을 가져온다.
	 * @param deptCode:본부코드
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectProjectList")
	public List<SelectProjectListResModel> selectProjectList(@RequestBody SelectProjectListReqModel projectListReqModel) throws ServletException{
		System.out.println("selectProjectList() start... ");
		List<SelectProjectListResModel> selectProjectListModels = null; 
		
		try{		
			
			// 서비스 호출
			selectProjectListModels=projectService.selectProjectList(projectListReqModel);
			
		}
		catch(Exception e)
		{
			System.out.println("error!! :" + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectProjectList() end... ");
		return selectProjectListModels;
		
	}// end selectProjectList
	
	
	@RequestMapping(value = "/makeContract" , method = RequestMethod.POST)
	public void makeContract(@RequestBody MakeContractReqModel makeContractReqModel, HttpServletResponse response) throws Exception{
		
		System.out.println("excelDownload() start..");
		
		XWPFDocument doc = null;
		
		FileOutputStream fileOut = null;
		String fileDirectory = null;
		String fileName = null;	
		
		
		try {
////			MakeContractReqModel makeContractReqModel = new MakeContractReqModel();
//			makeContractReqModel.setPartnerCode("4");
//			makeContractReqModel.setOutsourcingCode("1");
//			makeContractReqModel.setPartnerId(1);
//			makeContractReqModel.setProjectId(1);
			
			doc = projectService.makeContract(makeContractReqModel);
			
			
			String mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document;charset=charset=utf-8";			
			response.setContentType(mimeType);		
			response.setHeader( "Content-disposition", "attachment; filename=myfile.xlsx" );
			
			// clinet로 파일 전달.
			doc.write(response.getOutputStream());
			
			// 버퍼를 닫는다
			response.flushBuffer();	
			
			

	         
//	        // 파일 내보낸다.
//			String nowTime = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
//			fileDirectory = "E:\\word\\";
//			fileName = "CPMS_사업자 현황_"+nowTime+".docx";
//			fileOut = new FileOutputStream(fileDirectory+fileName); 	
//			
//			
//			System.out.println("docx 파일생성 완료("+fileDirectory+fileName+")");
//			doc.write(fileOut); // 파일생성
//			fileOut.close(); // 닫기
//			System.out.println("excelDownload() end..");

			
			
		}catch(Exception e){
			StackTraceElement[] ste = e.getStackTrace();
			System.out.println("error !!!! " + ste[0].getLineNumber() +"/"+e.toString());
			throw new ServletException(e.toString());
		}
		
		
		
	}
	
}//end class
