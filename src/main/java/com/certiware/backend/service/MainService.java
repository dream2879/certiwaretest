package com.certiware.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certiware.backend.mapper.CommonMapper;
import com.certiware.backend.mapper.MainMapper;
import com.certiware.backend.model.common.PartnerModel;
import com.certiware.backend.model.common.UserModel;
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
	
	

}
