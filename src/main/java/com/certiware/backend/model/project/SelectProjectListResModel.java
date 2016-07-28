package com.certiware.backend.model.project;

public class SelectProjectListResModel {
	
	private int projectId;		// 프로젝트 아이디
	private String projectName;	// 프로젝트 이름
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
