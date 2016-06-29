package com.certiware.backend.model.admin;

import java.util.List;

import com.certiware.backend.model.common.DeptCodeModel;

public class ModifyDeptCodeModel {
	private List<DeptCodeModel> mergeDeptCodeModels;
	private List<DeptCodeModel> deleteDeptCodeModels;
	
	public List<DeptCodeModel> getMergeDeptCodeModels() {
		return mergeDeptCodeModels;
	}
	public void setMergeDeptCodeModels(List<DeptCodeModel> mergeDeptCodeModels) {
		this.mergeDeptCodeModels = mergeDeptCodeModels;
	}
	public List<DeptCodeModel> getDeleteDeptCodeModels() {
		return deleteDeptCodeModels;
	}
	public void setDeleteDeptCodeModels(List<DeptCodeModel> deleteDeptCodeModels) {
		this.deleteDeptCodeModels = deleteDeptCodeModels;
	}

}
