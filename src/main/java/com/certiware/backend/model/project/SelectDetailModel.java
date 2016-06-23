package com.certiware.backend.model.project;

import java.util.List;

import com.certiware.backend.model.common.OutsourcingModel;
import com.certiware.backend.model.common.ProjectModel;

public class SelectDetailModel {
	
	private ProjectModel projectModel;
	private List<OutsourcingModel> outsourcingModels;

	public ProjectModel getProjectModel() {
		return projectModel;
	}

	public void setProjectModel(ProjectModel projectModel) {
		this.projectModel = projectModel;
	}

	public List<OutsourcingModel> getOutsourcingModels() {
		return outsourcingModels;
	}

	public void setOutsourcingModels(List<OutsourcingModel> outsourcingModels) {
		this.outsourcingModels = outsourcingModels;
	}



}
