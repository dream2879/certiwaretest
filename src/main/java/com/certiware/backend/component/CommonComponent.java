package com.certiware.backend.component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CommonComponent {
	
	/**
	 * 날짜를 바라서 해당 년도의 첫째날이나 마지막날을 구해준다.
	 * @param date
	 * @param flag
	 * @return
	 * @throws ParseException
	 */
	public Date makeDate(Date date, String flag) throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date result = null;
		
		Calendar cal = Calendar.getInstance();				
		cal.setTime(date);		
		
		switch(flag){
		
			case("start") :
				result = df.parse(cal.get(cal.YEAR)+"-01-01");;
				break;
				
			case("end") :
				result = df.parse(cal.get(cal.YEAR)+"-"
						+ (Integer.parseInt(String.valueOf(cal.get(cal.MONTH))) + 1 < 10 ? "0" + (Integer.parseInt(String.valueOf(cal.get(cal.MONTH))) + 1)  : Integer.parseInt(String.valueOf(cal.get(cal.MONTH))) + 1 ) 
						+"-"+ String.valueOf(cal.getActualMaximum(cal.DAY_OF_MONTH)));
				break;			
		}
		
		return result;	

	}

}
