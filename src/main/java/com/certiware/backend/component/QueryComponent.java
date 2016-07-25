package com.certiware.backend.component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.certiware.backend.model.common.QueryModel;
import com.certiware.backend.model.main.ManpowerMMStatisticsModel;
import com.certiware.backend.model.main.ManpowerStatisticsModel;
import com.certiware.backend.model.main.ProjectStatisticsModel;
import com.certiware.backend.model.main.SelectDashboardReqModel;
import com.certiware.backend.model.progress.SelectProgressListReqModel;

@Component
public class QueryComponent {
	
	/**
	 * 프로젝트 진행 현황을 조회하기 위한 쿼리 작성
	 * @param queryModel
	 * @param selectProgressListReqModel
	 * @return
	 * @throws Exception
	 */
	public QueryModel makeSelectProgressListQuery(QueryModel queryModel, SelectProgressListReqModel selectProgressListReqModel) throws Exception {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(selectProgressListReqModel.getStartDate());
		
		// 시작일과 마지막일 세팅
		Date startDate =  df.parse(cal.get(cal.YEAR) +"-01-01");
		Date endDate = df.parse(cal.get(cal.YEAR) +"-12-01");		
		
		// 시작일 달력에 다시 세팅
		cal.setTime(startDate);
	
		String A = " WHERE MONTH BETWEEN '" + startDate + "' AND '" + endDate +"'"; 
		
		// Dynamic Query를 만들기위한 로직
		String dQuery0 = "";
		String dQuery1 = "";
		String dQuery2 = "";
		String dQuery3 = "";
		String dQuery4 = "";
		
		int index = 1;		
		do{
			
			//System.out.println(df.format((cal.getTime())));
			
			dQuery0 += "A.M" + index + "," + System.getProperty("line.separator");
			
			dQuery1 += "SUM(A.M"+ index + ") AS M" + index +"," + System.getProperty("line.separator");
			
			dQuery2 += "SUM(A.M"+index+") + ";
			
			dQuery3 += "CASE WHEN MONTH = '" + df.format((cal.getTime()))  + "' THEN ROUND(MM, 4) ELSE 0 END AS M"+ index +"," + System.getProperty("line.separator");
			
			dQuery4 += "CASE WHEN B.AMOUNT = 0 THEN 0 ELSE SUM(A.M"+ index +") END AS M"+ index +"," + System.getProperty("line.separator");
			
			cal.add(Calendar.MONTH, 1);			
						
			index++;		
			
		}while(!(-1 == endDate.compareTo(df.parse(df.format((cal.getTime()))))) && index <= 12);		
		
		dQuery2 = dQuery2.substring(0, dQuery2.length() -3) + "AS MSUM";
		dQuery3 = dQuery3.substring(0, dQuery3.length() -3);

		/*
		System.out.println("dQuery1 : " +dQuery1);
		System.out.println("dQuery2 : " +dQuery2);
		System.out.println("dQuery3 : " +dQuery3);
		System.out.println("dQuery4 : " +dQuery4);
		*/	
		
		// 쿼리문시작
		String query = "";		
		
		query += "  SELECT A.PROJECTID,																																																							" + System.getProperty("line.separator") ;
		query += "    		 B.PROJECTNAME,																																																							" + System.getProperty("line.separator") ;
		query += "         A.MANPOWERNAME,                                                                                                            " + System.getProperty("line.separator") ;
		query += "         C.DESCRIPTION AS RATING,                                                                                                   " + System.getProperty("line.separator") ;
		query += "         D.DESCRIPTION AS PARTNER,                                                                                                  " + System.getProperty("line.separator") ;
		query += "         A.FLAG,                                                                                                                    " + System.getProperty("line.separator") ;
		query += "         A.AMOUNT,                                                                                                                  " + System.getProperty("line.separator") ;
		query += "         A.TMPAMOUNT,                                                                                                               " + System.getProperty("line.separator") ;
		query += "         A.STARTDATE,                                                                                                               " + System.getProperty("line.separator") ;
		query += "         A.ENDDATE,                                                                                                                 " + System.getProperty("line.separator") ;
		query += "         -- 반복문 시작                                                                                                                      " + System.getProperty("line.separator") ;
//		query += "         A.M1,                                                                                                                      " + System.getProperty("line.separator") ;
//		query += "         A.M2,                                                                                                                      " + System.getProperty("line.separator") ;
		query += dQuery0;
		query += "         -- 반복문 끝                                                                                                                      " + System.getProperty("line.separator") ;
		query += "         A.MSUM,                                                                                                                    " + System.getProperty("line.separator") ;
		query += "         A.TOT,                                                                                                                     " + System.getProperty("line.separator") ;
		query += "         A.NET,                                                                                                                     " + System.getProperty("line.separator") ;
		query += "         A.REMARKS                                                                                                                  " + System.getProperty("line.separator") ;
		query += "    FROM (  SELECT CASE WHEN B.PROJECTID IS NOT NULL THEN 0 ELSE 1 END AS IDSORT,                                                   " + System.getProperty("line.separator") ;
		query += "                   IFNULL(B.PROJECTID, 99999) AS PROJECTID,                                                                        " + System.getProperty("line.separator") ;
		query += "                   CASE WHEN B.MANPOWERNAME IS NOT NULL THEN 0 ELSE 1 END AS NAMESORT,                                              " + System.getProperty("line.separator") ;
		query += "                   IFNULL(B.MANPOWERNAME, '합계') AS MANPOWERNAME,                                                                  " + System.getProperty("line.separator") ;
		query += "                   B.RATINGCODE,                                                                                                    " + System.getProperty("line.separator") ;
		query += "                   B.PARTNERID,                                                                                                     " + System.getProperty("line.separator") ;
		query += "                   B.FLAG,                                                                                                          " + System.getProperty("line.separator") ;
		query += "                   B.AMOUNT,                                                                                                        " + System.getProperty("line.separator") ;
		query += "                   B.TMPAMOUNT,                                                                                                     " + System.getProperty("line.separator") ;
		query += "                   B.STARTDATE,                                                                                                     " + System.getProperty("line.separator") ;
		query += "                   B.ENDDATE,                                                                                                       " + System.getProperty("line.separator") ;
		
		query += dQuery1;
		
//		query += "                   -- 반복문 시작                                                                                                   " + System.getProperty("line.separator") ;
//		query += "                   SUM(A.M1) AS M1,                                                                                                 " + System.getProperty("line.separator") ;
//		query += "                   SUM(A.M2) AS M2,                                                                                                 " + System.getProperty("line.separator") ;
//		query += "                   -- 반복문 끝                                                                                                     " + System.getProperty("line.separator") ;

		query += "                   SUM(A.MSUM) AS MSUM,                                                                                             " + System.getProperty("line.separator") ;
		query += "                   SUM(A.MSUM * B.AMOUNT) AS TOT,                                                                                   " + System.getProperty("line.separator") ;
		query += "                   SUM( (B.AMOUNT * A.MSUM) - (A.MSUM * B.TMPAMOUNT)) AS NET,                                                       " + System.getProperty("line.separator") ;
		query += "                   B.REMARKS                                                                                                        " + System.getProperty("line.separator") ;
		query += "              FROM (  SELECT A.PROJECTID,                                                                                             " + System.getProperty("line.separator") ;
		query += "                             MANPOWERNAME,                                                                                          " + System.getProperty("line.separator") ;
		query += "                             -- 반복문 시작                                                                                         " + System.getProperty("line.separator") ;
//		query += "                             SUM(M1) AS M1,                                                                                         " + System.getProperty("line.separator") ;
//		query += "                             SUM(M2) AS M2,                                                                                         " + System.getProperty("line.separator") ;
//		query += "                             SUM(M1) + SUM(M2) AS MSUM                                                                              " + System.getProperty("line.separator") ;
		query += dQuery1;
		query += dQuery2;
		query += "                        		-- 반복문 끝                                                                                            " + System.getProperty("line.separator") ;
		query += "                        FROM (SELECT A.PROJECTID,                                                                                     " + System.getProperty("line.separator") ;
		query += "                                     MANPOWERNAME,                                                                                  " + System.getProperty("line.separator") ;
		query += "                                     -- 반복문 수행 시작                                                                            " + System.getProperty("line.separator") ;
		
//		query += "                                     CASE WHEN MONTH = '2016-01-01 오전 12:00:00' THEN ROUND(MM, 4) ELSE 0 END AS M1,               " + System.getProperty("line.separator") ;
//		query += "                                     CASE WHEN MONTH = '2016-02-02 오전 12:00:00' THEN ROUND(MM, 4) ELSE 0 END AS M2                " + System.getProperty("line.separator") ;
		query += dQuery3;
		
		query += "                                		-- 반복문 수행 끝                                                                               " + System.getProperty("line.separator") ;		
		query += "                                FROM TB_MANPOWERMM A, TB_PROJECT B                                                                               " + System.getProperty("line.separator") ;
		query += " 								  WHERE MONTH BETWEEN '" + df.format(startDate) + "' AND '" + df.format(endDate) +"' ";
		
		if(selectProgressListReqModel.getDeptCode() != null && selectProgressListReqModel.getDeptCode() != ""){
			
			query += " AND B.DEPTCODE = '" + selectProgressListReqModel.getDeptCode() + "' "+ System.getProperty("line.separator") ;
			
		}
		
		if(selectProgressListReqModel.getProjectName() != null && selectProgressListReqModel.getProjectName() != ""){			
			
			query += " AND B.PROJECTNAME LIKE '%" + selectProgressListReqModel.getProjectName() + "%' "+ System.getProperty("line.separator") ;
		}
		
		query += "                                ) A                                                                               " + System.getProperty("line.separator") ;
		query += "                    GROUP BY PROJECTID, MANPOWERNAME) A,                                                                            " + System.getProperty("line.separator") ;
		query += "                   (SELECT PROJECTID,                                                                                               " + System.getProperty("line.separator") ;
		query += "                           MANPOWERNAME,                                                                                            " + System.getProperty("line.separator") ;
		query += "                           RATINGCODE,                                                                                              " + System.getProperty("line.separator") ;
		query += "                           PARTNERID,                                                                                               " + System.getProperty("line.separator") ;
		query += "                           '매출' AS FLAG,                                                                                          " + System.getProperty("line.separator") ;
		query += "                           SELLINGAMOUNT AS AMOUNT,                                                                                 " + System.getProperty("line.separator") ;
		query += "                           OUTSOURCINGAMOUNT AS TMPAMOUNT,                                                                          " + System.getProperty("line.separator") ;
		query += "                           STARTDATE,                                                                                               " + System.getProperty("line.separator") ;
		query += "                           ENDDATE,                                                                                                 " + System.getProperty("line.separator") ;
		query += "                           REMARKS                                                                                                  " + System.getProperty("line.separator") ;
		query += "                      FROM TB_MANPOWER) B                                                                                           " + System.getProperty("line.separator") ;
		query += "             WHERE A.PROJECTID = B.PROJECTID AND A.MANPOWERNAME = B.MANPOWERNAME                                                    " + System.getProperty("line.separator") ;
		query += "          GROUP BY B.PROJECTID, B.MANPOWERNAME WITH ROLLUP                                                                          " + System.getProperty("line.separator") ;
		query += "          UNION ALL                                                                                                                 " + System.getProperty("line.separator") ;
		query += "            SELECT CASE WHEN B.PROJECTID IS NOT NULL THEN 0 ELSE 1 END AS IDSORT,                                                   " + System.getProperty("line.separator") ;
		query += "                   IFNULL(B.PROJECTID, 99999) AS PROJECTID,                                                                        " + System.getProperty("line.separator") ;
		query += "                   CASE WHEN B.MANPOWERNAME IS NOT NULL THEN 0 ELSE 1 END AS NAMESORT,                                              " + System.getProperty("line.separator") ;
		query += "                   IFNULL(B.MANPOWERNAME, '합계') AS MANPOWERNAME,                                                                  " + System.getProperty("line.separator") ;
		query += "                   B.RATINGCODE,                                                                                                    " + System.getProperty("line.separator") ;
		query += "                   B.PARTNERID,                                                                                                     " + System.getProperty("line.separator") ;
		query += "                   B.FLAG,                                                                                                          " + System.getProperty("line.separator") ;
		query += "                   B.AMOUNT,                                                                                                        " + System.getProperty("line.separator") ;
		query += "                   B.TMPAMOUNT,                                                                                                     " + System.getProperty("line.separator") ;
		query += "                   B.STARTDATE,                                                                                                     " + System.getProperty("line.separator") ;
		query += "                   B.ENDDATE,                                                                                                       " + System.getProperty("line.separator") ;
		query += "                   -- 반복문 시작                                                                                                   " + System.getProperty("line.separator") ;
//		query += "                   CASE WHEN B.AMOUNT = 0 THEN 0 ELSE SUM(A.M1) END AS M1,                                                          " + System.getProperty("line.separator") ;
//		query += "                   CASE WHEN B.AMOUNT = 0 THEN 0 ELSE SUM(A.M2) END AS M2,                                                          " + System.getProperty("line.separator") ;
		query += dQuery4;
		
		query += "                   -- 반복문 끝                                                                                                     " + System.getProperty("line.separator") ;
		query += "                   CASE WHEN B.AMOUNT = 0 THEN 0 ELSE SUM(A.MSUM) END AS MSUM,                                                      " + System.getProperty("line.separator") ;
		query += "                   SUM(A.MSUM * B.AMOUNT) AS TOT,                                                                                   " + System.getProperty("line.separator") ;
		query += "                   SUM( (A.MSUM * B.TMPAMOUNT) - (B.AMOUNT * A.MSUM)) AS NET,                                                       " + System.getProperty("line.separator") ;
		query += "                   B.REMARKS                                                                                                        " + System.getProperty("line.separator") ;
		query += "              FROM (  SELECT A.PROJECTID,                                                                                             " + System.getProperty("line.separator") ;
		query += "                             MANPOWERNAME,                                                                                          " + System.getProperty("line.separator") ;
		query += "                              -- 반복문 시작                                                                                        " + System.getProperty("line.separator") ;
		query += dQuery1;
		query += dQuery2;
//		query += "                             SUM(M1) AS M1,                                                                                         " + System.getProperty("line.separator") ;
//		query += "                             SUM(M2) AS M2,                                                                                         " + System.getProperty("line.separator") ;
//		query += "                             SUM(M1) + SUM(M2) AS MSUM                                                                              " + System.getProperty("line.separator") ;
		query += "                        		-- 반복문 끝                                                                                            " + System.getProperty("line.separator") ;
		query += "                        FROM (SELECT A.PROJECTID,                                                                                     " + System.getProperty("line.separator") ;
		query += "                                     MANPOWERNAME,                                                                                  " + System.getProperty("line.separator") ;
		query += "                                     -- 반복문 수행 시작                                                                            " + System.getProperty("line.separator") ;
//		query += "                                     CASE WHEN MONTH = '2016-01-01 오전 12:00:00' THEN ROUND(MM, 4) ELSE 0 END AS M1,               " + System.getProperty("line.separator") ;
//		query += "                                     CASE WHEN MONTH = '2016-02-02 오전 12:00:00' THEN ROUND(MM, 4) ELSE 0 END AS M2                " + System.getProperty("line.separator") ;
		query += dQuery3;
		query += "                                		-- 반복문 수행 끝                                                                               " + System.getProperty("line.separator") ;
		query += "                                FROM TB_MANPOWERMM A, TB_PROJECT B                                                                               " + System.getProperty("line.separator") ;
		query += " 								  WHERE MONTH BETWEEN '" + df.format(startDate) + "' AND '" + df.format(endDate) +"' ";
		
		if(selectProgressListReqModel.getDeptCode() != null && selectProgressListReqModel.getDeptCode() != ""){
			
			query += " AND B.DEPTCODE = '" + selectProgressListReqModel.getDeptCode() + "' "+ System.getProperty("line.separator") ;
			
		}
		
		if(selectProgressListReqModel.getProjectName() != null && selectProgressListReqModel.getProjectName() != ""){			
			
			query += " AND B.PROJECTNAME LIKE '%" + selectProgressListReqModel.getProjectName() + "%' "+ System.getProperty("line.separator") ;
		}
		
		query += "                                ) A                                                                               " + System.getProperty("line.separator") ;
		query += "                    GROUP BY PROJECTID, MANPOWERNAME) A,                                                                            " + System.getProperty("line.separator") ;
		query += "                   (SELECT PROJECTID,                                                                                               " + System.getProperty("line.separator") ;
		query += "                           MANPOWERNAME,                                                                                            " + System.getProperty("line.separator") ;
		query += "                           RATINGCODE,                                                                                              " + System.getProperty("line.separator") ;
		query += "                           PARTNERID,                                                                                               " + System.getProperty("line.separator") ;
		query += "                           '외주' AS FLAG,                                                                                          " + System.getProperty("line.separator") ;
		query += "                           OUTSOURCINGAMOUNT AS AMOUNT,                                                                             " + System.getProperty("line.separator") ;
		query += "                           SELLINGAMOUNT AS TMPAMOUNT,                                                                              " + System.getProperty("line.separator") ;
		query += "                           STARTDATE,                                                                                               " + System.getProperty("line.separator") ;
		query += "                           ENDDATE,                                                                                                 " + System.getProperty("line.separator") ;
		query += "                           REMARKS                                                                                                  " + System.getProperty("line.separator") ;
		query += "                      FROM TB_MANPOWER) B                                                                                           " + System.getProperty("line.separator") ;
		query += "             WHERE A.PROJECTID = B.PROJECTID AND A.MANPOWERNAME = B.MANPOWERNAME                                                    " + System.getProperty("line.separator") ;
		query += "          GROUP BY B.PROJECTID, B.MANPOWERNAME WITH ROLLUP                                                                          " + System.getProperty("line.separator") ;
		query += "                                              ) A                                                                                   " + System.getProperty("line.separator") ;
		query += "         LEFT OUTER JOIN TB_PROJECT B ON A.PROJECTID = B.PROJECTID                                                                  " + System.getProperty("line.separator") ;
		query += "         LEFT OUTER JOIN TB_RATINGCODE C ON A.RATINGCODE = C.RATINGCODE                                                             " + System.getProperty("line.separator") ;
		query += "         LEFT OUTER JOIN                                                                                                            " + System.getProperty("line.separator") ;
		query += "         (SELECT CASE WHEN PARTNERCODE >= 3 THEN '프리랜서' ELSE PARTNERNAME END AS DESCRIPTION,                                    " + System.getProperty("line.separator") ;
		query += "                 PARTNERID                                                                                                          " + System.getProperty("line.separator") ;
		query += "            FROM TB_PARTNER) D                                                                                                      " + System.getProperty("line.separator") ;
		query += "            ON A.PROJECTID = D.PARTNERID                                                                                            " + System.getProperty("line.separator") ;		
		query += "ORDER BY A.PROJECTID,                                                                                                               " + System.getProperty("line.separator") ;
		query += "         A.IDSORT,                                                                                                                  " + System.getProperty("line.separator") ;
		query += "         A.MANPOWERNAME,                                                                                                            " + System.getProperty("line.separator") ;
		query += "         A.NAMESORT,                                                                                                                " + System.getProperty("line.separator") ;
		query += "         A.FLAG                                                                                                                     " + System.getProperty("line.separator") ;
		
		
		
		queryModel.setQuery(query);
		
		return queryModel;
		
	}// end makeSelectProgressListQuery
	
	
	/**
	 * 프로젝트 갯수 정보를 조회하기 위한 쿼리 작성
	 * @param selectDashboardReqModel
	 * @return
	 * @throws Exception
	 */
	public String makeSelectProjectStatisticsQuery(SelectDashboardReqModel selectDashboardReqModel) throws Exception{		
			
		String query = "";
				
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Date startDate = null, endDate = null;
		String deptCode = selectDashboardReqModel.getDeptCode();
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		System.out.println(df.format(cal2.getTime()));
		
		cal1.setTime(selectDashboardReqModel.getYear());
		
		// 시작일 설정
		startDate = df.parse(cal1.get(cal1.YEAR)+"-01-01");
		cal1.setTime(startDate);
		
		/*
		 * 마지막일 설정
		 * 입력받은 년도가 현재년도라면 현재월의 마지막일을 endDate로 하고 
		 * 아니라면 해당 년도의 마지막일(12-31)을 마지막일로 한다.
		*/
		if(cal1.get(cal1.YEAR) == cal2.get(cal2.YEAR)){
			endDate = df.parse(cal2.get(cal2.YEAR)+"-"
								+ (Integer.parseInt(String.valueOf(cal2.get(cal2.MONTH))) + 1 < 10 ? "0" + (Integer.parseInt(String.valueOf(cal2.get(cal2.MONTH))) + 1)  : Integer.parseInt(String.valueOf(cal2.get(cal2.MONTH))) + 1 ) 
								+"-"+ String.valueOf(cal2.getActualMaximum(cal2.DAY_OF_MONTH)));
		}else{
			endDate = df.parse(cal1.get(cal1.YEAR)+"-12-31");			
		}
		
		// 반복문 시작
		int index = 1;
		String dynamicQuery = "";
		String month = "";
		
		do{
			// 숫가자 10이하면 앞에 0을 붙여주기 위한 로직
			month = index < 10 ? "0" + index : String.valueOf(index);
			
			// DynamicQuery 부분
			dynamicQuery += " IFNULL(SUM(CASE WHEN '"+ cal1.get(cal1.YEAR) +"-"+ month +"-01' BETWEEN DATE_FORMAT(STARTDATE, '%Y-%m-01') AND DATE_FORMAT(ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END),0) AS M"+ index +", " + System.getProperty("line.separator");		
			
			// 달력과 인덱스 값을 증가시킨다.
			cal1.add(Calendar.MONTH, 1);
			index++;			
			
		}while((index <= 12) && !(-1 >= endDate.compareTo(df.parse(df.format((cal1.getTime()))))));
		
		
		// 쿼리작성		
		query += " SELECT " + System.getProperty("line.separator");
		
		query += dynamicQuery;
		
		query += " COUNT(*) AS TOTAL " + System.getProperty("line.separator");
		query += " FROM TB_PROJECT " + System.getProperty("line.separator");
		query += " WHERE STARTDATE <= '" + df.format(endDate) + "' AND ENDDATE >= '" + df.format(startDate) + "'" + System.getProperty("line.separator");
		
		// deptCode
		if(deptCode != null && deptCode != ""){
			
			query += " AND DEPTCODE = '" + deptCode + "' "+ System.getProperty("line.separator") + System.getProperty("line.separator");
			
		}
		
		return query;	
	}// end makeSelectProjectStatisticsQuery
	
	
	/**
	 * 프로젝트 투입인력 정보를 구한다.
	 * @param selectDashboardReqModel
	 * @return
	 * @throws Exception
	 */
	public String makeSelectManpowerStatisticsQuery(SelectDashboardReqModel selectDashboardReqModel) throws Exception{
		
		String query = "";
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Date startDate = null, endDate = null;
		String deptCode = selectDashboardReqModel.getDeptCode();
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		System.out.println(df.format(cal2.getTime()));
		
		cal1.setTime(selectDashboardReqModel.getYear());
		
		// 시작일 설정
		startDate = df.parse(cal1.get(cal1.YEAR)+"-01-01");
		cal1.setTime(startDate);
		
		/*
		 * 마지막일 설정
		 * 입력받은 년도가 현재년도라면 현재월의 마지막일을 endDate로 하고 
		 * 아니라면 해당 년도의 마지막일(12-31)을 마지막일로 한다.
		*/
		if(cal1.get(cal1.YEAR) == cal2.get(cal2.YEAR)){
			endDate = df.parse(cal2.get(cal2.YEAR)+"-"
								+ (Integer.parseInt(String.valueOf(cal2.get(cal2.MONTH))) + 1 < 10 ? "0" + (Integer.parseInt(String.valueOf(cal2.get(cal2.MONTH))) + 1)  : Integer.parseInt(String.valueOf(cal2.get(cal2.MONTH))) + 1 ) 
								+"-"+ String.valueOf(cal2.getActualMaximum(cal2.DAY_OF_MONTH)));
		}else{
			endDate = df.parse(cal1.get(cal1.YEAR)+"-12-31");			
		}
		
		// 반복문 시작
		int index = 1;
		String dynamicQuery1 = "";
		String dynamicQuery2 = "";
		
		String month = "";
		
		do{
			// 숫가자 10이하면 앞에 0을 붙여주기 위한 로직
			month = index < 10 ? "0" + index : String.valueOf(index);
			
			// DynamicQuery 부분
			dynamicQuery1 += "M" + index + " + ";
			dynamicQuery2 += " IFNULL(SUM(CASE WHEN '"+ cal1.get(cal1.YEAR) +"-"+ month +"-01' BETWEEN DATE_FORMAT(A.STARTDATE, '%Y-%m-01') AND DATE_FORMAT(A.ENDDATE, '%Y-%m-01') THEN 1 ELSE 0 END),0) AS M"+ index +", " + System.getProperty("line.separator");		
			
			// 달력과 인덱스 값을 증가시킨다.
			cal1.add(Calendar.MONTH, 1);
			index++;			
			
		}while((index <= 12) && !(-1 >= endDate.compareTo(df.parse(df.format((cal1.getTime()))))));
		
		// 마지막의 "+ "를 제거하고 Alias를 추가한다.
		dynamicQuery1 = dynamicQuery1.substring(0, dynamicQuery1.length() -2) + " AS MMTOTAL" + System.getProperty("line.separator");
		
		
		// 쿼리 작성
		query += " SELECT *, " + System.getProperty("line.separator");
		
		query += dynamicQuery1;
		
		query += "FROM ( SELECT" + System.getProperty("line.separator");
		
		query += dynamicQuery2;
		
		query += " COUNT(*) AS REALTOTAL " + System.getProperty("line.separator");
		query += " FROM TB_MANPOWER A, TB_PROJECT B " + System.getProperty("line.separator");
		query += " WHERE A.PROJECTID = B.PROJECTID " + System.getProperty("line.separator");
		query += " AND B.STARTDATE <= '" + df.format(endDate) + "' AND B.ENDDATE >= '" + df.format(startDate) + "'" + System.getProperty("line.separator");
		
		// deptCode
		if(deptCode != null && deptCode != ""){
			
			query += " AND B.DEPTCODE = '" + deptCode + "' "+ System.getProperty("line.separator") + System.getProperty("line.separator");
			
		}	
		
		query += " ) A " + System.getProperty("line.separator");
		
		
		return query;	
	} // end makeSelectManpowerStatisticsQuery
	
	
	/**
	 * 월별 매출 현황을 구한다.
	 * @param selectDashboardReqModel
	 * @return
	 * @throws Exception
	 */
	public String makeSelectManpowerMMStatisticsQuery(SelectDashboardReqModel selectDashboardReqModel) throws Exception{
		
		String query = "";
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Date startDate = null, endDate = null;
		String deptCode = selectDashboardReqModel.getDeptCode();
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		System.out.println(df.format(cal2.getTime()));
		
		cal1.setTime(selectDashboardReqModel.getYear());
		
		// 시작일 설정
		startDate = df.parse(cal1.get(cal1.YEAR)+"-01-01");
		cal1.setTime(startDate);
		
		/*
		 * 마지막일 설정
		 * 입력받은 년도가 현재년도라면 현재월의 마지막일을 endDate로 하고 
		 * 아니라면 해당 년도의 마지막일(12-31)을 마지막일로 한다.
		 * TB_MANPOWERMM 테이블에 DAY는 01로 넣어 끝자리를 01로 함
		*/
		if(cal1.get(cal1.YEAR) == cal2.get(cal2.YEAR)){
			endDate = df.parse(cal2.get(cal2.YEAR)+"-"
								+ (Integer.parseInt(String.valueOf(cal2.get(cal2.MONTH))) + 1 < 10 ? "0" + (Integer.parseInt(String.valueOf(cal2.get(cal2.MONTH))) + 1)  : Integer.parseInt(String.valueOf(cal2.get(cal2.MONTH))) + 1 ) 
								+"-01");
		}else{
			endDate = df.parse(cal1.get(cal1.YEAR)+"-12-01");			
		}
		
		// 반복문 시작
		int index = 1;
		String dynamicQuery = "";		
		
		String month = "";
		
		do{
			// 숫가자 10이하면 앞에 0을 붙여주기 위한 로직
			month = index < 10 ? "0" + index : String.valueOf(index);
			
			// DynamicQuery 부분			
			dynamicQuery += "IFNULL(TRUNCATE(SUM(CASE WHEN '"+ cal1.get(cal1.YEAR) +"-"+ month +"-01' = MONTH THEN SELLINGAMOUNT ELSE 0 END / 10000000), 1), 0) AS M" + index +", " + System.getProperty("line.separator");		
			
			// 달력과 인덱스 값을 증가시킨다.
			cal1.add(Calendar.MONTH, 1);
			index++;			
			
		}while((index <= 12) && !(-1 >= endDate.compareTo(df.parse(df.format((cal1.getTime()))))));
		
		System.out.println(dynamicQuery);
		
		// 쿼리작석
		
		query += " SELECT " + System.getProperty("line.separator");
		
		query += dynamicQuery;
		
		query += " IFNULL(SUM(SELLINGAMOUNT),0) AS TOTAL " + System.getProperty("line.separator");
		query += " FROM ( " + System.getProperty("line.separator");
		query += " 			SELECT DATE_FORMAT(A.MONTH, '%Y-%m-%d') AS MONTH, FLOOR(A.MM * B.SELLINGAMOUNT) AS SELLINGAMOUNT " + System.getProperty("line.separator");
		query += " 			FROM TB_MANPOWERMM A, TB_MANPOWER B, TB_PROJECT C " + System.getProperty("line.separator");
		query += " 			WHERE A.PROJECTID = B.PROJECTID AND B.MANPOWERNAME = A.MANPOWERNAME " + System.getProperty("line.separator");
		query += " 			AND B.PARTNERID = C.PARTNERID " + System.getProperty("line.separator");		
		query += " 			AND  A.MONTH BETWEEN '" + df.format(startDate) +"' AND '" + df.format(endDate) + "'" + System.getProperty("line.separator");
		
		// deptCode
		if(deptCode != null && deptCode != ""){
					
			query += " AND C.DEPTCODE = '" + deptCode + "' "+ System.getProperty("line.separator") + System.getProperty("line.separator");
					
		}
		
		query += " ) A";
		
		return query;	
	}// end makeSelectManpowerMMStatisticsQuery
	

}
