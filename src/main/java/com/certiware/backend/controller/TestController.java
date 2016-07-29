package com.certiware.backend.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.TestExcelModel;
import com.certiware.backend.service.TestService;

@RestController
@RequestMapping(value="/test")
public class TestController {
	
	@Autowired
	TestService testService;
	
	private XWPFDocument doc = null;
	private File docFile = null;
	
	private static final int SHEET_NUM = 0;
    private static final int ROW_NUM = 0;
    private static final int CELL_NUM = 0;
    private static final double NEW_VALUE = 100.98D;
    private static final String BINARY_EXTENSION = "xls";
    private static final String OPENXML_EXTENSION = "xlsx";

	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/excelDownload" )
	public List<TestExcelModel> excelDownload(HttpServletResponse response) throws ServletException{
		
		System.out.println("excelDownload() start..");
		
		List<TestExcelModel> excelModels = null;		
		
		FileOutputStream fileOut = null;
		String fileDirectory = null;
		String fileName = null;				
		
		try {
			
			
			// local에 있는 템플릿 파일을 가져온다.
			this.docFile = new File("E:\\word\\template\\company.docx");
	        FileInputStream fis = null;
	        if (!this.docFile.exists()) {
	            throw new FileNotFoundException("The Word dcoument does not exist.");
	        }
	        try {

	            // Open the Word document file and instantiate the XWPFDocument
	            // class.
	            fis = new FileInputStream(this.docFile);
	            this.doc = new XWPFDocument(fis);
	        } finally {
	            if (fis != null) {
	                try {
	                    fis.close();
	                    fis = null;
	                } catch (IOException ioEx) {
	                    System.out.println("IOException caught trying to close " +
	                            "FileInputStream in the constructor of " +
	                            "UpdateEmbeddedDoc.");
	                }
	            }
	        }
	        
	        /*****************************************************************************************************************************/
			
	        List<XWPFTable> tables = this.doc.getTables();
			
	        System.out.println(tables.size());
	        
	        XWPFTable table1 = tables.get(0); // 용역계약서테이블
	        
	        table1.getRow(1).getCell(1).setText("테스트입니다"); // 계약명
	        
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

}
