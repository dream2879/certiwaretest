package com.certiware.backend.model.admin;

import java.util.List;

import com.certiware.backend.model.common.DeptCodeModel;
import com.certiware.backend.model.common.RankCodeModel;
import com.certiware.backend.model.common.RoleCodeModel;

public class AdminCodeModel {
	
	private List<DeptCodeModel> deptCodeModels;
	private List<RankCodeModel> rankCodeModels;
	private List<RoleCodeModel> roleCodeModels;
	
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

}
