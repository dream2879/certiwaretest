package com.certiware.backend.config;

import java.util.List;

import com.certiware.backend.model.main.SelectMenuModel;
import com.certiware.backend.service.MainService;

/**
 * URL에 호출에 따른 권한 체크를 해주기 위한 필터
 * @author K
 *
 */
public class RoleFilter {
	
	private List<SelectMenuModel> selectMenuModels = null;
	/*
	public RoleFilter(MainService mainService) throws Exception{
		this.setSelectMenuModels(mainService);
	}
	*/
	
	/**
	 * 권한리스트를 조회한다.
	 * 2016.06.30 기준 가장 높은 권한 코드는 "4"이다.
	 * @param mainService
	 * @throws Exception
	 */
	public void setSelectMenuModels(MainService mainService) throws Exception{
		this.selectMenuModels = mainService.selectMenuList("4");
	}
	
	/**
	 * 권한코드와 호출URL을 받아서 권한을 체크해준다.
	 * @param roleCode
	 * @param requestURL
	 * @return
	 */
	public boolean checkRole(String roleCode, String requestURL) {		
	
		if(selectMenuModels != null) {
			for (SelectMenuModel selectMenuModel : selectMenuModels) {
				
				if(selectMenuModel.getRequestURL().equals(requestURL) && Integer.parseInt(selectMenuModel.getRoleCode()) <= Integer.parseInt(roleCode)){
					return true;				
				}				
			}			
		}
		
		return false;
	}

}
