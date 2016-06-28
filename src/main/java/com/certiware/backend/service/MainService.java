package com.certiware.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certiware.backend.mapper.CommonMapper;
import com.certiware.backend.mapper.MainMapper;
import com.certiware.backend.model.common.UserModel;
import com.certiware.backend.model.main.SelectLoginModel;

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
		
		selectLoginModel.setBusinessCodeModels(commonMapper.SelectBusinessCode());
		selectLoginModel.setDeptCodeModels(commonMapper.SelectDeptCode());
		selectLoginModel.setOutsourcingCodeModels(commonMapper.SelectOutsourcingCode());
		selectLoginModel.setPartnerCodeModels(commonMapper.SelectPartnerCode());
		selectLoginModel.setRankCodeModels(commonMapper.SelectRankCode());
		selectLoginModel.setRatingCodeModels(commonMapper.SelectRatingCode());
		selectLoginModel.setRoleCodeModels(commonMapper.SelectRoleCode());		
		selectLoginModel.setUserModel(mainMapper.selectUserByPK(userId));
		selectLoginModel.setSelectMenuModels(mainMapper.selectMenuByRoleCode(selectLoginModel.getUserModel().getRoleCode()));
		
		return selectLoginModel;		
	}
	
	

}
