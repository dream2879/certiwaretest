package com.certiware.backend.model.preproject;

public class MovePreProjectReqModel {
	
	private int projectId;			// TB_PREPROJECT 테이블에 있는 프로젝트 아이디
	private int generatedProjectId;	// TB_PROJECT 테이블에 INSERT 했을 경우 생성되는 프로젝트아이디
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getGeneratedProjectId() {
		return generatedProjectId;
	}
	public void setGeneratedProjectId(int generatedProjectId) {
		this.generatedProjectId = generatedProjectId;
	}
	
	

}
