package com.certiware.backend.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certiware.backend.mapper.PartnerMapper;
import com.certiware.backend.mapper.TestMapper;
import com.certiware.backend.model.SelectProgress;
import com.certiware.backend.model.TestExcelModel;
import com.certiware.backend.model.common.UserModel;
import com.certiware.backend.model.partner.SelectListModel;

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
	/*
	public List<UserModel> selectTest(UserModel userModel) throws Exception{
		return testMapper.selectTest(userModel);
	}
	*/
	public List<SelectProgress> testDate() throws Exception {

		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		
		Date startDate = df.parse("2016-01-01");
		
		Date endDate = df.parse("2017-07-01");
		List<String> strings = new ArrayList<String>();
		
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(startDate);
		 /*
		 System.out.println(cal.getTime());
		 System.out.println(df.format((cal.getTime())));
		 
		 System.out.println(startDate.compareTo(df.parse(df.format((cal.getTime())))));
		 
		 if(0 == startDate.compareTo(df.parse(df.format((cal.getTime()))))){
			 System.out.println("끝");
		 }
		 */
		 
		 
		while(!(0 == endDate.compareTo(df.parse(df.format((cal.getTime())))))){
			
			cal.add(Calendar.MONTH, 1);
			
			//월을 넣고
			strings.add(df.format((cal.getTime())).substring(0, 6));
			
			
			
		}
		
		/*
		for (String string : strings) {
			
			System.out.println(string);
			
		}
*/
		String query = "";
		
		query += " SELECT PROJECTID, MANPOWERNAME, " + System.getProperty("line.separator") ;
		
		int intdex = 1;
		
		for (String string : strings) {
			
			query += " CASE WHEN SUBSTRING(DATE_FORMAT(MONTH, '%Y%m%d'),1,6) = '" + string + "' THEN ROUND(MM, 2) ELSE 0 END AS M" + intdex  + ", " + System.getProperty("line.separator") ;
			intdex++;
			
		}
		
		//query += " 'test' as test   " + System.getProperty("line.separator");
		
		query = query.substring(0, query.length() -4) + System.getProperty("line.separator");
		
		query += " FROM   TB_MANPOWERMM " + System.getProperty("line.separator");
		
		System.out.println(query);
			
		
		UserModel userModel = new UserModel();
		userModel.setUserName(query);
		
		return testMapper.selectTest(userModel);		
			
	}

}
