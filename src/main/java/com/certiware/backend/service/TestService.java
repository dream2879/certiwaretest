package com.certiware.backend.service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certiware.backend.mapper.PartnerMapper;
import com.certiware.backend.mapper.TestMapper;
import com.certiware.backend.model.SelectProgress;
import com.certiware.backend.model.TestExcelModel;
import com.certiware.backend.model.common.UserModel;
import com.certiware.backend.model.partner.SelectListModel;
import com.certiware.backend.model.progress.SelectProgressListReqModel;
import com.certiware.backend.model.progress.SelectProgressListResModel;
import com.certiware.backend.model.project.SelectProjectListModel;

@Service
public class TestService {
	
	@Autowired
	TestMapper testMapper;
	@Autowired
	PartnerMapper partnerMapper;
	
	public List<TestExcelModel> selectExcel() throws Exception {
		return testMapper.selectExcel();
	}
	
	/**
	 * TB_PARTNER 테이블 조회(리스트)
	 * 조건에 따라 사용되는 쿼리문이 다르다.
	 * @return
	 * @throws Exception
	 */
	public List<SelectListModel> selectList(SelectListModel selectListModel) throws Exception{
		return partnerMapper.selectList(selectListModel);
	}

	public void DateTest(SelectProgressListReqModel selectProgressListReqModel) throws Exception {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Date startDate =  selectProgressListReqModel.getStartDate();
		Date endDate = selectProgressListReqModel.getEndDate();		
		
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal.setTime(startDate);
		cal2.setTime(endDate);
		
		System.out.println(cal.get(cal.YEAR));
		
//		double mm = (double) cal.get(cal.DAY_OF_MONTH) / (double) cal.getActualMaximum(cal.DAY_OF_MONTH); // MM 구하기 근무일수/해당월의일수		
		//mm = (int) (mm * 10000) / 10000.0; // 소수점 4자리까지 구하기(버림)
		//System.out.println(df.format(cal.getTime()) + " : " + mm);
		double mm=0;
		
		// 시작점 확인
		boolean flag = true;		
		while(!(-1 == endDate.compareTo(df.parse(df.format((cal.getTime())))))){
			
			// 반복문 시작 loop 
			if(flag)
			{
//				System.out.println("계산 : " + (double) cal.get(cal.DAY_OF_MONTH) + " / " + (double) cal.getActualMaximum(cal.DAY_OF_MONTH));
				// MM 구하기 근무일수/해당월의일수
				mm = (double) cal.get(cal.DAY_OF_MONTH) / (double) cal.getActualMaximum(cal.DAY_OF_MONTH); 
				cal.set(cal.DATE, 1);
				
				flag = false;
				
			}
			// 반복문 중간
			else
			{				
				mm = 1.0;				
			}			
			
			System.out.print(df.format(cal.getTime()) + " : ");
			
			// 
			cal.add(Calendar.MONTH, 1);
			
			// 반복문 마지막 loop			
			if((-1 >= endDate.compareTo(df.parse(df.format((cal.getTime())))))){				
				
			// System.out.println("계산 : " + (double) cal2.get(cal2.DAY_OF_MONTH) + " / " + (double) cal2.getActualMaximum(cal2.DAY_OF_MONTH));
				
				// MM 구하기 근무일수/해당월의일수
				mm = (double) cal2.get(cal2.DAY_OF_MONTH) / (double) cal2.getActualMaximum(cal2.DAY_OF_MONTH);				
			}
			
			// 소수점 4자리까지 구하기(버림)
			mm = (int) (mm * 10000) / 10000.0; 
			
			System.out.println(mm);
			
		};	
		
		
		
		
		
//		// Dynamic Query를 만들기위한 로직
//		String dQuery0 = "";
//		String dQuery1 = "";
//		String dQuery2 = "";
//		String dQuery3 = "";
//		String dQuery4 = "";
//		int index = 1;
//		
//		do{
//			
//			//System.out.println(df.format((cal.getTime())));
//			
//			dQuery0 += "A.M" + index + "," + System.getProperty("line.separator");
//			
//			dQuery1 += "SUM(A.M"+ index + ") AS M" + index +"," + System.getProperty("line.separator");
//			
//			dQuery2 += "SUM(A.M"+index+") + ";
//			
//			dQuery3 += "CASE WHEN MONTH = '" + df.format((cal.getTime()))  + "' THEN ROUND(MM, 2) ELSE 0 END AS M"+ index +"," + System.getProperty("line.separator");
//			
//			dQuery4 += "CASE WHEN B.AMOUNT = 0 THEN 0 ELSE SUM(A.M"+ index +") END AS M"+ index +"," + System.getProperty("line.separator");
//			
//			cal.add(Calendar.MONTH, 1);			
//						
//			index++;		
//			
//		}while(!(-1 == endDate.compareTo(df.parse(df.format((cal.getTime()))))) && index <= 12);		
//		
//		dQuery2 = dQuery2.substring(0, dQuery2.length() -3) + "AS MSUM";
//		dQuery3 = dQuery3.substring(0, dQuery3.length() -3);

		
		
	}

}
