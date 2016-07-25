package com.certiware.backend.controller;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.TestExcelModel;
import com.certiware.backend.model.partner.SelectListModel;
import com.certiware.backend.service.TestService;

@RestController
@RequestMapping(value="/test")
public class TestController {
	
	@Autowired
	TestService testService;
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/excelDownload" , method=RequestMethod.GET)
	public List<TestExcelModel> excelDownload(HttpServletResponse response) throws ServletException{
		
		System.out.println("excelDownload() start..");
		
		List<TestExcelModel> excelModels = null;
		List<SelectListModel> selectListModels = new ArrayList<>();
		SelectListModel selectListModel = new SelectListModel();
		
		try {
			HSSFWorkbook wb = new HSSFWorkbook();   // workbook 생성
			HSSFSheet sheet1 = wb.createSheet("sheet1");  //sheet 생성
			FileOutputStream fileOut = null;
			String fileDirectory = null;
			String fileName = null;
			
			String innerFlag = "PROJECT";
			if(innerFlag.equals("PROJECT")){
				excelModels = testService.selectExcel();
				
				/*--------------------셀스타일 설정--------------------*/
				//열너비 배열
				int widthArr[] = {300, 5550, 3000, 2700, 4000, 1500, 3500, 
									2700, 2700, 1350, 1350 ,1350 ,1350, 1350, 
									1350, 1350, 1350, 1350, 1350, 1350, 1350, 
									1900, 4500, 4500, 9000};
				
				//열너비 조정
				for(int count = 0; count<widthArr.length; count++){
					sheet1.setColumnWidth(count, widthArr[count]);
				}
				
				//헤더 스타일
				HSSFCellStyle styleHead = wb.createCellStyle();
				HSSFFont headFont = wb.createFont();
				headFont.setFontHeightInPoints((short)20);
				headFont.setUnderline(headFont.U_SINGLE);
				styleHead.setFont(headFont);
				styleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				styleHead.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				styleHead.setFillForegroundColor(HSSFColor.WHITE.index);
				styleHead.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				
				//헤더 범주 스타일
				HSSFCellStyle styleHead_index = wb.createCellStyle();
				styleHead_index.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				styleHead_index.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER);
				styleHead_index.setFillForegroundColor(HSSFColor.WHITE.index);
				styleHead_index.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
				
				//메뉴 스타일
				HSSFCellStyle styleMenu = wb.createCellStyle();
				HSSFFont menuFont = wb.createFont();
				menuFont.setFontHeightInPoints((short)11);
				menuFont.setBoldweight(menuFont.BOLDWEIGHT_BOLD);
				styleMenu.setFont(menuFont);
				styleMenu.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				styleMenu.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				styleMenu.setFillForegroundColor(HSSFColor.LIME.index);
				styleMenu.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleMenu.setBorderBottom(CellStyle.BORDER_THIN);
				styleMenu.setBorderLeft(CellStyle.BORDER_THIN);
				styleMenu.setBorderRight(CellStyle.BORDER_THIN);
				styleMenu.setBorderTop(CellStyle.BORDER_THIN);
				
				//메뉴 스타일  왼쪽구분
				HSSFCellStyle styleGubunMenu = wb.createCellStyle();
				HSSFFont menuGubunFont = wb.createFont();
				menuGubunFont.setFontHeightInPoints((short)11);
				menuGubunFont.setBoldweight(menuGubunFont.BOLDWEIGHT_BOLD);
				styleGubunMenu.setFont(menuGubunFont);
				styleGubunMenu.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				styleGubunMenu.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				styleGubunMenu.setFillForegroundColor(HSSFColor.LIME.index);
				styleGubunMenu.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleGubunMenu.setBorderBottom(CellStyle.BORDER_THIN);
				styleGubunMenu.setBorderLeft(CellStyle.BORDER_THICK);
				styleGubunMenu.setBorderRight(CellStyle.BORDER_THIN);
				styleGubunMenu.setBorderTop(CellStyle.BORDER_THIN);
				
				//데이터 스타일
				HSSFCellStyle styleMain = wb.createCellStyle();
				styleMain.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				styleMain.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				styleMain.setFillForegroundColor(HSSFColor.WHITE.index);
				styleMain.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleMain.setBorderBottom(CellStyle.BORDER_THIN);
				styleMain.setBorderLeft(CellStyle.BORDER_THIN);
				styleMain.setBorderRight(CellStyle.BORDER_THIN);
				styleMain.setBorderTop(CellStyle.BORDER_THIN);
				
				//데이터 스타일 왼쪽구분
				HSSFCellStyle styleMainGubun = wb.createCellStyle();
				styleMainGubun.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				styleMainGubun.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				styleMainGubun.setFillForegroundColor(HSSFColor.WHITE.index);
				styleMainGubun.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleMainGubun.setBorderBottom(CellStyle.BORDER_THIN);
				styleMainGubun.setBorderLeft(CellStyle.BORDER_THICK);
				styleMainGubun.setBorderRight(CellStyle.BORDER_THIN);
				styleMainGubun.setBorderTop(CellStyle.BORDER_THIN);
				
				//사업계 스타일
				HSSFCellStyle styleSum = wb.createCellStyle();
				styleSum.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				styleSum.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				styleSum.setFillForegroundColor(HSSFColor.TAN.index);
				styleSum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleSum.setBorderBottom(CellStyle.BORDER_THIN);
				styleSum.setBorderLeft(CellStyle.BORDER_THIN);
				styleSum.setBorderRight(CellStyle.BORDER_THIN);
				styleSum.setBorderTop(CellStyle.BORDER_THIN);
				
				//사업계 스타일  왼쪽구분
				HSSFCellStyle styleSumGubun = wb.createCellStyle();
				styleSumGubun.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				styleSumGubun.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				styleSumGubun.setFillForegroundColor(HSSFColor.TAN.index);
				styleSumGubun.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleSumGubun.setBorderBottom(CellStyle.BORDER_THIN);
				styleSumGubun.setBorderLeft(CellStyle.BORDER_THICK);
				styleSumGubun.setBorderRight(CellStyle.BORDER_THIN);
				styleSumGubun.setBorderTop(CellStyle.BORDER_THIN);
				
				//총합계 스타일
				HSSFCellStyle styleTotal = wb.createCellStyle();
				styleTotal.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				styleTotal.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				styleTotal.setFillForegroundColor(HSSFColor.CORAL.index);
				styleTotal.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleTotal.setBorderBottom(CellStyle.BORDER_THIN);
				styleTotal.setBorderLeft(CellStyle.BORDER_THIN);
				styleTotal.setBorderRight(CellStyle.BORDER_THIN);
				styleTotal.setBorderTop(CellStyle.BORDER_THIN);
				
				//총합계 스타일  왼쪽구분
				HSSFCellStyle styleTotalGubun = wb.createCellStyle();
				styleTotalGubun.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				styleTotalGubun.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				styleTotalGubun.setFillForegroundColor(HSSFColor.CORAL.index);
				styleTotalGubun.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleTotalGubun.setBorderBottom(CellStyle.BORDER_THIN);
				styleTotalGubun.setBorderLeft(CellStyle.BORDER_THICK);
				styleTotalGubun.setBorderRight(CellStyle.BORDER_THIN);
				styleTotalGubun.setBorderTop(CellStyle.BORDER_THIN);
				
				/*--------------------헤더 셀 삽입 설정--------------------*/
				//셀병합
				sheet1.addMergedRegion(new Region(0 ,(short)1 ,0 ,(short)24));
				sheet1.addMergedRegion(new Region(1 ,(short)1 ,1 ,(short)24));
				sheet1.addMergedRegion(new Region(2 ,(short)1 ,2 ,(short)24));
				sheet1.addMergedRegion(new Region(3 ,(short)1 ,4 ,(short)1));
				sheet1.addMergedRegion(new Region(3 ,(short)2 ,4 ,(short)2));
				sheet1.addMergedRegion(new Region(3 ,(short)3 ,4 ,(short)3));
				sheet1.addMergedRegion(new Region(3 ,(short)4 ,4 ,(short)4));
				sheet1.addMergedRegion(new Region(3 ,(short)5 ,4 ,(short)5));
				sheet1.addMergedRegion(new Region(3 ,(short)6 ,4 ,(short)6));
				sheet1.addMergedRegion(new Region(3 ,(short)7 ,3 ,(short)8));
				sheet1.addMergedRegion(new Region(3 ,(short)9 ,3 ,(short)21));
				sheet1.addMergedRegion(new Region(3 ,(short)22 ,4 ,(short)22));
				sheet1.addMergedRegion(new Region(3 ,(short)23 ,4 ,(short)23));
				sheet1.addMergedRegion(new Region(3 ,(short)24 ,4 ,(short)24));
				
				//타이틀 변수
				String baseName = "OSS본부";
				String projectYear = "2016";
				
				//1열
				HSSFRow row0 = sheet1.createRow(0);  // sheet에 row0 생성
				row0.setHeight((short)650);
				
				HSSFCell objCell0_1 = row0.createCell(1);
				objCell0_1.setCellValue(projectYear+"년 투입인력/순매출액 현황("+baseName+" 사업)");
				objCell0_1.setCellStyle(styleHead);
				
				//2열
				HSSFRow row1 = sheet1.createRow(1);  // sheet에 row1 생성
				row1.setHeight((short)350);
				HSSFCell objCell1_1 = row1.createCell(1);
				objCell1_1.setCellStyle(styleHead_index);
				
				//3열
				HSSFRow row2 = sheet1.createRow(2);  // sheet에 row2 생성
				row2.setHeight((short)350);
				
				HSSFCell objCell2_1 = row2.createCell(1);
				objCell2_1.setCellValue("(단위: MM,원/부가세별도)");
				objCell2_1.setCellStyle(styleHead_index);
				
				/*--------------------메뉴 셀 설정--------------------*/
				//4열
				HSSFRow row3 = sheet1.createRow(3);  // sheet에 row3 생성
				row3.setHeight((short)350);
				
				HSSFCell objCell3_1 = row3.createCell(1);
				objCell3_1.setCellValue("프로젝트명");
				objCell3_1.setCellStyle(styleMenu);
				
				HSSFCell objCell3_2 = row3.createCell(2);
				objCell3_2.setCellValue("이름");
				objCell3_2.setCellStyle(styleMenu);
				
				HSSFCell objCell3_3 = row3.createCell(3);
				objCell3_3.setCellValue("등급");
				objCell3_3.setCellStyle(styleMenu);
				
				HSSFCell objCell3_4 = row3.createCell(4);
				objCell3_4.setCellValue("실 소속");
				objCell3_4.setCellStyle(styleMenu);
				
				HSSFCell objCell3_5 = row3.createCell(5);
				objCell3_5.setCellValue("구분");
				objCell3_5.setCellStyle(styleMenu);
				
				HSSFCell objCell3_6 = row3.createCell(6);
				objCell3_6.setCellValue("계약단가");
				objCell3_6.setCellStyle(styleMenu);
				
				HSSFCell objCell3_7 = row3.createCell(7);
				objCell3_7.setCellValue("투입기간");
				objCell3_7.setCellStyle(styleGubunMenu);
				
				HSSFCell objCell3_9 = row3.createCell(9);
				objCell3_9.setCellValue("월별 투입 M/M");
				objCell3_9.setCellStyle(styleMenu);
				
				HSSFCell objCell3_22 = row3.createCell(22);
				objCell3_22.setCellValue("금액");
				objCell3_22.setCellStyle(styleMenu);
				
				HSSFCell objCell3_23 = row3.createCell(23);
				objCell3_23.setCellValue("순매출액");
				objCell3_23.setCellStyle(styleMenu);
				
				HSSFCell objCell3_24 = row3.createCell(24);
				objCell3_24.setCellValue("비고(특기사항)");
				objCell3_24.setCellStyle(styleMenu);
				
				//5열
				HSSFRow row4 = sheet1.createRow(4);  // sheet에 row4 생성
				row4.setHeight((short)350);
				
				HSSFCell objCell4_7 = row4.createCell(7);
				objCell4_7.setCellValue("투입일");
				objCell4_7.setCellStyle(styleGubunMenu);
				
				HSSFCell objCell4_8 = row4.createCell(8);
				objCell4_8.setCellValue("철수일");
				objCell4_8.setCellStyle(styleMenu);
				
				//월1~12
				for(int count = 0; count<12; count++){
					int cellCount = count + 9;
					HSSFCell objCell4 = row4.createCell(cellCount);
					objCell4.setCellValue((count+1)+"월");
					objCell4.setCellStyle(styleMenu);
				}
				
				HSSFCell objCell4_21 = row4.createCell(21);
				objCell4_21.setCellValue("합계(a)");
				objCell4_21.setCellStyle(styleMenu);
				
				//빈 셀서식
				//row3
				HSSFCell objCell3_8 = row3.createCell(8);
				objCell3_8.setCellStyle(styleMenu);
				HSSFCell objCell3_10 = row3.createCell(10);
				objCell3_10.setCellStyle(styleMenu);
				HSSFCell objCell3_11 = row3.createCell(11);
				objCell3_11.setCellStyle(styleMenu);
				HSSFCell objCell3_12 = row3.createCell(12);
				objCell3_12.setCellStyle(styleMenu);
				HSSFCell objCell3_13 = row3.createCell(13);
				objCell3_13.setCellStyle(styleMenu);
				HSSFCell objCell3_14 = row3.createCell(14);
				objCell3_14.setCellStyle(styleMenu);
				HSSFCell objCell3_15 = row3.createCell(15);
				objCell3_15.setCellStyle(styleMenu);
				HSSFCell objCell3_16 = row3.createCell(16);
				objCell3_16.setCellStyle(styleMenu);
				HSSFCell objCell3_17 = row3.createCell(17);
				objCell3_17.setCellStyle(styleMenu);
				HSSFCell objCell3_18 = row3.createCell(18);
				objCell3_18.setCellStyle(styleMenu);
				HSSFCell objCell3_19 = row3.createCell(19);
				objCell3_19.setCellStyle(styleMenu);
				HSSFCell objCell3_20 = row3.createCell(20);
				objCell3_20.setCellStyle(styleMenu);
				HSSFCell objCell3_21 = row3.createCell(21);
				objCell3_21.setCellStyle(styleMenu);
				//row4
				HSSFCell objCell4_1 = row4.createCell(1);
				objCell4_1.setCellStyle(styleMenu);
				HSSFCell objCell4_2 = row4.createCell(2);
				objCell4_2.setCellStyle(styleMenu);
				HSSFCell objCell4_3 = row4.createCell(3);
				objCell4_3.setCellStyle(styleMenu);
				HSSFCell objCell4_4 = row4.createCell(4);
				objCell4_4.setCellStyle(styleMenu);
				HSSFCell objCell4_5 = row4.createCell(5);
				objCell4_5.setCellStyle(styleMenu);
				HSSFCell objCell4_6 = row4.createCell(6);
				objCell4_6.setCellStyle(styleMenu);
				HSSFCell objCell4_22 = row4.createCell(22);
				objCell4_22.setCellStyle(styleMenu);
				HSSFCell objCell4_23 = row4.createCell(23);
				objCell4_23.setCellStyle(styleMenu);
				HSSFCell objCell4_24 = row4.createCell(24);
				objCell4_24.setCellStyle(styleMenu);
				
				/*--------------------DB DATA OR 본부합계 OR 합계 셀 설정--------------------*/
				//본문 변수 추가 및 초기화
				String projectName = null;
				int rowNum = 0;
				int sumRowNum = 0;
				
				Double sumM1Sales = (double) 0;							Double sumM1Outsourcing = (double) 0;
				Double sumM2Sales = (double) 0;							Double sumM2Outsourcing = (double) 0;
				Double sumM3Sales = (double) 0;							Double sumM3Outsourcing = (double) 0;
				Double sumM4Sales = (double) 0;							Double sumM4Outsourcing = (double) 0;
				Double sumM5Sales = (double) 0;							Double sumM5Outsourcing = (double) 0;
				Double sumM6Sales = (double) 0;							Double sumM6Outsourcing = (double) 0;
				Double sumM7Sales = (double) 0;							Double sumM7Outsourcing = (double) 0;
				Double sumM8Sales = (double) 0;							Double sumM8Outsourcing = (double) 0;
				Double sumM9Sales = (double) 0;							Double sumM9Outsourcing = (double) 0;
				Double sumM10Sales = (double) 0;						Double sumM10Outsourcing = (double) 0;
				Double sumM11Sales = (double) 0;						Double sumM11Outsourcing = (double) 0;
				Double sumM12Sales = (double) 0;						Double sumM12Outsourcing = (double) 0;
				Double sumAllSales = (double) 0;						Double sumAllOutsourcing = (double) 0;
				Double sumAmountSales = (double) 0;						Double sumAmountOutsourcing = (double) 0;
				Double sumNetAmountSales = (double) 0;					Double sumNetAmountOutsourcing = (double) 0;
				
				Double sumTotalM1Sales = (double) 0;					Double sumTotalM1Outsourcing = (double) 0;
				Double sumTotalM2Sales = (double) 0;					Double sumTotalM2Outsourcing = (double) 0;
				Double sumTotalM3Sales = (double) 0;					Double sumTotalM3Outsourcing = (double) 0;
				Double sumTotalM4Sales = (double) 0;					Double sumTotalM4Outsourcing = (double) 0;
				Double sumTotalM5Sales = (double) 0;					Double sumTotalM5Outsourcing = (double) 0;
				Double sumTotalM6Sales = (double) 0;					Double sumTotalM6Outsourcing = (double) 0;
				Double sumTotalM7Sales = (double) 0;					Double sumTotalM7Outsourcing = (double) 0;
				Double sumTotalM8Sales = (double) 0;					Double sumTotalM8Outsourcing = (double) 0;
				Double sumTotalM9Sales = (double) 0;					Double sumTotalM9Outsourcing = (double) 0;
				Double sumTotalM10Sales = (double) 0;					Double sumTotalM10Outsourcing = (double) 0;
				Double sumTotalM11Sales = (double) 0;					Double sumTotalM11Outsourcing = (double) 0;
				Double sumTotalM12Sales = (double) 0;					Double sumTotalM12Outsourcing = (double) 0;
				Double sumTotalAllSales = (double) 0;					Double sumTotalAllOutsourcing = (double) 0;
				Double sumTotalAmountSales = (double) 0;				Double sumTotalAmountOutsourcing = (double) 0;
				Double sumTotalNetAmountSales = (double) 0;				Double sumTotalNetAmountOutsourcing = (double) 0;

				ArrayList projectNameRowNumList = new ArrayList();
				for(int cnt = 0; cnt < excelModels.size(); cnt++){
					/*--------------------DB DATA 추가 설정--------------------*/
					//데이터 셀
					TestExcelModel dataModel = excelModels.get(cnt);

					rowNum = 5 + cnt + sumRowNum;
					HSSFRow dataRow = sheet1.createRow(rowNum);  // sheet에 row4 생성
					dataRow.setHeight((short)350);
					
					
					HSSFCell dataObjCell_1 = dataRow.createCell(1);
					
					if(dataModel.getProjectName().equals(projectName) == false){
						projectNameRowNumList.add(rowNum);
						projectName = (String)dataModel.getProjectName();
						dataObjCell_1.setCellValue((String)dataModel.getProjectName());
						dataObjCell_1.setCellStyle(styleMain);
					}
					
					HSSFCell dataObjCell_2 = dataRow.createCell(2);
					HSSFCell dataObjCell_3 = dataRow.createCell(3);
					HSSFCell dataObjCell_4 = dataRow.createCell(4);

					if(rowNum%2 == 1){
						dataObjCell_2.setCellValue((String)dataModel.getManpowerName());
						dataObjCell_3.setCellValue((String)dataModel.getRatingCode());
						dataObjCell_4.setCellValue((String)dataModel.getPartnername());
					}else{
						for(short rowCnt = 2; rowCnt < 5; rowCnt++){
							sheet1.addMergedRegion(new Region((rowNum-1) ,rowCnt ,rowNum ,rowCnt));
						}
					}
					dataObjCell_2.setCellStyle(styleMain);
					dataObjCell_3.setCellStyle(styleMain);
					dataObjCell_4.setCellStyle(styleMain);
						
					HSSFCell dataObjCell_5 = dataRow.createCell(5);
					dataObjCell_5.setCellValue((String)dataModel.getFlag());
					dataObjCell_5.setCellStyle(styleMain);
					
					HSSFCell dataObjCell_6 = dataRow.createCell(6);
					if(dataModel.getContractamount().equals("") == false){
						dataObjCell_6.setCellValue(insertComma(Double.parseDouble(dataModel.getContractamount())));
					}else{
						dataObjCell_6.setCellValue("");
					}
					dataObjCell_6.setCellStyle(styleMain);	
					
					HSSFCell dataObjCell_7 = dataRow.createCell(7);
					dataObjCell_7.setCellValue((String)dataModel.getStartDate());
					dataObjCell_7.setCellStyle(styleMainGubun);
					
					HSSFCell dataObjCell_8 = dataRow.createCell(8);
					dataObjCell_8.setCellValue((String)dataModel.getEndDate());
					dataObjCell_8.setCellStyle(styleMain);

					HSSFCell dataObjCell_9 = dataRow.createCell(9);
					if(dataModel.getM1().equals("")){
						dataObjCell_9.setCellValue("");
					}else if(dataModel.getM1().equals("-")){
						dataObjCell_9.setCellValue("-");
					}else{
						dataObjCell_9.setCellValue(Double.parseDouble(dataModel.getM1()));
					}
					dataObjCell_9.setCellStyle(styleMain);

					HSSFCell dataObjCell_10 = dataRow.createCell(10);
					if(dataModel.getM2().equals("")){
						dataObjCell_10.setCellValue("");
					}else if(dataModel.getM2().equals("-")){
						dataObjCell_10.setCellValue("-");
					}else{
						dataObjCell_10.setCellValue(Double.parseDouble(dataModel.getM2()));
					}
					dataObjCell_10.setCellStyle(styleMain);

					HSSFCell dataObjCell_11 = dataRow.createCell(11);
					if(dataModel.getM3().equals("")){
						dataObjCell_11.setCellValue("");
					}else if(dataModel.getM3().equals("-")){
						dataObjCell_11.setCellValue("-");
					}else{
						dataObjCell_11.setCellValue(Double.parseDouble(dataModel.getM3()));
					}
					dataObjCell_11.setCellStyle(styleMain);

					HSSFCell dataObjCell_12 = dataRow.createCell(12);
					if(dataModel.getM4().equals("")){
						dataObjCell_12.setCellValue("");
					}else if(dataModel.getM4().equals("-")){
						dataObjCell_12.setCellValue("-");
					}else{
						dataObjCell_12.setCellValue(Double.parseDouble(dataModel.getM4()));
					}
					dataObjCell_12.setCellStyle(styleMain);

					HSSFCell dataObjCell_13 = dataRow.createCell(13);
					if(dataModel.getM5().equals("")){
						dataObjCell_13.setCellValue("");
					}else if(dataModel.getM5().equals("-")){
						dataObjCell_13.setCellValue("-");
					}else{
						dataObjCell_13.setCellValue(Double.parseDouble(dataModel.getM5()));
					}
					dataObjCell_13.setCellStyle(styleMain);

					HSSFCell dataObjCell_14 = dataRow.createCell(14);
					if(dataModel.getM6().equals("")){
						dataObjCell_14.setCellValue("");
					}else if(dataModel.getM6().equals("-")){
						dataObjCell_14.setCellValue("-");
					}else{
						dataObjCell_14.setCellValue(Double.parseDouble(dataModel.getM6()));
					}
					dataObjCell_14.setCellStyle(styleMain);

					HSSFCell dataObjCell_15 = dataRow.createCell(15);
					if(dataModel.getM7().equals("")){
						dataObjCell_15.setCellValue("");
					}else if(dataModel.getM7().equals("-")){
						dataObjCell_15.setCellValue("-");
					}else{
						dataObjCell_15.setCellValue(Double.parseDouble(dataModel.getM7()));
					}
					dataObjCell_15.setCellStyle(styleMain);

					HSSFCell dataObjCell_16 = dataRow.createCell(16);
					if(dataModel.getM8().equals("")){
						dataObjCell_16.setCellValue("");
					}else if(dataModel.getM8().equals("-")){
						dataObjCell_16.setCellValue("-");
					}else{
						dataObjCell_16.setCellValue(Double.parseDouble(dataModel.getM8()));
					}
					dataObjCell_16.setCellStyle(styleMain);

					HSSFCell dataObjCell_17 = dataRow.createCell(17);
					if(dataModel.getM9().equals("")){
						dataObjCell_17.setCellValue("");
					}else if(dataModel.getM9().equals("-")){
						dataObjCell_17.setCellValue("-");
					}else{
						dataObjCell_17.setCellValue(Double.parseDouble(dataModel.getM9()));
					}
					dataObjCell_17.setCellStyle(styleMain);

					HSSFCell dataObjCell_18 = dataRow.createCell(18);
					if(dataModel.getM10().equals("")){
						dataObjCell_18.setCellValue("");
					}else if(dataModel.getM10().equals("-")){
						dataObjCell_18.setCellValue("-");
					}else{
						dataObjCell_18.setCellValue(Double.parseDouble(dataModel.getM10()));
					}
					dataObjCell_18.setCellStyle(styleMain);

					HSSFCell dataObjCell_19 = dataRow.createCell(19);
					if(dataModel.getM11().equals("")){
						dataObjCell_19.setCellValue("");
					}else if(dataModel.getM11().equals("-")){
						dataObjCell_19.setCellValue("-");
					}else{
						dataObjCell_19.setCellValue(Double.parseDouble(dataModel.getM11()));
					}
					dataObjCell_19.setCellStyle(styleMain);

					HSSFCell dataObjCell_20 = dataRow.createCell(20);
					if(dataModel.getM12().equals("")){
						dataObjCell_20.setCellValue("");
					}else if(dataModel.getM12().equals("-")){
						dataObjCell_20.setCellValue("-");
					}else{
						dataObjCell_20.setCellValue(Double.parseDouble(dataModel.getM12()));
					}
					dataObjCell_20.setCellStyle(styleMain);

					HSSFCell dataObjCell_21 = dataRow.createCell(21);
					dataObjCell_21.setCellValue(Double.parseDouble(dataModel.getmSum()));
					dataObjCell_21.setCellStyle(styleMain);

					HSSFCell dataObjCell_22 = dataRow.createCell(22);
					if(dataModel.getContractamount().equals("") == false){
						dataObjCell_22.setCellValue(insertComma(Double.parseDouble(dataModel.getSumAmount())));
					}else{
						dataObjCell_22.setCellValue("");
					}
					dataObjCell_22.setCellStyle(styleMain);

					HSSFCell dataObjCell_23 = dataRow.createCell(23);
					if(dataModel.getNetAmount().equals("") == false && dataModel.getNetAmount().isEmpty() == false){
						dataObjCell_23.setCellValue(insertComma(Double.parseDouble(dataModel.getNetAmount())));
					}else{
						dataObjCell_23.setCellValue("");
					}
					dataObjCell_23.setCellStyle(styleMain);
					
					HSSFCell dataObjCell_24 = dataRow.createCell(24);
					dataObjCell_24.setCellValue((String)dataModel.getRemarks());
					dataObjCell_24.setCellStyle(styleMain);
					
					//매출 OR 외주 합계
					if(((String)dataModel.getFlag()).equals("매출")){
						if(dataModel.getM1().equals("") == false && dataModel.getM1().equals("-") == false && dataModel.getM1().isEmpty() == false){
							sumM1Sales = sumM1Sales + Double.parseDouble(dataModel.getM1());
						}
						if(dataModel.getM2().equals("") == false && dataModel.getM2().equals("-") == false && dataModel.getM2().isEmpty() == false){
							sumM2Sales = sumM2Sales + Double.parseDouble(dataModel.getM2());
						}
						if(dataModel.getM3().equals("") == false && dataModel.getM3().equals("-") == false && dataModel.getM3().isEmpty() == false){
							sumM3Sales = sumM3Sales + Double.parseDouble(dataModel.getM3());
						}
						if(dataModel.getM4().equals("") == false && dataModel.getM4().equals("-") == false && dataModel.getM4().isEmpty() == false){
							sumM4Sales = sumM4Sales + Double.parseDouble(dataModel.getM4());
						}
						if(dataModel.getM5().equals("") == false && dataModel.getM5().equals("-") == false && dataModel.getM5().isEmpty() == false){
							sumM5Sales = sumM5Sales + Double.parseDouble(dataModel.getM5());
						}
						if(dataModel.getM6().equals("") == false && dataModel.getM6().equals("-") == false && dataModel.getM6().isEmpty() == false){
							sumM6Sales = sumM6Sales + Double.parseDouble(dataModel.getM6());
						}
						if(dataModel.getM7().equals("") == false && dataModel.getM7().equals("-") == false && dataModel.getM7().isEmpty() == false){
							sumM7Sales = sumM7Sales + Double.parseDouble(dataModel.getM7());
						}
						if(dataModel.getM8().equals("") == false && dataModel.getM8().equals("-") == false && dataModel.getM8().isEmpty() == false){
							sumM8Sales = sumM8Sales + Double.parseDouble(dataModel.getM8());
						}
						if(dataModel.getM9().equals("") == false && dataModel.getM9().equals("-") == false && dataModel.getM9().isEmpty() == false){
							sumM9Sales = sumM9Sales + Double.parseDouble(dataModel.getM9());
						}
						if(dataModel.getM10().equals("") == false && dataModel.getM10().equals("-") == false && dataModel.getM10().isEmpty() == false){
							sumM10Sales = sumM10Sales + Double.parseDouble(dataModel.getM10());
						}
						if(dataModel.getM11().equals("") == false && dataModel.getM11().equals("-") == false && dataModel.getM11().isEmpty() == false){
							sumM11Sales = sumM11Sales + Double.parseDouble(dataModel.getM11());
						}
						if(dataModel.getM12().equals("") == false && dataModel.getM12().equals("-") == false && dataModel.getM12().isEmpty() == false){
							sumM12Sales = sumM12Sales + Double.parseDouble(dataModel.getM12());
						}
						if(dataModel.getmSum().equals("") == false && dataModel.getmSum().equals("-") == false && dataModel.getmSum().isEmpty() == false){
							sumAllSales = sumAllSales + Double.parseDouble(dataModel.getmSum());
						}
						if(dataModel.getSumAmount().equals("") == false && dataModel.getSumAmount().equals("-") == false && dataModel.getSumAmount().isEmpty() == false){
							sumAmountSales = sumAmountSales + Double.parseDouble(dataModel.getSumAmount());
						}
						if(dataModel.getNetAmount().equals("") == false && dataModel.getNetAmount().equals("-") == false && dataModel.getNetAmount().isEmpty() == false){
							sumNetAmountSales = sumNetAmountSales + Double.parseDouble(dataModel.getNetAmount());
						}
					}else if(((String)dataModel.getFlag()).equals("외주")){
						if(dataModel.getM1().equals("") == false && dataModel.getM1().equals("-") == false && dataModel.getM1().isEmpty() == false){
							sumM1Outsourcing = sumM1Outsourcing + Double.parseDouble(dataModel.getM1());
						}
						if(dataModel.getM2().equals("") == false && dataModel.getM2().equals("-") == false && dataModel.getM2().isEmpty() == false){
							sumM2Outsourcing = sumM2Outsourcing + Double.parseDouble(dataModel.getM2());
						}
						if(dataModel.getM3().equals("") == false && dataModel.getM3().equals("-") == false && dataModel.getM3().isEmpty() == false){
							sumM3Outsourcing = sumM3Outsourcing + Double.parseDouble(dataModel.getM3());
						}
						if(dataModel.getM4().equals("") == false && dataModel.getM4().equals("-") == false && dataModel.getM4().isEmpty() == false){
							sumM4Outsourcing = sumM4Outsourcing + Double.parseDouble(dataModel.getM4());
						}
						if(dataModel.getM5().equals("") == false && dataModel.getM5().equals("-") == false && dataModel.getM5().isEmpty() == false){
							sumM5Outsourcing = sumM5Outsourcing + Double.parseDouble(dataModel.getM5());
						}
						if(dataModel.getM6().equals("") == false && dataModel.getM6().equals("-") == false && dataModel.getM6().isEmpty() == false){
							sumM6Outsourcing = sumM6Outsourcing + Double.parseDouble(dataModel.getM6());
						}
						if(dataModel.getM7().equals("") == false && dataModel.getM7().equals("-") == false && dataModel.getM7().isEmpty() == false){
							sumM7Outsourcing = sumM7Outsourcing + Double.parseDouble(dataModel.getM7());
						}
						if(dataModel.getM8().equals("") == false && dataModel.getM8().equals("-") == false && dataModel.getM8().isEmpty() == false){
							sumM8Outsourcing = sumM8Outsourcing + Double.parseDouble(dataModel.getM8());
						}
						if(dataModel.getM9().equals("") == false && dataModel.getM9().equals("-") == false && dataModel.getM9().isEmpty() == false){
							sumM9Outsourcing = sumM9Outsourcing + Double.parseDouble(dataModel.getM9());
						}
						if(dataModel.getM10().equals("") == false && dataModel.getM10().equals("-") == false && dataModel.getM10().isEmpty() == false){
							sumM10Outsourcing = sumM10Outsourcing + Double.parseDouble(dataModel.getM10());
						}
						if(dataModel.getM11().equals("") == false && dataModel.getM11().equals("-") == false && dataModel.getM11().isEmpty() == false){
							sumM11Outsourcing = sumM11Outsourcing + Double.parseDouble(dataModel.getM11());
						}
						if(dataModel.getM12().equals("") == false && dataModel.getM12().equals("-") == false && dataModel.getM12().isEmpty() == false){
							sumM12Outsourcing = sumM12Outsourcing + Double.parseDouble(dataModel.getM12());
						}
						if(dataModel.getSumAmount().equals("") == false && dataModel.getSumAmount().equals("-") == false && dataModel.getSumAmount().isEmpty() == false){
							sumAmountOutsourcing = sumAmountOutsourcing + Double.parseDouble(dataModel.getSumAmount());
						}
						if(dataModel.getNetAmount().equals("") == false && dataModel.getNetAmount().equals("-") == false && dataModel.getNetAmount().isEmpty() == false){
							sumNetAmountOutsourcing = sumNetAmountOutsourcing + Double.parseDouble(dataModel.getNetAmount());
						}
					}
					
					/*---------------사업합계 + 본부합계 구분---------------*/
					String flag = "NON";
					if(cnt+1 <= excelModels.size()){
						if(cnt+1 == excelModels.size()){
							flag = "TOTAL";
						}else{
							TestExcelModel nextDataModel = excelModels.get(cnt+1);
							if(dataModel.getProjectName().equals(nextDataModel.getProjectName()) == false){
								flag = "SUM";
							}
						}
					}
					
					/*---------------사업합계, 본부합계 셀 생성---------------*/
					if(flag.equals("SUM") || flag.equals("TOTAL")){
						//----------사업합계 row1 생성----------
						HSSFRow sumOneRow = sheet1.createRow(rowNum+1);  
						sumOneRow.setHeight((short)350);
						
						HSSFCell sumObjCell_1_1 = sumOneRow.createCell(1);
						sumObjCell_1_1.setCellValue("사업 계");
						sumObjCell_1_1.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_2 = sumOneRow.createCell(2);
						sumObjCell_1_2.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_3 = sumOneRow.createCell(3);
						sumObjCell_1_3.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_4 = sumOneRow.createCell(4);
						sumObjCell_1_4.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_5 = sumOneRow.createCell(5);
						sumObjCell_1_5.setCellValue("매출");
						sumObjCell_1_5.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_6 = sumOneRow.createCell(6);
						sumObjCell_1_6.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_7 = sumOneRow.createCell(7);
						sumObjCell_1_7.setCellStyle(styleSumGubun);
						
						HSSFCell sumObjCell_1_8 = sumOneRow.createCell(8);
						sumObjCell_1_8.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_9 = sumOneRow.createCell(9);
						sumObjCell_1_9.setCellValue(sumM1Sales);
						sumObjCell_1_9.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_10 = sumOneRow.createCell(10);
						sumObjCell_1_10.setCellValue(sumM2Sales);
						sumObjCell_1_10.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_11 = sumOneRow.createCell(11);
						sumObjCell_1_11.setCellValue(sumM3Sales);
						sumObjCell_1_11.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_12 = sumOneRow.createCell(12);
						sumObjCell_1_12.setCellValue(sumM4Sales);
						sumObjCell_1_12.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_13 = sumOneRow.createCell(13);
						sumObjCell_1_13.setCellValue(sumM5Sales);
						sumObjCell_1_13.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_14 = sumOneRow.createCell(14);
						sumObjCell_1_14.setCellValue(sumM6Sales);
						sumObjCell_1_14.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_15 = sumOneRow.createCell(15);
						sumObjCell_1_15.setCellValue(sumM7Sales);
						sumObjCell_1_15.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_16 = sumOneRow.createCell(16);
						sumObjCell_1_16.setCellValue(sumM8Sales);
						sumObjCell_1_16.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_17 = sumOneRow.createCell(17);
						sumObjCell_1_17.setCellValue(sumM9Sales);
						sumObjCell_1_17.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_18 = sumOneRow.createCell(18);
						sumObjCell_1_18.setCellValue(sumM10Sales);
						sumObjCell_1_18.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_19 = sumOneRow.createCell(19);
						sumObjCell_1_19.setCellValue(sumM11Sales);
						sumObjCell_1_19.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_20 = sumOneRow.createCell(20);
						sumObjCell_1_20.setCellValue(sumM12Sales);
						sumObjCell_1_20.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_21 = sumOneRow.createCell(21);
						sumObjCell_1_21.setCellValue(sumAllSales);
						sumObjCell_1_21.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_22 = sumOneRow.createCell(22);
						sumObjCell_1_22.setCellValue(insertComma(sumAmountSales));
						sumObjCell_1_22.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_23 = sumOneRow.createCell(23);
						sumObjCell_1_23.setCellValue(insertComma(sumNetAmountSales));
						sumObjCell_1_23.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_1_24 = sumOneRow.createCell(24);
						sumObjCell_1_24.setCellStyle(styleSum);
						
						//----------사업합계 row2 생성----------
						HSSFRow sumTwoRow = sheet1.createRow(rowNum+2);  
						sumTwoRow.setHeight((short)350);
						
						HSSFCell sumObjCell_2_1 = sumTwoRow.createCell(1);
						sumObjCell_2_1.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_2 = sumTwoRow.createCell(2);
						sumObjCell_2_2.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_3 = sumTwoRow.createCell(3);
						sumObjCell_2_3.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_4 = sumTwoRow.createCell(4);
						sumObjCell_2_4.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_5 = sumTwoRow.createCell(5);
						sumObjCell_2_5.setCellValue("외주");
						sumObjCell_2_5.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_6 = sumTwoRow.createCell(6);
						sumObjCell_2_6.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_7 = sumTwoRow.createCell(7);
						sumObjCell_2_7.setCellStyle(styleSumGubun);
						
						HSSFCell sumObjCell_2_8 = sumTwoRow.createCell(8);
						sumObjCell_2_8.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_9 = sumTwoRow.createCell(9);
						sumObjCell_2_9.setCellValue(sumM1Outsourcing);
						sumObjCell_2_9.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_10 = sumTwoRow.createCell(10);
						sumObjCell_2_10.setCellValue(sumM2Outsourcing);
						sumObjCell_2_10.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_11 = sumTwoRow.createCell(11);
						sumObjCell_2_11.setCellValue(sumM3Outsourcing);
						sumObjCell_2_11.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_12 = sumTwoRow.createCell(12);
						sumObjCell_2_12.setCellValue(sumM4Outsourcing);
						sumObjCell_2_12.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_13 = sumTwoRow.createCell(13);
						sumObjCell_2_13.setCellValue(sumM5Outsourcing);
						sumObjCell_2_13.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_14 = sumTwoRow.createCell(14);
						sumObjCell_2_14.setCellValue(sumM6Outsourcing);
						sumObjCell_2_14.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_15 = sumTwoRow.createCell(15);
						sumObjCell_2_15.setCellValue(sumM7Outsourcing);
						sumObjCell_2_15.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_16 = sumTwoRow.createCell(16);
						sumObjCell_2_16.setCellValue(sumM8Outsourcing);
						sumObjCell_2_16.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_17 = sumTwoRow.createCell(17);
						sumObjCell_2_17.setCellValue(sumM9Outsourcing);
						sumObjCell_2_17.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_18 = sumTwoRow.createCell(18);
						sumObjCell_2_18.setCellValue(sumM10Outsourcing);
						sumObjCell_2_18.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_19 = sumTwoRow.createCell(19);
						sumObjCell_2_19.setCellValue(sumM11Outsourcing);
						sumObjCell_2_19.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_20 = sumTwoRow.createCell(20);
						sumObjCell_2_20.setCellValue(sumM12Outsourcing);
						sumObjCell_2_20.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_21 = sumTwoRow.createCell(21);
						sumObjCell_2_21.setCellValue(sumAllOutsourcing);
						sumObjCell_2_21.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_22 = sumTwoRow.createCell(22);
						sumObjCell_2_22.setCellValue(insertComma(sumAmountOutsourcing));
						sumObjCell_2_22.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_23 = sumTwoRow.createCell(23);
						sumObjCell_2_23.setCellValue(insertComma(sumNetAmountOutsourcing));
						sumObjCell_2_23.setCellStyle(styleSum);
						
						HSSFCell sumObjCell_2_24 = sumTwoRow.createCell(24);
						sumObjCell_2_24.setCellStyle(styleSum);
						
						for(short rowCnt = 1; rowCnt < 5; rowCnt++){
							sheet1.addMergedRegion(new Region(rowNum+1 ,rowCnt ,rowNum+2 ,rowCnt));
						}
						
						//본부합계 데이터
						//총합계 매출											//총 합계 외주
						sumTotalM1Sales += sumM1Sales;						sumTotalM1Outsourcing += sumM1Outsourcing;
						sumTotalM2Sales += sumM2Sales;						sumTotalM2Outsourcing += sumM2Outsourcing;
						sumTotalM3Sales += sumM3Sales;						sumTotalM3Outsourcing += sumM3Outsourcing;
						sumTotalM4Sales += sumM4Sales;						sumTotalM4Outsourcing += sumM4Outsourcing;
						sumTotalM5Sales += sumM5Sales;						sumTotalM5Outsourcing += sumM5Outsourcing;
						sumTotalM6Sales += sumM6Sales;						sumTotalM6Outsourcing += sumM6Outsourcing;
						sumTotalM7Sales += sumM7Sales;						sumTotalM7Outsourcing += sumM7Outsourcing;
						sumTotalM8Sales += sumM8Sales;						sumTotalM8Outsourcing += sumM8Outsourcing;
						sumTotalM9Sales += sumM9Sales;						sumTotalM9Outsourcing += sumM9Outsourcing;
						sumTotalM10Sales += sumM10Sales;					sumTotalM10Outsourcing += sumM10Outsourcing;
						sumTotalM11Sales += sumM11Sales;					sumTotalM11Outsourcing += sumM11Outsourcing;
						sumTotalM12Sales += sumM12Sales;					sumTotalM12Outsourcing += sumM12Outsourcing;
						sumTotalAllSales += sumAllSales;					sumTotalAllOutsourcing += sumAllOutsourcing;
						sumTotalAmountSales += sumAmountSales;				sumTotalAmountOutsourcing += sumAmountOutsourcing;
						sumTotalNetAmountSales += sumNetAmountSales;		sumTotalNetAmountOutsourcing += sumNetAmountOutsourcing;
						
						//데이터 초기화
						sumM1Sales = (double) 0;							sumM1Outsourcing = (double) 0;
						sumM2Sales = (double) 0;							sumM2Outsourcing = (double) 0;
						sumM3Sales = (double) 0;							sumM3Outsourcing = (double) 0;
						sumM4Sales = (double) 0;							sumM4Outsourcing = (double) 0;
						sumM5Sales = (double) 0;							sumM5Outsourcing = (double) 0;
						sumM6Sales = (double) 0;							sumM6Outsourcing = (double) 0;
						sumM7Sales = (double) 0;							sumM7Outsourcing = (double) 0;
						sumM8Sales = (double) 0;							sumM8Outsourcing = (double) 0;
						sumM9Sales = (double) 0;							sumM9Outsourcing = (double) 0;
						sumM10Sales = (double) 0;							sumM10Outsourcing = (double) 0;
						sumM11Sales = (double) 0;							sumM11Outsourcing = (double) 0;
						sumM12Sales = (double) 0;							sumM12Outsourcing = (double) 0;
						sumAllSales = (double) 0;							sumAllOutsourcing = (double) 0;
						sumAmountSales = (double) 0;						sumAmountOutsourcing = (double) 0;
						sumNetAmountSales = (double) 0;						sumNetAmountOutsourcing = (double) 0;
						
						sumRowNum = sumRowNum+2;
						if(flag.equals("TOTAL")){
							//본부합계 데이터
							//---------------총합계 row1 생성---------------
							HSSFRow sumTotalOneRow = sheet1.createRow(rowNum+3);
							sumTotalOneRow.setHeight((short)350);
							
							HSSFCell sumTotalObjCell_1_1 = sumTotalOneRow.createCell(1);
							sumTotalObjCell_1_1.setCellValue("[본부 합계]");
							sumTotalObjCell_1_1.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_2 = sumTotalOneRow.createCell(2);
							sumTotalObjCell_1_2.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_3 = sumTotalOneRow.createCell(3);
							sumTotalObjCell_1_3.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_4 = sumTotalOneRow.createCell(4);
							sumTotalObjCell_1_4.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_5 = sumTotalOneRow.createCell(5);
							sumTotalObjCell_1_5.setCellValue("매출");
							sumTotalObjCell_1_5.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_6 = sumTotalOneRow.createCell(6);
							sumTotalObjCell_1_6.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_7 = sumTotalOneRow.createCell(7);
							sumTotalObjCell_1_7.setCellStyle(styleTotalGubun);
							
							HSSFCell sumTotalObjCell_1_8 = sumTotalOneRow.createCell(8);
							sumTotalObjCell_1_8.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_9 = sumTotalOneRow.createCell(9);
							sumTotalObjCell_1_9.setCellValue(sumTotalM1Sales);
							sumTotalObjCell_1_9.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_10 = sumTotalOneRow.createCell(10);
							sumTotalObjCell_1_10.setCellValue(sumTotalM2Sales);
							sumTotalObjCell_1_10.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_11 = sumTotalOneRow.createCell(11);
							sumTotalObjCell_1_11.setCellValue(sumTotalM3Sales);
							sumTotalObjCell_1_11.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_12 = sumTotalOneRow.createCell(12);
							sumTotalObjCell_1_12.setCellValue(sumTotalM4Sales);
							sumTotalObjCell_1_12.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_13 = sumTotalOneRow.createCell(13);
							sumTotalObjCell_1_13.setCellValue(sumTotalM5Sales);
							sumTotalObjCell_1_13.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_14 = sumTotalOneRow.createCell(14);
							sumTotalObjCell_1_14.setCellValue(sumTotalM6Sales);
							sumTotalObjCell_1_14.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_15 = sumTotalOneRow.createCell(15);
							sumTotalObjCell_1_15.setCellValue(sumTotalM7Sales);
							sumTotalObjCell_1_15.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_16 = sumTotalOneRow.createCell(16);
							sumTotalObjCell_1_16.setCellValue(sumTotalM8Sales);
							sumTotalObjCell_1_16.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_17 = sumTotalOneRow.createCell(17);
							sumTotalObjCell_1_17.setCellValue(sumTotalM9Sales);
							sumTotalObjCell_1_17.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_18 = sumTotalOneRow.createCell(18);
							sumTotalObjCell_1_18.setCellValue(sumTotalM10Sales);
							sumTotalObjCell_1_18.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_19 = sumTotalOneRow.createCell(19);
							sumTotalObjCell_1_19.setCellValue(sumTotalM11Sales);
							sumTotalObjCell_1_19.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_20 = sumTotalOneRow.createCell(20);
							sumTotalObjCell_1_20.setCellValue(sumTotalM12Sales);
							sumTotalObjCell_1_20.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_21 = sumTotalOneRow.createCell(21);
							sumTotalObjCell_1_21.setCellValue(sumTotalAllSales);
							sumTotalObjCell_1_21.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_22 = sumTotalOneRow.createCell(22);
							sumTotalObjCell_1_22.setCellValue(insertComma(sumTotalAmountSales));
							sumTotalObjCell_1_22.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_23 = sumTotalOneRow.createCell(23);
							sumTotalObjCell_1_23.setCellValue(insertComma(sumTotalNetAmountSales));
							sumTotalObjCell_1_23.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_1_24 = sumTotalOneRow.createCell(24);
							sumTotalObjCell_1_24.setCellStyle(styleTotal);
							
							//---------------총합계 row2 생성---------------
							HSSFRow sumTotalTwoRow = sheet1.createRow(rowNum+4);
							sumTotalTwoRow.setHeight((short)350);
							
							HSSFCell sumTotalObjCell_2_1 = sumTotalTwoRow.createCell(1);
							sumTotalObjCell_2_1.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_2 = sumTotalTwoRow.createCell(2);
							sumTotalObjCell_2_2.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_3 = sumTotalTwoRow.createCell(3);
							sumTotalObjCell_2_3.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_4 = sumTotalTwoRow.createCell(4);
							sumTotalObjCell_2_4.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_5 = sumTotalTwoRow.createCell(5);
							sumTotalObjCell_2_5.setCellValue("외주");
							sumTotalObjCell_2_5.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_6 = sumTotalTwoRow.createCell(6);
							sumTotalObjCell_2_6.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_7 = sumTotalTwoRow.createCell(7);
							sumTotalObjCell_2_7.setCellStyle(styleTotalGubun);
							
							HSSFCell sumTotalObjCell_2_8 = sumTotalTwoRow.createCell(8);
							sumTotalObjCell_2_8.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_9 = sumTotalTwoRow.createCell(9);
							sumTotalObjCell_2_9.setCellValue(sumTotalM1Outsourcing);
							sumTotalObjCell_2_9.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_10 = sumTotalTwoRow.createCell(10);
							sumTotalObjCell_2_10.setCellValue(sumTotalM2Outsourcing);
							sumTotalObjCell_2_10.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_11 = sumTotalTwoRow.createCell(11);
							sumTotalObjCell_2_11.setCellValue(sumTotalM3Outsourcing);
							sumTotalObjCell_2_11.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_12 = sumTotalTwoRow.createCell(12);
							sumTotalObjCell_2_12.setCellValue(sumTotalM4Outsourcing);
							sumTotalObjCell_2_12.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_13 = sumTotalTwoRow.createCell(13);
							sumTotalObjCell_2_13.setCellValue(sumTotalM5Outsourcing);
							sumTotalObjCell_2_13.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_14 = sumTotalTwoRow.createCell(14);
							sumTotalObjCell_2_14.setCellValue(sumTotalM6Outsourcing);
							sumTotalObjCell_2_14.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_15 = sumTotalTwoRow.createCell(15);
							sumTotalObjCell_2_15.setCellValue(sumTotalM7Outsourcing);
							sumTotalObjCell_2_15.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_16 = sumTotalTwoRow.createCell(16);
							sumTotalObjCell_2_16.setCellValue(sumTotalM8Outsourcing);
							sumTotalObjCell_2_16.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_17 = sumTotalTwoRow.createCell(17);
							sumTotalObjCell_2_17.setCellValue(sumTotalM9Outsourcing);
							sumTotalObjCell_2_17.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_18 = sumTotalTwoRow.createCell(18);
							sumTotalObjCell_2_18.setCellValue(sumTotalM10Outsourcing);
							sumTotalObjCell_2_18.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_19 = sumTotalTwoRow.createCell(19);
							sumTotalObjCell_2_19.setCellValue(sumTotalM11Outsourcing);
							sumTotalObjCell_2_19.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_20 = sumTotalTwoRow.createCell(20);
							sumTotalObjCell_2_20.setCellValue(sumTotalM12Outsourcing);
							sumTotalObjCell_2_20.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_21 = sumTotalTwoRow.createCell(21);
							sumTotalObjCell_2_21.setCellValue(sumTotalAllOutsourcing);
							sumTotalObjCell_2_21.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_22 = sumTotalTwoRow.createCell(22);
							sumTotalObjCell_2_22.setCellValue(insertComma(sumTotalAmountOutsourcing));
							sumTotalObjCell_2_22.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_23 = sumTotalTwoRow.createCell(23);
							sumTotalObjCell_2_23.setCellValue(insertComma(sumTotalNetAmountOutsourcing));
							sumTotalObjCell_2_23.setCellStyle(styleTotal);
							
							HSSFCell sumTotalObjCell_2_24 = sumTotalTwoRow.createCell(24);
							sumTotalObjCell_2_24.setCellStyle(styleTotal);
							
							//셀병합
							for(short rowCnt = 1; rowCnt < 5; rowCnt++){
								sheet1.addMergedRegion(new Region(rowNum+3 ,rowCnt ,rowNum+4 ,rowCnt));
							}
						}
					}
				}
				
				//프로젝트명 셀병합
				for(int cnt = 0; cnt < projectNameRowNumList.size(); cnt++){
					int minFlag = (int)projectNameRowNumList.get(cnt);
					if(cnt+1 == projectNameRowNumList.size()){
						sheet1.addMergedRegion(new Region(minFlag ,(short)1 ,rowNum ,(short)1));
					}else{
						int maxFlag = (int)projectNameRowNumList.get(cnt+1)-3;
						sheet1.addMergedRegion(new Region(minFlag ,(short)1 ,maxFlag ,(short)1));
					}
				}
				
				//파일생성
				String nowTime = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
				fileDirectory = "C:\\";
				fileName = "CPMS_"+baseName+"_"+nowTime+".xls";
				fileOut = new FileOutputStream(fileDirectory+fileName); // xls 파일 및 경로 지정
				
			} else if(innerFlag.equals("BUSINESS")){
				selectListModels = testService.selectList(selectListModel);
				
				int widthArr[] = {300, 10000, 10000, 10000, 10000};

				//열너비 조정
				for(int count = 0; count < widthArr.length; count++){
					sheet1.setColumnWidth(count, widthArr[count]);
				}
				
				//헤더 스타일
				HSSFCellStyle styleHead = wb.createCellStyle();
				HSSFFont headFont = wb.createFont();
				headFont.setFontHeightInPoints((short)20);
				headFont.setUnderline(headFont.U_SINGLE);
				styleHead.setFont(headFont);
				styleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				styleHead.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				styleHead.setFillForegroundColor(HSSFColor.WHITE.index);
				styleHead.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				
				//메뉴 스타일
				HSSFCellStyle styleMenu = wb.createCellStyle();
				HSSFFont menuFont = wb.createFont();
				menuFont.setFontHeightInPoints((short)11);
				menuFont.setBoldweight(menuFont.BOLDWEIGHT_BOLD);
				styleMenu.setFont(menuFont);
				styleMenu.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				styleMenu.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				styleMenu.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
				styleMenu.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleMenu.setBorderBottom(CellStyle.BORDER_THIN);
				styleMenu.setBorderLeft(CellStyle.BORDER_THIN);
				styleMenu.setBorderRight(CellStyle.BORDER_THIN);
				styleMenu.setBorderTop(CellStyle.BORDER_THIN);
				
				//데이터 스타일
				HSSFCellStyle styleMain = wb.createCellStyle();
				styleMain.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				styleMain.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				styleMain.setFillForegroundColor(HSSFColor.WHITE.index);
				styleMain.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				styleMain.setBorderBottom(CellStyle.BORDER_THIN);
				styleMain.setBorderLeft(CellStyle.BORDER_THIN);
				styleMain.setBorderRight(CellStyle.BORDER_THIN);
				styleMain.setBorderTop(CellStyle.BORDER_THIN);

				/*---------------HEADER---------------*/
				//1열
				HSSFRow row0 = sheet1.createRow(0);  // sheet에 row0 생성
				row0.setHeight((short)650);
				
				HSSFCell objCell0_1 = row0.createCell(1);
				String bussinessNowTime = new SimpleDateFormat("yyyy년MM월dd일HH시").format(Calendar.getInstance().getTime());
				objCell0_1.setCellValue("사업자 현황("+bussinessNowTime+")");
				objCell0_1.setCellStyle(styleHead);
				
				//2열
				HSSFRow row1 = sheet1.createRow(1);  // sheet에 row1 생성
				row1.setHeight((short)350);
				HSSFCell objCell1_1 = row1.createCell(1);
				objCell1_1.setCellStyle(styleHead);
				
				sheet1.addMergedRegion(new Region(0 ,(short)1 ,0 ,(short)4));
				sheet1.addMergedRegion(new Region(1 ,(short)1 ,1 ,(short)4));
				
				/*---------------MENU---------------*/
				HSSFRow row2 = sheet1.createRow(2);  // sheet에 row3 생성
				row2.setHeight((short)350);
				
				HSSFCell objCell2_1 = row2.createCell(1);
				objCell2_1.setCellValue("사업자명");
				objCell2_1.setCellStyle(styleMenu);
				
				HSSFCell objCell2_2 = row2.createCell(2);
				objCell2_2.setCellValue("사업자 번호");
				objCell2_2.setCellStyle(styleMenu);
				
				HSSFCell objCell2_3 = row2.createCell(3);
				objCell2_3.setCellValue("등록 분류");
				objCell2_3.setCellStyle(styleMenu);
				
				HSSFCell objCell2_4 = row2.createCell(4);
				objCell2_4.setCellValue("사업자 구분");
				objCell2_4.setCellStyle(styleMenu);
				
				/*---------------DATA---------------*/
				for(int cnt = 0; cnt < selectListModels.size(); cnt++){
					HSSFRow dataRow = sheet1.createRow(cnt+3);  // sheet에 row3 생성
					dataRow.setHeight((short)350);
					
					HSSFCell dataObjCell1 = dataRow.createCell(1);
					dataObjCell1.setCellValue(selectListModels.get(cnt).getPartnerName());
					dataObjCell1.setCellStyle(styleMain);
					
					HSSFCell dataObjCell2 = dataRow.createCell(2);
					dataObjCell2.setCellValue(selectListModels.get(cnt).getBusinessNumber());
					dataObjCell2.setCellStyle(styleMain);
					
					HSSFCell dataObjCell13 = dataRow.createCell(3);
					dataObjCell13.setCellValue(selectListModels.get(cnt).getBusinessDescription());
					dataObjCell13.setCellStyle(styleMain);
					
					HSSFCell dataObjCell4 = dataRow.createCell(4);
					dataObjCell4.setCellValue(selectListModels.get(cnt).getPartnerDescription());
					dataObjCell4.setCellStyle(styleMain);
				}

				String nowTime = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
				fileDirectory = "C:\\";
				fileName = "CPMS_사업자 현황_"+nowTime+".xls";
				fileOut = new FileOutputStream(fileDirectory+fileName); // xls 파일 및 경로 지정			
			}
			
			
			System.out.println("xls 파일생성 완료("+fileDirectory+fileName+")");
			wb.write(fileOut); // 파일생성
			fileOut.close(); // 닫기
			System.out.println("excelDownload() end..");
			
			
		}catch(Exception e){
			System.out.println(e.toString());
			throw new ServletException(e.toString());
		}
		
		return excelModels;
		
	}
	//콤마추가
	public String insertComma(Double num) {
		DecimalFormat decFormat = new DecimalFormat("###,###,###,###,###,###,###");
		return decFormat.format(num).toString();
	}

}
