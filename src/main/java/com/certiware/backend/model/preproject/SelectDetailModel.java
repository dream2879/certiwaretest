package com.certiware.backend.model.preproject;

import java.util.List;

import com.certiware.backend.model.common.OutsourcingModel;
import com.certiware.backend.model.common.ProjectModel;

public class SelectDetailModel {
	
	private ProjectModel projectModel;
	private List<SelectPreOutsourcingResModel> SelectOutsourcingModel;
	public ProjectModel getProjectModel() {
		return projectModel;
	}
	public void setProjectModel(ProjectModel projectModel) {
		this.projectModel = projectModel;
	}
	public List<SelectPreOutsourcingResModel> getSelectOutsourcingModel() {
		return SelectOutsourcingModel;
	}
	public void setSelectOutsourcingModel(List<SelectPreOutsourcingResModel> selectOutsourcingModel) {
		SelectOutsourcingModel = selectOutsourcingModel;
	}

	


}
