package com.certiware.backend.model.main;

public class SelectDashboardResModel {
	
	private ProjectStatisticsModel projectStatisticsModel;			// 월별 프로젝트 현황
	private ManpowerStatisticsModel manpowerStatisticsModel;		// 월별 투입인원 현황
	private ManpowerMMStatisticsModel manpowerMMStatisticsModel;	// 월별 매출현황
	
	public ProjectStatisticsModel getProjectStatisticsModel() {
		return projectStatisticsModel;
	}
	public void setProjectStatisticsModel(ProjectStatisticsModel projectStatisticsModel) {
		this.projectStatisticsModel = projectStatisticsModel;
	}
	public ManpowerStatisticsModel getManpowerStatisticsModel() {
		return manpowerStatisticsModel;
	}
	public void setManpowerStatisticsModel(ManpowerStatisticsModel manpowerStatisticsModel) {
		this.manpowerStatisticsModel = manpowerStatisticsModel;
	}
	public ManpowerMMStatisticsModel getManpowerMMStatisticsModel() {
		return manpowerMMStatisticsModel;
	}
	public void setManpowerMMStatisticsModel(ManpowerMMStatisticsModel manpowerMMStatisticsModel) {
		this.manpowerMMStatisticsModel = manpowerMMStatisticsModel;
	}
	
	
	
	

}
