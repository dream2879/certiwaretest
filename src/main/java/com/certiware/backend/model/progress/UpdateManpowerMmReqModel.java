package com.certiware.backend.model.progress;

import java.util.Date;

public class UpdateManpowerMmReqModel {
	
	private int projectId;			// 프로젝트아이디
	private String manpowerName;	// 투입인력이름
	private Date month;				// 기준월	
	private double beforeMM;		// 변경 전 M/M
	private double afterMM;			// 변경 후M/M
	private String userId;			// 사용자아이디
	private String userName;		// 사용자명
	
	
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
		this.manpowerName = manpowerName;
	}
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}
	public double getBeforeMM() {
		return beforeMM;
	}
	public void setBeforeMM(double beforeMM) {
		this.beforeMM = beforeMM;
	}
	public double getAfterMM() {
		return afterMM;
	}
	public void setAfterMM(double afterMM) {
		this.afterMM = afterMM;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
