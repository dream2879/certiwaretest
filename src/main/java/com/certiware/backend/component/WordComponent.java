package com.certiware.backend.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.springframework.stereotype.Component;

import com.certiware.backend.model.preproject.SelectManpowerMMModel;
import com.certiware.backend.model.preproject.SelectProjectPartnerModel;

@Component
public class WordComponent {
	
	public XWPFDocument makeContract(SelectProjectPartnerModel selectProjectPartnerModel, List<SelectManpowerMMModel> selectManpowerMMModels, int partnerCode) throws Exception {		
		XWPFDocument doc = null;
		File docFile = null;		
		SelectManpowerMMModel manpowerMMModel = selectManpowerMMModels.get(selectManpowerMMModels.size()-1);
		
		try {
			
			
			// local에 있는 템플릿 파일을 가져온다.
			String file = partnerCode < 3 ? "E:\\word\\template\\company.docx" : "E:\\word\\template\\free.docx";
			docFile = new File(file);
	        FileInputStream fis = null;
	        if (!docFile.exists()) {
	            throw new FileNotFoundException("The Word dcoument does not exist.");
	        }
	        try {

	            // Open the Word document file and instantiate the XWPFDocument
	            // class.
	            fis = new FileInputStream(docFile);
	            doc = new XWPFDocument(fis);
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
			
	        // 폰트 설정
	        XWPFStyles styles = doc.createStyles();
	        CTFonts fonts = CTFonts.Factory.newInstance();
	        fonts.setEastAsia("굴림체");
	        fonts.setHAnsi("굴림체");	        
	        styles.setDefaultFonts(fonts); 
	        
	        
	        List<XWPFTable> tables = doc.getTables();
			
	        System.out.println(tables.size());
	        
	        // 용역계약서 테이블
	        XWPFTable table1 = tables.get(0);
	        
	        // 프로젝트 이름
	        XWPFParagraph p1 = doc.createParagraph();
	        XWPFRun r1 = p1.createRun();
	        r1.setBold(true);
	        r1.setFontFamily("굴림체");	        
	        r1.setText(selectProjectPartnerModel.getProjectName());	        
	        table1.getRow(1).getCell(1).setParagraph(p1);
	        
	        // 계약금액	       	        
	        XWPFParagraph p2 = doc.createParagraph();
	        XWPFRun r2 = p2.createRun();
	        r2.setBold(true);
	        r2.setFontFamily("굴림체");
	        
	        
	        r2.setText("계약 금액 : 일금 "+ this.convertHangul(String.valueOf(manpowerMMModel.getTot())) +"원정 (\\" + this.insertComma((double)manpowerMMModel.getTot()) +".-)");	        
	        table1.getRow(2).getCell(1).setParagraph(p2);
	        
	        // M/M
	        XWPFParagraph p3 = doc.createParagraph();
	        XWPFRun r3 = p3.createRun();
	        r3.setBold(true);
	        r3.setFontFamily("굴림체");
	        r3.setText("M/M 단가 : 일금"+ this.convertHangul("5500000") +"원정 (\\" + this.insertComma((double)5500000) +".-)");
	        r3.addBreak();
	        r3.setText("계약 M/M : " + manpowerMMModel.getMm() + " M/M");	        
	        table1.getRow(3).getCell(1).setParagraph(p3);
	        
	        // 계약기간
	        XWPFParagraph p4 = doc.createParagraph();
	        XWPFRun r4 = p4.createRun();
	        r4.setBold(true);
	        r4.setFontFamily("굴림체");
	        r4.setText(this.makeDate(selectProjectPartnerModel.getStartDate(), selectProjectPartnerModel.getEndDate(), "A"));
	        table1.getRow(4).getCell(1).setParagraph(p4);
	        
	        // 용역업무
	        XWPFParagraph p5 = doc.createParagraph();
	        XWPFRun r5 = p5.createRun();
	        r5.setBold(true);
	        r5.setFontFamily("굴림체");
	        r5.setText(selectProjectPartnerModel.getProduct());
	        table1.getRow(5).getCell(2).setParagraph(p5);
	        
	        
	        // 투입인력1 - 기술등급 
	        XWPFParagraph p6 = doc.createParagraph();
	        XWPFRun r6 = p6.createRun();
	        r6.setBold(true);
	        r6.setFontFamily("굴림체");
	        r6.setText(selectManpowerMMModels.get(0).getDescription());
	        table1.getRow(6).getCell(2).setParagraph(p6);
	        
	        // 투입인력1 - 수행자
	        XWPFParagraph p7 = doc.createParagraph();
	        XWPFRun r7 = p7.createRun();
	        r7.setBold(true);
	        r7.setFontFamily("굴림체");
	        r7.setText(this.makeName(selectManpowerMMModels.get(0).getManpowerName()));
	        table1.getRow(6).getCell(4).setParagraph(p7);
	        
//	        // 투입인력2 - 기술등급 
//	        XWPFParagraph p8 = doc.createParagraph();
//	        XWPFRun r8 = p8.createRun();
//	        r8.setBold(true);
//	        r8.setFontFamily("굴림체");
//	        r8.setText("고급");
//	        table1.getRow(7).getCell(2).setParagraph(p8);
//	        
//	        // 투입인력2 - 수행자
//	        XWPFParagraph p9 = doc.createParagraph();
//	        XWPFRun r9 = p9.createRun();
//	        r9.setBold(true);
//	        r9.setFontFamily("굴림체");
//	        r9.setText("박 종 홍");
//	        table1.getRow(7).getCell(4).setParagraph(p9);
	        
	        
	        // 이행(납품) 장소
	        XWPFParagraph p10 = doc.createParagraph();
	        XWPFRun r10 = p10.createRun();
	        //r10.setBold(true);
	        r10.setFontFamily("굴림체");
	        r10.setText(selectProjectPartnerModel.getLocale());
	        table1.getRow(7).getCell(1).setParagraph(p10);
	        
	        // 특기사항
	        XWPFParagraph p11 = doc.createParagraph();
	        XWPFRun r11 = p11.createRun();
	        //r10.setBold(true);
	        r11.setFontFamily("굴림체");
	        r11.setText(selectProjectPartnerModel.getRemarks());
	        table1.getRow(12).getCell(1).setParagraph(p11);
	        
	        // 계약 내용
	        XWPFParagraph p12 = doc.createParagraph();
	        XWPFRun r12 = p12.createRun();
	        r12.setBold(true);
	        r12.setFontFamily("굴림체");
	        r12.setFontSize(11);	        
	        r12.addBreak();
	        r12.setText("   발주자인 (주)써티웨어(이하 \"갑\"이라 함)와 " + selectProjectPartnerModel.getPartnerName() +"(이하 \"을\"이라 함)는 ");
	        r12.setText("상호 대등한 지위에서 합의에 이르러 본 계약을 체결하고, 그 증거로 계약서 2부를 작성하여 각각 1부씩 보관한다.");
	        r12.addBreak();	 
	        table1.getRow(13).getCell(0).setParagraph(p12);
	        
	        
	        // 일자
	        XWPFParagraph p13 = doc.createParagraph();
	        p13.setAlignment(ParagraphAlignment.CENTER);	        
	        XWPFRun r13 = p13.createRun();
	        r13.setBold(true);
	        r13.setFontFamily("굴림체");
	        r13.setFontSize(11);
	        r13.setText(this.makeDate(Calendar.getInstance().getTime(), null, "B"));
	        r13.addBreak();
	        table1.getRow(14).getCell(0).setParagraph(p13);
	        
	        // 계약대상자 "갑"(포멧을 맞춰주기 위해 입력한다.)
	        XWPFParagraph p14 = doc.createParagraph();
	        p14.setIndentationLeft(1);
	        p14.setIndentationRight(1);
	        XWPFRun r14 = p14.createRun();	        
	        r14.setFontFamily("굴림체");
	        r14.setFontSize(11);
	        r14.addBreak();
	        r14.setText("회사명 : " + "(주)써티웨어");
	        r14.addBreak();
	        r14.setText("사업자(주민)등록번호 : " + "114-86-72452");
	        r14.addBreak();
	        r14.setText("주소 : ");
	        r14.addBreak();
	        r14.setText("서울특별시 송파구 법원로 11길 7, 씨동 1307호(문정동, 현대지식산업센터1-2)");
	        r14.addBreak();
	        r14.addBreak();
	        r14.setText("     대표이사 : " + "고 병 도" + "   (인)");
	        r14.addBreak();
	        table1.getRow(16).getCell(0).setParagraph(p14);	    
	        
	        // 계약대상자 "을"
	        XWPFParagraph p15 = doc.createParagraph();
	        p15.setIndentationLeft(1);
	        p15.setIndentationRight(1);
	        XWPFRun r15 = p15.createRun();	        
	        r15.setFontFamily("굴림체");
	        r15.setFontSize(11);
	        r15.addBreak();
	        
	        // 자사/법인인 경우에만 회사명을 입력해준다.
	        r15.setText("회사명 : " + (partnerCode < 3 ? selectProjectPartnerModel.getPartnerName() : ""));
	        r15.addBreak();
	        r15.setText("사업자(주민)등록번호 : " + this.makeNumber(String.valueOf(selectProjectPartnerModel.getBusinessNumber()), partnerCode));
	        r15.addBreak();
	        r15.setText("주소 : ");
	        r15.addBreak();
	        r15.setText(selectProjectPartnerModel.getAddress());
	        r15.addBreak();
	        r15.addBreak();
	        r15.setText("     대표(성명) : " + this.makeName(selectProjectPartnerModel.getCeoName()) + "   (인)");
	        r15.addBreak();
	        table1.getRow(16).getCell(1).setParagraph(p15);
	        
	        
	        // 자사/법인인 경우에만 투입인력을 가져온다.
	        if(partnerCode < 3){
		        // 투입인력내역서 테이블
		        XWPFTable table2 = tables.get(1);
		        	        
		        for(int i = 0; i < selectManpowerMMModels.size() - 1; i ++){       
		           
		        table2.addRow(table2.getRow(1), table2.getRows().size() - 2); 	        
		        XWPFTableRow row = table2.getRow(table2.getRows().size() - 2);       
		        
		        // cell을 청소한다.
		        this.clearCell(row);
		        
		        // 성명
		        XWPFParagraph pp1 = doc.createParagraph();
		        pp1.setAlignment(ParagraphAlignment.CENTER);	        
		        XWPFRun rr1 = pp1.createRun();	        
		        rr1.setFontFamily("굴림체");
		        rr1.setFontSize(10);
		        rr1.setText(selectManpowerMMModels.get(i).getManpowerName());	        
		        row.getCell(1).setParagraph(pp1);
		        
		        // 등급
		        XWPFParagraph pp2 = doc.createParagraph();
		        pp2.setAlignment(ParagraphAlignment.CENTER);	        
		        XWPFRun rr2 = pp2.createRun();	        
		        rr2.setFontFamily("굴림체");
		        rr2.setFontSize(10);
		        rr2.setText(selectManpowerMMModels.get(i).getDescription());	        
		        row.getCell(2).setParagraph(pp2);
		        
		        // 업무
		        XWPFParagraph pp3 = doc.createParagraph();
		        pp3.setAlignment(ParagraphAlignment.CENTER);	        
		        XWPFRun rr3 = pp2.createRun();	        
		        rr3.setFontFamily("굴림체");
		        rr3.setFontSize(10);
		        rr3.setText("");	        
		        row.getCell(3).setParagraph(pp3);
		        
		        // 투입기간(M/M)
		        XWPFParagraph pp4 = doc.createParagraph();
		        pp4.setAlignment(ParagraphAlignment.CENTER);	        
		        XWPFRun rr4 = pp4.createRun();	        
		        rr4.setFontFamily("굴림체");
		        rr4.setFontSize(10);
		        rr4.setText(this.makeDate(selectManpowerMMModels.get(i).getStartDate(), selectManpowerMMModels.get(i).getEndDate(), "C")
		        		+ "(" + selectManpowerMMModels.get(i).getMm() + "MM)");	        
		        row.getCell(4).setParagraph(pp4);	        
		        
		        // 단가(원/월)
		        XWPFParagraph pp5 = doc.createParagraph();
		        pp5.setAlignment(ParagraphAlignment.CENTER);	        
		        XWPFRun rr5 = pp5.createRun();	        
		        rr5.setFontFamily("굴림체");
		        rr5.setFontSize(10);
		        rr5.setText(this.insertComma((double)selectManpowerMMModels.get(i).getOutsourcingAmount()));      
		        row.getCell(5).setParagraph(pp5);	   
		        
		        // 총금액(원)
		        XWPFParagraph pp6 = doc.createParagraph();
		        pp6.setAlignment(ParagraphAlignment.CENTER);	        
		        XWPFRun rr6 = pp6.createRun();	        
		        rr6.setFontFamily("굴림체");
		        rr6.setFontSize(10);
		        rr6.setText(this.insertComma((double)selectManpowerMMModels.get(i).getTot()));	        
		        row.getCell(6).setParagraph(pp6);
		        
		        }       
		        
		        // 합계
		        XWPFTableRow row = table2.getRow(table2.getRows().size() - 1);
		        
		        // 투입기간(M/M)
		        XWPFParagraph pp7 = doc.createParagraph();
		        pp7.setAlignment(ParagraphAlignment.CENTER);	        
		        XWPFRun rr7 = pp7.createRun();	        
		        rr7.setFontFamily("굴림체");
		        rr7.setFontSize(10);
		        rr7.setText(manpowerMMModel.getMm() + " M/M");	        
		        row.getCell(2).setParagraph(pp7);
		        
		        // 총금액(원)
		        XWPFParagraph pp8 = doc.createParagraph();
		        pp8.setAlignment(ParagraphAlignment.CENTER);	        
		        XWPFRun rr8 = pp8.createRun();	        
		        rr8.setFontFamily("굴림체");
		        rr8.setFontSize(10);
		        rr8.setText(this.insertComma(manpowerMMModel.getTot()));        
		        row.getCell(4).setParagraph(pp8);
		        
		        // 공백 cell 제거
		         table2.removeRow(1);
	        }
			
		}catch(Exception e){
			StackTraceElement[] ste = e.getStackTrace();
			System.out.println("error !!!! " + ste[0].getLineNumber() +"/"+e.toString());
			throw new ServletException(e.toString());
		}
		
		return doc;
	}
	
	
	/******************************************************************************************************************/
	
		//콤마추가
		private String insertComma(double num) {
			DecimalFormat decFormat = new DecimalFormat("###,###,###,###,###,###,###");
			return decFormat.format(num).toString();
		}
		
		// 금액 한글
		private String convertHangul(String money){
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
		
		/**
		 * cell의 내용을 초기화 시켜준다.
		 * @param row
		 */
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
		
		/**
		 * 이름 사이사이 공백추가(ex "고 병 도")
		 * @param name
		 * @return
		 */
		private String makeName(String name){
			String result = "";
			
			for(int i=0;i<name.length();i++){
				
				result += name.charAt(i) + " ";
			}		
			result = result.substring(0, result.length() -1);
			
			
			return result;
		}
		
		/**
		 * 날짜를 변환하여 리턴한다.
		 * @param startDate
		 * @param endDate
		 * @param type
		 * @return
		 * @throws ParseException
		 */
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
				
				result += df.format(startDate) + "~" + df.format(endDate);
				
				break;	
			}
			
			return result;
		}
		
		/**
		 * 사업자번호와 주민등록번호를 각각의 양식에 맞게 변환한다
		 * @param number
		 * @param partnerCode
		 * @return
		 */
		private String makeNumber(String number, int partnerCode){
			
			if(partnerCode < 3){
				
				
				return number.substring(0,3) + "-" + number.substring(3,5) + "-" + number.substring(5) ;			
			}else{
				
				return number.substring(0,6) + "-" + number.substring(6);
				
			}		
		}


}
