package com.certiware.backend.model.progress;

public class SelectManpowerMMHistoryReqModel {
	
	private int projectId;			// 프로젝트아이디
	private String manpowerNmae;	// 투입인력 이름
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getManpowerNmae() {
		return manpowerNmae;
	}
	public void setManpowerNmae(String manpowerNmae) {
		this.manpowerNmae = manpowerNmae;
	}
}