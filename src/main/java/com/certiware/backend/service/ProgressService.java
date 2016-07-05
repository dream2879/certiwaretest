package com.certiware.backend.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certiware.backend.mapper.ProgressMapper;
import com.certiware.backend.model.common.ManpowerMmModel;
import com.certiware.backend.model.common.ManpowerModel;
import com.certiware.backend.model.common.QueryModel;
import com.certiware.backend.model.common.UserModel;
import com.certiware.backend.model.progress.ModifyManpowerMmModel;
import com.certiware.backend.model.progress.ModifyManpowerModel;
import com.certiware.backend.model.progress.SelectProgressListReqModel;
import com.certiware.backend.model.progress.SelectProgressListResModel;

@Service
public class ProgressService {
	
	@Autowired
	ProgressMapper progressMapper;
	@Autowired
	CommonService commonService;
	
	/**
	 * TB_MANPOWER 테이블조회
	 * @param projectId:프로젝트아이디
	 * @return
	 * @throws Exception
	 */
	public List<ManpowerModel> selectManpowerList(int projectId) throws Exception{
		return progressMapper.selectManpowerList(projectId);
	}
	
	/**
	 * TB_MANPOWER 테이블 MERGE, DELETE 수행
	 * @param modifyManpowerModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean modifyManpower(ModifyManpowerModel modifyManpowerModel) throws Exception{		
	
		// merge
		for (ManpowerModel manpowerModel : modifyManpowerModel.getMergeManpowerModels()) {
			progressMapper.mergeManpower(manpowerModel);
		}
		
		
		// delete
		for (ManpowerModel manpowerModel : modifyManpowerModel.getDeleteManpowerModels()) {
			progressMapper.deleteManpower(manpowerModel);
		}
		
		return true;
	}
	
	/**
	 * TB_MANPOWERMM 테이블조회
	 * @param projectId:프로젝트ID
	 * @param manpowerName:투입인력이름
	 * @return
	 * @throws Exception
	 */
	public List<ManpowerMmModel> selectManpowerMmList(int projectId, String manpowerName) throws Exception{
		return progressMapper.selectManpowerMmList(projectId, manpowerName);
	}
	
	/**
	 * TB_MANPOWERMM 테이블 MERGE, DELETE
	 * @param modifyManpowerMmModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean modifyManpowerMm(ModifyManpowerMmModel modifyManpowerMmModel) throws Exception{
		
		// update
		for (ManpowerMmModel manpowerMmModel : modifyManpowerMmModel.getMergeManpowerMmModels()) {
			progressMapper.mergeManpowerMm(manpowerMmModel);
		}
		
		// delete
		for (ManpowerMmModel manpowerMmModel : modifyManpowerMmModel.getDeleteManpowerMmModels()) {
			progressMapper.deleteManpowerMm(manpowerMmModel);
		}	
		
		return true;
	}
	
	
	/**
	 * 프로젝트 진행현황 리스트를 가져오는 로직
	 * @param selectProgressListReqModel
	 * @return
	 * @throws Exception
	 */
	public List<SelectProgressListResModel> selectProgressList(SelectProgressListReqModel selectProgressListReqModel) throws Exception {
		QueryModel queryModel = new QueryModel();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Date startDate =  selectProgressListReqModel.getStartDate();
		Date endDate = selectProgressListReqModel.getEndDate();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		
		
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
			
			dQuery3 += "CASE WHEN MONTH = '" + df.format((cal.getTime()))  + "' THEN ROUND(MM, 2) ELSE 0 END AS M"+ index +"," + System.getProperty("line.separator");
			
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
		query += "              FROM (  SELECT PROJECTID,                                                                                             " + System.getProperty("line.separator") ;
		query += "                             MANPOWERNAME,                                                                                          " + System.getProperty("line.separator") ;
		query += "                             -- 반복문 시작                                                                                         " + System.getProperty("line.separator") ;
//		query += "                             SUM(M1) AS M1,                                                                                         " + System.getProperty("line.separator") ;
//		query += "                             SUM(M2) AS M2,                                                                                         " + System.getProperty("line.separator") ;
//		query += "                             SUM(M1) + SUM(M2) AS MSUM                                                                              " + System.getProperty("line.separator") ;
		query += dQuery1;
		query += dQuery2;
		query += "                        		-- 반복문 끝                                                                                            " + System.getProperty("line.separator") ;
		query += "                        FROM (SELECT PROJECTID,                                                                                     " + System.getProperty("line.separator") ;
		query += "                                     MANPOWERNAME,                                                                                  " + System.getProperty("line.separator") ;
		query += "                                     -- 반복문 수행 시작                                                                            " + System.getProperty("line.separator") ;
		
//		query += "                                     CASE WHEN MONTH = '2016-01-01 오전 12:00:00' THEN ROUND(MM, 2) ELSE 0 END AS M1,               " + System.getProperty("line.separator") ;
//		query += "                                     CASE WHEN MONTH = '2016-02-02 오전 12:00:00' THEN ROUND(MM, 2) ELSE 0 END AS M2                " + System.getProperty("line.separator") ;
		query += dQuery3;
		
		query += "                                		-- 반복문 수행 끝                                                                               " + System.getProperty("line.separator") ;
		query += "                                FROM TB_MANPOWERMM) A                                                                               " + System.getProperty("line.separator") ;
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
		query += "              FROM (  SELECT PROJECTID,                                                                                             " + System.getProperty("line.separator") ;
		query += "                             MANPOWERNAME,                                                                                          " + System.getProperty("line.separator") ;
		query += "                              -- 반복문 시작                                                                                        " + System.getProperty("line.separator") ;
		query += dQuery1;
		query += dQuery2;
//		query += "                             SUM(M1) AS M1,                                                                                         " + System.getProperty("line.separator") ;
//		query += "                             SUM(M2) AS M2,                                                                                         " + System.getProperty("line.separator") ;
//		query += "                             SUM(M1) + SUM(M2) AS MSUM                                                                              " + System.getProperty("line.separator") ;
		query += "                        		-- 반복문 끝                                                                                            " + System.getProperty("line.separator") ;
		query += "                        FROM (SELECT PROJECTID,                                                                                     " + System.getProperty("line.separator") ;
		query += "                                     MANPOWERNAME,                                                                                  " + System.getProperty("line.separator") ;
		query += "                                     -- 반복문 수행 시작                                                                            " + System.getProperty("line.separator") ;
//		query += "                                     CASE WHEN MONTH = '2016-01-01 오전 12:00:00' THEN ROUND(MM, 2) ELSE 0 END AS M1,               " + System.getProperty("line.separator") ;
//		query += "                                     CASE WHEN MONTH = '2016-02-02 오전 12:00:00' THEN ROUND(MM, 2) ELSE 0 END AS M2                " + System.getProperty("line.separator") ;
		query += dQuery3;
		query += "                                		-- 반복문 수행 끝                                                                               " + System.getProperty("line.separator") ;
		query += "                                FROM TB_MANPOWERMM) A                                                                               " + System.getProperty("line.separator") ;
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
		
		System.out.println(queryModel.getQuery());
		
		return progressMapper.SelectQuery(queryModel);
	}
	
	

	

	
	
	

}
