package com.certiware.backend.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.certiware.backend.model.progress.SelectPartnerNameList;
import com.certiware.backend.model.progress.SelectProgressListReqModel;
import com.certiware.backend.model.progress.SelectProgressListResModel;
import com.certiware.backend.model.progress.UpdateManpowerModel;

@Service
public class ProgressService {
	
	@Autowired
	ProgressMapper progressMapper;
	@Autowired
	CommonService commonService;
	
//	public List<ManpowerMmModel> mergeManpowerMM(ManpowerModel manpowerModel) throws Exception {
	public void mergeManpowerMM(ManpowerModel manpowerModel) throws Exception {
		
		List<ManpowerMmModel> manpowerMmModels = new ArrayList<ManpowerMmModel>();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		
		int projectId = manpowerModel.getProjectId();
		String manpowerName = manpowerModel.getManpowerName();
		Date startDate =  manpowerModel.getStartDate();
		Date endDate = manpowerModel.getEndDate();		
		
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal.setTime(startDate);
		cal2.setTime(endDate);		
		
		// 시작점 확인
		boolean flag = true;
		double mm=0;
		while(!(-1 == endDate.compareTo(df.parse(df.format((cal.getTime())))))){
			
			ManpowerMmModel temp = new ManpowerMmModel();
			
			// MM을 계산하기전 기본 세팅
			temp.setProjectId(projectId);
			temp.setManpowerName(manpowerName);
			temp.setMonth(cal.getTime()); // 년,월만 잘 들어가지면 됨 		
		
			
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
			
			// cal에 월을 더 해준다. 
			cal.add(Calendar.MONTH, 1);
			
			// 반복문 마지막 loop			
			if((-1 >= endDate.compareTo(df.parse(df.format((cal.getTime())))))){				
				
			// System.out.println("계산 : " + (double) cal2.get(cal2.DAY_OF_MONTH) + " / " + (double) cal2.getActualMaximum(cal2.DAY_OF_MONTH));
				
				// MM 구하기 근무일수/해당월의일수
				mm = (double) cal2.get(cal2.DAY_OF_MONTH) / (double) cal2.getActualMaximum(cal2.DAY_OF_MONTH);				
			}
			
			// 소수점 4자리까지 구하기(버림)
			mm = (int) (mm * 10000) / 10000.0;	
			// temp 객체에 mm추가
			temp.setMm(mm);
			// temp 객체 add
			manpowerMmModels.add(temp);			
			
		};
		
		this.modifyManpowerMm(manpowerMmModels);
		
//		return manpowerMmModels;
			
	}
	
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
	 * TB_MANPOWER 테이블 INSERT
	 * @param manpowerModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean insertManpower(ManpowerModel manpowerModel) throws Exception{	
	
			// TB_MANPWER 테이블에 정보를 입력한다.
			progressMapper.inserteManpower(manpowerModel);
			
			// 투입기간에 따라 MM을 자동계산하여 보낸다.
			this.mergeManpowerMM(manpowerModel);
		
		return true;
	}
	
	/**
	 * TB_MANPOWER 테이블 UPDATE
	 * @param modifyManpowerModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean updateManpower(UpdateManpowerModel updateManpowerModel) throws Exception{
						
		// TB_MANPWER 테이블에 정보를 변경한다.
		progressMapper.updateManpower(updateManpowerModel);
		
		
		// startDate와 endDate가 변경됬다고 보고 TB_MANPWERMM의 정보도 수정해준다.
		
		ManpowerModel manpowerModel = new ManpowerModel();
		manpowerModel.setProjectId(updateManpowerModel.getProjectId());
		manpowerModel.setManpowerName(updateManpowerModel.getManpowerName());
		manpowerModel.setStartDate(updateManpowerModel.getStartDate());
		manpowerModel.setEndDate(updateManpowerModel.getEndDate());
		
		// 변경된 기간에 포함되지 않는 기간의 MM정보 삭제
		this.deleteManpowerMmByPeriod(manpowerModel);
		
		// 투입기간 변경에 따라 MM을 자동계산하여 보낸다.
		this.mergeManpowerMM(manpowerModel);
		
		
		return true;
	}
	
	/**
	 * Manpower 정보를 삭제한다.
	 * @param manpowerModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean deleteManpower(ManpowerModel manpowerModel) throws Exception{
		
		// TB_MANOWER 정보 삭제
		progressMapper.deleteManpower(manpowerModel);
		
		// TB_MANPWERMM 정보도 삭제
		this.deleteManpowerMmByPK(manpowerModel);
		
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
	public boolean modifyManpowerMm(List<ManpowerMmModel> manpowerMmModels) throws Exception{
		
		// merge
		for (ManpowerMmModel manpowerMmModel : manpowerMmModels) {
			progressMapper.mergeManpowerMm(manpowerMmModel);
		}
		
		return true;
	}
	
	/**
	 * TB_MANPOWERMM 테이블 사용자 정보 삭제(기간에 해당하는 것만)
	 * @param manpowerMmModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean deleteManpowerMmByPeriod(ManpowerModel manpowerModel) throws Exception{
		
		progressMapper.deleteManpowerMmByPeriod(manpowerModel);
		
		return true;
	}
	
	/**
	 * TB_MANPWERMM 테이블 사용자 정보 삭제
	 * @param manpowerMmModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean deleteManpowerMmByPK(ManpowerModel manpowerModel) throws Exception{
		
		progressMapper.deleteManpowerMmByPK(manpowerModel);
		
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
		
		System.out.println(queryModel.getQuery());
		
		return progressMapper.SelectQuery(queryModel);
	}
	
	
	/**
	 * 프로젝트 아이디를가지고 외주회사명을 가져온다.
	 * @param partnerId
	 * @return
	 * @throws Exception
	 */
	public List<SelectPartnerNameList> SelectPartnerNameList(int partnerId) throws Exception{
		return progressMapper.selectOutsourcingByPartnerId(partnerId);
	}
	

	

	
	
	

}
