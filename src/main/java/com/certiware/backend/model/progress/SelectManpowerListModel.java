package com.certiware.backend.model.progress;

import java.util.List;

public class SelectManpowerListModel {
	
	private List<ProjectPartnerModel> projectPartnerModels;
	private List<ManpowerNameModel> manpowerNameModels;
	
	
	public List<ProjectPartnerModel> getProjectPartnerModels() {
		return projectPartnerModels;
	}
	public void setProjectPartnerModels(List<ProjectPartnerModel> projectPartnerModels) {
		this.projectPartnerModels = projectPartnerModels;
	}
	public List<ManpowerNameModel> getManpowerNameModels() {
		return manpowerNameModels;
	}
	public void setManpowerNameModels(List<ManpowerNameModel> manpowerNameModels) {
		this.manpowerNameModels = manpowerNameModels;
	}
	
	

}
