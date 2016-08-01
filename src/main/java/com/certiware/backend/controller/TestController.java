package com.certiware.backend.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.TestExcelModel;
import com.certiware.backend.model.project.MakeContractReqModel;
import com.certiware.backend.service.ProjectService;
import com.certiware.backend.service.TestService;

@RestController
@RequestMapping(value="/test")
public class TestController {
	
	@Autowired
	TestService testService;
	@Autowired
	ProjectService projectService;
	
	private XWPFDocument doc = null;
	private File docFile = null;
	
	
	@RequestMapping(value="/excelDownload" )
	public List<TestExcelModel> excelDownload(HttpServletResponse response) throws ServletException{
		
		System.out.println("excelDownload() start..");
		
		List<TestExcelModel> excelModels = null;		
		
		FileOutputStream fileOut = null;
		String fileDirectory = null;
		String fileName = null;				
		
		try {
			MakeContractReqModel makeContractReqModel = new MakeContractReqModel();
			makeContractReqModel.setPartnerCode("4");
			makeContractReqModel.setOutsourcingCode("1");
			makeContractReqModel.setPartnerId(1);
			makeContractReqModel.setProjectId(1);
			
			this.doc = projectService.makeContract(makeContractReqModel);
			
			

	         
	        // 파일 내보낸다.
			String nowTime = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
			fileDirectory = "E:\\word\\";
			fileName = "CPMS_사업자 현황_"+nowTime+".docx";
			fileOut = new FileOutputStream(fileDirectory+fileName); 	
			
			
			System.out.println("docx 파일생성 완료("+fileDirectory+fileName+")");
			doc.write(fileOut); // 파일생성
			fileOut.close(); // 닫기
			System.out.println("excelDownload() end..");

			
			
		}catch(Exception e){
			StackTraceElement[] ste = e.getStackTrace();
			System.out.println("error !!!! " + ste[0].getLineNumber() +"/"+e.toString());
			throw new ServletException(e.toString());
		}
		
		return excelModels;
		
	}
	//콤마추가
	public String insertComma(Double num) {
		DecimalFormat decFormat = new DecimalFormat("###,###,###,###,###,###,###");
		return decFormat.format(num).toString();
	}
	
	// 금액 한글
	public String convertHangul(String money){
		String[] han1 = {"","일","이","삼","사","오","육","칠","팔","구"};
		String[] han2 = {"","십","백","천"};
		String[] han3 = {"","만","억","조","경"};

		StringBuffer result = new StringBuffer();
		int len = money.length();
		for(int i=len-1; i>=0; i--){
			result.append(han1[Integer.parseInt(money.substring(len-i-1, len-i))]);
			if(Integer.parseInt(money.substring(len-i-1, len-i)) > 0)
				result.append(han2[i%4]);
			if(i%4 == 0)
				result.append(han3[i/4]);
		}
		
		return result.toString();
	}
	
	private void clearCell(XWPFTableRow row){
		
		CTTc[] cells = row.getCtRow().getTcList().toArray(new CTTc[0]);
        for (int c = 0; c < cells.length; c++) {
         CTTc cTTc = cells[c];
         //clear only the paragraphs in the cell, keep cell styles
         cTTc.setPArray(new CTP[] {CTP.Factory.newInstance()});
         cells[c] = cTTc;
        }
        System.out.println(row.getCell(1).getText());
		
	}
	
	private String makeName(String name){
		String result = "";
		
		for(int i=0;i<name.length();i++){
			
			result += name.charAt(i) + " ";
		}		
		result = result.substring(0, result.length() -1);
		
		
		return result;
	}
	
	private String makeDate(Date startDate, Date endDate, String type) throws ParseException{
		
		String result = "";
		
		DateFormat df= new SimpleDateFormat("yy.MM.dd");		
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();		
		
		
		switch(type){
		case("A") :
			
			cal1.setTime(startDate);
			cal2.setTime(endDate);
			
			// 시작일 세팅
			result += cal1.get(cal1.YEAR) + "년 ";
			result += (Integer.parseInt(String.valueOf(cal1.get(cal1.MONTH))) + 1 < 10 ? "0" + (Integer.parseInt(String.valueOf(cal1.get(cal1.MONTH))) + 1)  : Integer.parseInt(String.valueOf(cal1.get(cal1.MONTH))) + 1 )  + "월 ";
			result += (Integer.parseInt(String.valueOf(cal1.get(cal1.DAY_OF_MONTH))) + 1 < 10 ? "0" + (Integer.parseInt(String.valueOf(cal1.get(cal1.DAY_OF_MONTH))) + 1)  : Integer.parseInt(String.valueOf(cal1.get(cal1.DAY_OF_MONTH))) + 1 )  + "일 ";		
			
			result += "~ ";
			
			// 종료일 세팅
			result += cal2.get(cal2.YEAR) + "년 ";
			result += (Integer.parseInt(String.valueOf(cal2.get(cal2.MONTH))) + 1 < 10 ? "0" + (Integer.parseInt(String.valueOf(cal2.get(cal2.MONTH))) + 1)  : Integer.parseInt(String.valueOf(cal2.get(cal2.MONTH))) + 1 )  + "월 ";
			result += (Integer.parseInt(String.valueOf(cal2.get(cal2.DAY_OF_MONTH))) + 1 < 10 ? "0" + (Integer.parseInt(String.valueOf(cal2.get(cal2.DAY_OF_MONTH))) + 1)  : Integer.parseInt(String.valueOf(cal2.get(cal2.DAY_OF_MONTH))) + 1 )  + "일 ";
			
			break;
			
		case("B") :
			
			cal1.setTime(startDate);
			
			// 시작일 세팅
		result += cal1.get(cal1.YEAR) + "년 ";
		result += (Integer.parseInt(String.valueOf(cal1.get(cal1.MONTH))) + 1 < 10 ? "0" + (Integer.parseInt(String.valueOf(cal1.get(cal1.MONTH))) + 1)  : Integer.parseInt(String.valueOf(cal1.get(cal1.MONTH))) + 1 )  + "월 ";
		result += (Integer.parseInt(String.valueOf(cal1.get(cal1.DAY_OF_MONTH))) + 1 < 10 ? "0" + (Integer.parseInt(String.valueOf(cal1.get(cal1.DAY_OF_MONTH))) + 1)  : Integer.parseInt(String.valueOf(cal1.get(cal1.DAY_OF_MONTH))) + 1 )  + "일 ";
			
			break;
		case("C") :
			
			cal1.setTime(startDate);
			cal2.setTime(endDate);			
			
			result += df.format(startDate) + " ~ " + df.format(endDate);
			
			break;	
		}
		
		return result;
	}
	
	

}


