package com.certiware.backend.model.main;

import java.util.List;

import com.certiware.backend.model.common.BusinessCodeModel;
import com.certiware.backend.model.common.DeptCodeModel;
import com.certiware.backend.model.common.OutsourcingCodeModel;
import com.certiware.backend.model.common.PartnerCodeModel;
import com.certiware.backend.model.common.RankCodeModel;
import com.certiware.backend.model.common.RatingCodeModel;
import com.certiware.backend.model.common.RoleCodeModel;
import com.certiware.backend.model.common.UserModel;

/**
 * Front-end에서 사용하는 모든 코드 데이터를 정리한다.
 * @author K
 *
 */
public class SelectLoginModel {
	
	// 코드
	private List<DeptCodeModel> deptCodeModels;
	private List<RankCodeModel> rankCodeModels;
	private List<RoleCodeModel> roleCodeModels;
	private List<PartnerCodeModel> partnerCodeModels;
	private List<BusinessCodeModel> businessCodeModels;
	private List<RatingCodeModel> ratingCodeModels;
	private List<OutsourcingCodeModel> outsourcingCodeModels;
	
	// 사용자정보
	private UserModel userModel;
	
	// 메뉴목록
	private List<SelectMenuModel> selectMenuModels;
	

	public List<DeptCodeModel> getDeptCodeModels() {
		return deptCodeModels;
	}
	public void setDeptCodeModels(List<DeptCodeModel> deptCodeModels) {
		this.deptCodeModels = deptCodeModels;
	}
	public List<RankCodeModel> getRankCodeModels() {
		return rankCodeModels;
	}
	public void setRankCodeModels(List<RankCodeModel> rankCodeModels) {
		this.rankCodeModels = rankCodeModels;
	}
	public List<RoleCodeModel> getRoleCodeModels() {
		return roleCodeModels;
	}
	public void setRoleCodeModels(List<RoleCodeModel> roleCodeModels) {
		this.roleCodeModels = roleCodeModels;
	}
	public List<PartnerCodeModel> getPartnerCodeModels() {
		return partnerCodeModels;
	}
	public void setPartnerCodeModels(List<PartnerCodeModel> partnerCodeModels) {
		this.partnerCodeModels = partnerCodeModels;
	}
	public List<BusinessCodeModel> getBusinessCodeModels() {
		return businessCodeModels;
	}
	public void setBusinessCodeModels(List<BusinessCodeModel> businessCodeModels) {
		this.businessCodeModels = businessCodeModels;
	}
	public List<RatingCodeModel> getRatingCodeModels() {
		return ratingCodeModels;
	}
	public void setRatingCodeModels(List<RatingCodeModel> ratingCodeModels) {
		this.ratingCodeModels = ratingCodeModels;
	}
	public List<OutsourcingCodeModel> getOutsourcingCodeModels() {
		return outsourcingCodeModels;
	}
	public void setOutsourcingCodeModels(List<OutsourcingCodeModel> outsourcingCodeModels) {
		this.outsourcingCodeModels = outsourcingCodeModels;
	}
	public UserModel getUserModel() {
		return userModel;
	}
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	public List<SelectMenuModel> getSelectMenuModels() {
		return selectMenuModels;
	}
	public void setSelectMenuModels(List<SelectMenuModel> selectMenuModels) {
		this.selectMenuModels = selectMenuModels;
	}
	
	
	
	
}
