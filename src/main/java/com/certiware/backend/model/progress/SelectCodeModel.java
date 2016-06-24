package com.certiware.backend.model.progress;

import java.util.List;

import com.certiware.backend.model.common.DeptCodeModel;
import com.certiware.backend.model.common.RatingCodeModel;

public class SelectCodeModel {
	
	private List<ProjectListModel> projectListModels;
	private List<DeptCodeModel> deptCodeModels;
	private List<RatingCodeModel> ratingCodeModels;
	private List<ProjectPartnerModel> projectPartnerModels;
	private List<ManpowerNameListModel> manpowerNameListModels;
	
	public List<ProjectListModel> getProjectListModels() {
		return projectListModels;
	}
	public void setProjectListModels(List<ProjectListModel> projectListModels) {
		this.projectListModels = projectListModels;
	}
	public List<DeptCodeModel> getDeptCodeModels() {
		return deptCodeModels;
	}
	public void setDeptCodeModels(List<DeptCodeModel> deptCodeModels) {
		this.deptCodeModels = deptCodeModels;
	}
	public List<RatingCodeModel> getRatingCodeModels() {
		return ratingCodeModels;
	}
	public void setRatingCodeModels(List<RatingCodeModel> ratingCodeModels) {
		this.ratingCodeModels = ratingCodeModels;
	}
	public List<ProjectPartnerModel> getProjectPartnerModels() {
		return projectPartnerModels;
	}
	public void setProjectPartnerModels(List<ProjectPartnerModel> projectPartnerModels) {
		this.projectPartnerModels = projectPartnerModels;
	}
	public List<ManpowerNameListModel> getManpowerNameListModels() {
		return manpowerNameListModels;
	}
	public void setManpowerNameListModels(List<ManpowerNameListModel> manpowerNameListModels) {
		this.manpowerNameListModels = manpowerNameListModels;
	}
	

	

}
