package com.certiware.backend.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certiware.backend.component.QueryComponent;
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
	@Autowired
	private QueryComponent queryComponent;
	
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
		selectDashboardResModel.setProjectStatisticsModel(												// 저장
				mainMapper.selectProjectStatistics(														// DB 조회
						queryComponent.makeSelectProjectStatisticsQuery(selectDashboardReqModel)));		// 쿼리생성
		
		// 프로젝트 인원 통계 정보 조회
		selectDashboardResModel.setManpowerStatisticsModel(												// 저장
				mainMapper.selectManpowerStatistics(													// DB 조회
						queryComponent.makeSelectManpowerStatisticsQuery(selectDashboardReqModel)));	// 쿼리생성
		
		// 프로젝트 매출 통계정보 조회
		selectDashboardResModel.setManpowerMMStatisticsModel(											// 저장
				mainMapper.selectManpowerMMStatistics(													// DB 조회
						queryComponent.makeSelectManpowerMMStatisticsQuery(selectDashboardReqModel)));	// 쿼리생성
		
		return selectDashboardResModel;		
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
		
		// 반복
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
