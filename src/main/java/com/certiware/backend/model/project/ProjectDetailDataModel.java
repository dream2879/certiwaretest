package com.certiware.backend.model.project;

import java.util.List;

import com.certiware.backend.model.common.OutsourcingModel;
import com.certiware.backend.model.common.ProjectModel;

public class ProjectDetailDataModel {
	
	private ProjectModel projectModel;
	private List<OutsourcingModel> outsourcings;
	
	public ProjectModel getProjectModel() {
		return projectModel;
	}
	public void setProjectModel(ProjectModel projectModel) {
		this.projectModel = projectModel;
	}
	public List<OutsourcingModel> getOutsourcings() {
		return outsourcings;
	}
	public void setOutsourcings(List<OutsourcingModel> outsourcings) {
		this.outsourcings = outsourcings;
	}	
}
