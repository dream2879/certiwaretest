package com.certiware.backend.model.project;

import java.util.List;

import com.certiware.backend.model.common.OutsourcingModel;
import com.certiware.backend.model.common.ProjectModel;

public class SelectDetailModel {
	
	private ProjectModel projectModel;
	private List<SelectOutsourcingResModel> SelectOutsourcingModel;
	public ProjectModel getProjectModel() {
		return projectModel;
	}
	public void setProjectModel(ProjectModel projectModel) {
		this.projectModel = projectModel;
	}
	public List<SelectOutsourcingResModel> getSelectOutsourcingModel() {
		return SelectOutsourcingModel;
	}
	public void setSelectOutsourcingModel(List<SelectOutsourcingResModel> selectOutsourcingModel) {
		SelectOutsourcingModel = selectOutsourcingModel;
	}

	


}
