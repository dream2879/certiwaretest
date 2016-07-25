package com.certiware.backend.component;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;

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
import org.springframework.stereotype.Component;

import com.certiware.backend.model.progress.SelectProgressListResModel;

@Component
public class ExcelComponent {
	
	
	/**
	 * 프로젝트 진행 현황 데이터를 가지고 엑셀을 만든다.
	 * @param selectProgressListResModels
	 * @return
	 * @throws Exception
	 */
	public HSSFWorkbook makeSelectProgressListExcel(List<SelectProgressListResModel> selectProgressListResModels) throws Exception {		
		
		HSSFWorkbook wb = new HSSFWorkbook();   // workbook 생성
				
		try{			
			
			HSSFSheet sheet1 = wb.createSheet("sheet1");  //sheet 생성	
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");			
			Calendar cal1 = Calendar.getInstance();
			
			/*--------------------셀스타일 설정--------------------*/
			//열너비 배열
			int widthArr[] = {	300,	// A열 : no mapping
								6000, 	// B열 : 프로젝트 명
								3000, 	// C열 : 이름
								4000,	// D열 : 등급
								4000, 	// E열 : 실소속
								1500, 	// F열 : 구분
								3500, 	// G열 : 계약단가
								3200, 	// H열 : 투입일
								3200, 	// I열 : 철수일
								2700, 	// J열 : M1
								2700,	// K열 : M2
								2700,	// L열 : M3
								2700,	// M열 : M4
								2700, 	// N열 : M5
								2700, 	// O열 : M6
								2700,	// P열 : M7
								2700,	// Q열 : M8
								2700,	// R열 : M9
								2700,	// S열 : M10
								2700,	// T열 : M11
								2700, 	// U열 : M12
								3000,	// V열 : M/M 합계
								4500,	// W열 : 매출액
								4500,	// X열 : 순매출액
								9000	// Y열 : 비고
							};
			
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
			
			//데이터 스타일(숫자, 왼쪽정렬)
			HSSFCellStyle styleNum = wb.createCellStyle();
			styleNum.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			styleNum.setVerticalAlignment(HSSFCellStyle.ALIGN_RIGHT);
			styleNum.setFillForegroundColor(HSSFColor.WHITE.index);
			styleNum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			styleNum.setBorderBottom(CellStyle.BORDER_THIN);
			styleNum.setBorderLeft(CellStyle.BORDER_THIN);
			styleNum.setBorderRight(CellStyle.BORDER_THIN);
			styleNum.setBorderTop(CellStyle.BORDER_THIN);			
			
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
			
			//사업계 스타일(숫자, 왼쪽정렬)
			HSSFCellStyle styleSumNum = wb.createCellStyle();
			styleSumNum.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			styleSumNum.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			styleSumNum.setFillForegroundColor(HSSFColor.TAN.index);
			styleSumNum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			styleSumNum.setBorderBottom(CellStyle.BORDER_THIN);
			styleSumNum.setBorderLeft(CellStyle.BORDER_THIN);
			styleSumNum.setBorderRight(CellStyle.BORDER_THIN);
			styleSumNum.setBorderTop(CellStyle.BORDER_THIN);
			
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
			
			//총합계 스타일
			HSSFCellStyle styleTotalNum = wb.createCellStyle();
			styleTotalNum.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			styleTotalNum.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			styleTotalNum.setFillForegroundColor(HSSFColor.CORAL.index);
			styleTotalNum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			styleTotalNum.setBorderBottom(CellStyle.BORDER_THIN);
			styleTotalNum.setBorderLeft(CellStyle.BORDER_THIN);
			styleTotalNum.setBorderRight(CellStyle.BORDER_THIN);
			styleTotalNum.setBorderTop(CellStyle.BORDER_THIN);
			
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
			
			
			/***************************************************************************************************************/
			
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
//			String baseName = "OSS본부";
//			String projectYear = String.valueOf(cal1.get(cal1.YEAR));
			
			
			//1열
			HSSFRow row0 = sheet1.createRow(0);  // sheet에 row0 생성
			row0.setHeight((short)650);
			
			HSSFCell objCell0_1 = row0.createCell(1);
			objCell0_1.setCellValue("프로젝트 진행현황 조회");
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
			objCell3_7.setCellStyle(styleMenu);
			
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
			objCell4_7.setCellStyle(styleMenu);
			
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
			
			// 반복문			
			Row row = null;
			Cell cell = null;
			int index = 5;
			for (SelectProgressListResModel selectProgressListResModel : selectProgressListResModels) {
				
				row = sheet1.createRow(index);							
				
				// 합계 부분에 쓸모없는 데이터가 들어가는 것을 막기위해서 사용
				boolean flag = true;				
				if(selectProgressListResModel.getProjectName() == null || selectProgressListResModel.getManpowerName().equals("합계") ){					
					flag=false;					
				}		
				
				// 프로젝트 이름
				cell = row.createCell(1);
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotal);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSum);
									
				}else{
					
					cell.setCellStyle(styleMain);
					
				}				
				cell.setCellValue( selectProgressListResModel.getProjectName() == null? "전체 합계" : selectProgressListResModel.getManpowerName().equals("합계") ? "소계" : selectProgressListResModel.getProjectName());	  
				
				// 투입인력 이름
				cell = row.createCell(2);
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotal);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSum);
									
				}else{
					
					cell.setCellStyle(styleMain);
					
				}	
				cell.setCellValue(selectProgressListResModel.getManpowerName().equals("합계")? "" : selectProgressListResModel.getManpowerName());
				
				
				// 등급
				cell = row.createCell(3);
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotal);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSum);
									
				}else{
					
					cell.setCellStyle(styleMain);
					
				}
				if(flag){
					cell.setCellValue(selectProgressListResModel.getRating());
				}				
				
				// 실소속
				cell = row.createCell(4);
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotal);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSum);
									
				}else{
					
					cell.setCellStyle(styleMain);
					
				}			
				if(flag){
					cell.setCellValue(selectProgressListResModel.getPartner());					
				}
				
				// 구분
				cell = row.createCell(5);
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotal);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSum);
									
				}else{
					
					cell.setCellStyle(styleMain);
					
				}
				cell.setCellValue(selectProgressListResModel.getFlag());
				
				
				// 계약단가
				cell = row.createCell(6);
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotalNum);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSumNum);
									
				}else{
					
					cell.setCellStyle(styleNum);
					
				}
				
				if(flag){
					cell.setCellValue(this.insertComma(selectProgressListResModel.getAmount()));
				}				
				
				// 투입일			
				cell = row.createCell(7);
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotal);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSum);
									
				}else{
					
					cell.setCellStyle(styleMain);
					
				}				
				if(flag){
					cell.setCellValue(df.format(selectProgressListResModel.getStartDate()));
				}
				
				
				// 종료일
				cell = row.createCell(8);
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotal);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSum);
									
				}else{
					
					cell.setCellStyle(styleMain);
					
				}				
				if(flag){
					cell.setCellValue(df.format(selectProgressListResModel.getEndDate()));
				}
				
				
				// 1월
				cell = row.createCell(9);				
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotalNum);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSumNum);
									
				}else{
					
					cell.setCellStyle(styleNum);
					
				}				
				cell.setCellValue(selectProgressListResModel.getM1());
				
				
				// 2월
				cell = row.createCell(10);
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotalNum);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSumNum);
									
				}else{
					
					cell.setCellStyle(styleNum);
					
				}
				cell.setCellValue(selectProgressListResModel.getM2());
				
				
				// 3월
				cell = row.createCell(11);				
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotalNum);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSumNum);
									
				}else{
					
					cell.setCellStyle(styleNum);
					
				}
				cell.setCellValue(selectProgressListResModel.getM3());
				
				
				// 4월
				cell = row.createCell(12);				
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotalNum);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSumNum);
									
				}else{
					
					cell.setCellStyle(styleNum);
					
				}
				cell.setCellValue(selectProgressListResModel.getM4());
				
				
				// 5월
				cell = row.createCell(13);				
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotalNum);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSumNum);
									
				}else{
					
					cell.setCellStyle(styleNum);
					
				}
				cell.setCellValue(selectProgressListResModel.getM5());
				
				
				// 6월
				cell = row.createCell(14);				
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotalNum);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSumNum);
									
				}else{
					
					cell.setCellStyle(styleNum);
					
				}
				cell.setCellValue(selectProgressListResModel.getM6());
				
				
				// 7월
				cell = row.createCell(15);				
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotalNum);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSumNum);
									
				}else{
					
					cell.setCellStyle(styleNum);
					
				}
				cell.setCellValue(selectProgressListResModel.getM7());
				
				
				// 8월
				cell = row.createCell(16);				
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotalNum);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSumNum);
									
				}else{
					
					cell.setCellStyle(styleNum);
					
				}
				cell.setCellValue(selectProgressListResModel.getM8());
				
				
				// 9월
				cell = row.createCell(17);				
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotalNum);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSumNum);
									
				}else{
					
					cell.setCellStyle(styleNum);
					
				}
				cell.setCellValue(selectProgressListResModel.getM9());
				
				
				// 10월
				cell = row.createCell(18);				
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotalNum);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSumNum);
									
				}else{
					
					cell.setCellStyle(styleNum);
					
				}
				cell.setCellValue(selectProgressListResModel.getM10());				
				
				// 11월
				cell = row.createCell(19);
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotalNum);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSumNum);
									
				}else{
					
					cell.setCellStyle(styleNum);
					
				}
				cell.setCellValue(selectProgressListResModel.getM11());
				
				
				// 12월
				cell = row.createCell(20);				
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotalNum);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSumNum);
									
				}else{
					
					cell.setCellStyle(styleNum);
					
				}
				cell.setCellValue(selectProgressListResModel.getM12());
				
				
				// 합계
				cell = row.createCell(21);				
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotalNum);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSumNum);
									
				}else{
					
					cell.setCellStyle(styleNum);
					
				}
				cell.setCellValue(selectProgressListResModel.getmSum());
				
				
				// 매출액
				cell = row.createCell(22);				
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotalNum);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSumNum);
									
				}else{
					
					cell.setCellStyle(styleNum);
					
				}
				cell.setCellValue(this.insertComma(selectProgressListResModel.getTot()));
				
				
				// 순매출액
				cell = row.createCell(23);				
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotalNum);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSumNum);
									
				}else{
					
					cell.setCellStyle(styleNum);
					
				}
				cell.setCellValue(this.insertComma(selectProgressListResModel.getNet()));
				
				
				// 비고
				cell = row.createCell(24);
				if(selectProgressListResModel.getProjectName() == null){
					
					cell.setCellStyle(styleTotal);
									
				}else if(selectProgressListResModel.getManpowerName().equals("합계")){
					
					cell.setCellStyle(styleSum);
									
				}else{
					
					cell.setCellStyle(styleMain);
					
				}				
				if(flag){
					cell.setCellValue(selectProgressListResModel.getRemarks());
				}
				
				
				index++;				
			}         
			
			
	
            
		}catch(Exception e)
		{
			System.out.println("error : " + e.getStackTrace());
			throw new ServletException(e.toString());
		}
		finally {
			
		}
		
		return wb;
		
	}
	
	
	//콤마추가
	private String insertComma(long num) {
		DecimalFormat decFormat = new DecimalFormat("###,###,###,###,###,###,###");
		return decFormat.format(num).toString();
	}
	
		

}
