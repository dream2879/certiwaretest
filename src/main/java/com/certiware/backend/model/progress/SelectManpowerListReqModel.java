package com.certiware.backend.model.progress;

public class SelectManpowerListReqModel {
	
	private int projectId; 			// 프로젝트아이디
	private String manpowerName; 	// 투입인력이름
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getManpowerName() {
		return manpowerName;
	}
	public void setManpowerName(String manpowerName) {
		manpowerName = manpowerName;
	}
	
	

}
