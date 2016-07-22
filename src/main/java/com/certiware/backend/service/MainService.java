package com.certiware.backend.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certiware.backend.mapper.CommonMapper;
import com.certiware.backend.mapper.MainMapper;
import com.certiware.backend.model.common.PartnerModel;
import com.certiware.backend.model.common.UnitPriceModel;
import com.certiware.backend.model.common.UserModel;
import com.certiware.backend.model.main.ManpowerMMStatisticsModel;
import com.certiware.backend.model.main.ManpowerStatisticsModel;
import com.certiware.backend.model.main.ProjectStatisticsModel;
import com.certiware.backend.model.main.SelectDashboardReqModel;
import com.certiware.backend.model.main.SelectDashboardResModel;
import com.certiware.backend.model.main.SelectLoginModel;
import com.certiware.backend.model.main.SelectMenuModel;

@Service
public class MainService {
	
	@Autowired
	private MainMapper mainMapper;
	@Autowired 
	private CommonMapper commonMapper;
	
	/**
	 * TB_USER 테이블조회
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserModel selectUserByPK(String userId) throws Exception {	
		return mainMapper.selectUserByPK(userId);	
	}
	
	/**
	 * LOGIN 후 필요한 데이터 조회
	 * 코드성 테이블, USER정보, 메뉴리스트 등
	 * @param selectLoginModel
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public SelectLoginModel selectLogin(SelectLoginModel selectLoginModel, String userId) throws Exception {
		
		// 코드데이터
		selectLoginModel.setBusinessCodeModels(commonMapper.SelectBusinessCode());
		selectLoginModel.setDeptCodeModels(commonMapper.SelectDeptCode());
		selectLoginModel.setOutsourcingCodeModels(commonMapper.SelectOutsourcingCode());
		selectLoginModel.setPartnerCodeModels(commonMapper.SelectPartnerCode());
		selectLoginModel.setRankCodeModels(commonMapper.SelectRankCode());
		selectLoginModel.setRatingCodeModels(commonMapper.SelectRatingCode());
		selectLoginModel.setRoleCodeModels(commonMapper.SelectRoleCode());
		
		// 단가정보를 가져올 때는 현재 년도를 기준으로 이전 3년치 데이터만 조회한다.
		Calendar calendar = Calendar.getInstance();
		selectLoginModel.setUnitPriceModels(mainMapper.selectUnitPrice(String.valueOf(calendar.get(calendar.YEAR))));
		
		// 유저정보
		selectLoginModel.setUserModel(mainMapper.selectUserByPK(userId));
		
		// 메뉴목록
		selectLoginModel.setSelectMenuModels(mainMapper.selectMenuByRoleCode(selectLoginModel.getUserModel().getRoleCode()));
		
		return selectLoginModel;		
	}
	
	/**
	 * 매출처를 조회한다.
	 * @param partnerName:매출처명
	 * @return
	 * @throws Exception
	 */
	public List<PartnerModel> selectCustomerPatner(PartnerModel partnerModel) throws Exception{
		return mainMapper.selectCustomerPatner(partnerModel);
	}
	
	/**
	 * 롤코드 저장을 위해 임시 생성
	 * @param roleCode
	 * @return
	 * @throws Exception
	 */
	public List<SelectMenuModel> selectMenuList(String roleCode) throws Exception  {		
			
		return mainMapper.selectMenuByRoleCode(roleCode);		
	}

	/**
	 * 메인 페이지를 구성할 통계정보를 조회하여 리턴한다.
	 * @param dashboardReqModel
	 * @param selectDashboardResModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public SelectDashboardResModel selectDashboard(SelectDashboardReqModel selectDashboardReqModel, SelectDashboardResModel selectDashboardResModel) throws Exception{
		
		// 프로젝트 갯수 통계 정보 조회
		selectDashboardResModel.setProjectStatisticsModel(this.selectProjectStatistics(selectDashboardReqModel));
		
		// 프로젝트 인원 통계 정보 조회
		selectDashboardResModel.setManpowerStatisticsModel(this.selectManpowerStatistics(selectDashboardReqModel));
		
		// 프로젝트 매출 통계정보 조회
		selectDashboardResModel.setManpowerMMStatisticsModel(this.selectManpowerMMStatistics(selectDashboardReqModel));
		
		return selectDashboardResModel;		
	}
	
	/**
	 * 프로젝트 갯수 정보를 조회하기 위한 쿼리 작성
	 * @param selectDashboardReqModel
	 * @return
	 * @throws Exception
	 */
	private ProjectStatisticsModel selectProjectStatistics(SelectDashboardReqModel selectDashboardReqModel) throws Exception{		
			
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
		
		return mainMapper.selectProjectStatistics(query);	
	}
	
	
	/**
	 * 프로젝트 투입인력 정보를 구한다.
	 * @param selectDashboardReqModel
	 * @return
	 * @throws Exception
	 */
	private ManpowerStatisticsModel selectManpowerStatistics(SelectDashboardReqModel selectDashboardReqModel) throws Exception{
		
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
		
		
		return mainMapper.selectManpowerStatistics(query);	
	}
	
	
	/**
	 * 월별 매출 현황을 구한다.
	 * @param selectDashboardReqModel
	 * @return
	 * @throws Exception
	 */
	private ManpowerMMStatisticsModel selectManpowerMMStatistics(SelectDashboardReqModel selectDashboardReqModel) throws Exception{
		
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
		
		return mainMapper.selectManpowerMMStatistics(query);	
	}
	
	
	/**
	 * 단가 테이블을 조회한다.
	 * @param param1
	 * @return
	 * @throws Exception
	 */
	public List<UnitPriceModel> selectUnitPrice(String param1) throws Exception{
		return mainMapper.selectUnitPrice(param1);
	}
	
	/**
	 * 단가 테이블을 Merge 한다
	 * @param unitPriceModels
	 * @throws Exception
	 */
	@Transactional
	public boolean mergeUnitPrice(List<UnitPriceModel> unitPriceModels) throws Exception{
		for (UnitPriceModel unitPriceModel : unitPriceModels) {
			mainMapper.mergeUnitPrice(unitPriceModel);			
		}
		
		return true;
	}
	
	/**
	 * 단가 테이블을 삭제한다.
	 * @param year
	 * @throws Exception
	 */
	public boolean deleteUnitPrice(String year) throws Exception{
		
		mainMapper.deleteUnitPrice(year);
		
		return true;
	}
	

	

}
