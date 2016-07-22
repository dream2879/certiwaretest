package com.certiware.backend.model.common;

import java.util.Date;

public class ManpowerMmModel {
	
	private int projectId;			// 프로젝트아이디
	private String manpowerName;	// 투입인력이름
	private Date month;				// 월정보	
	private double mm;				// M/M
	
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
	public double getMm() {
		return mm;
	}
	public void setMm(double mm) {
		this.mm = mm;
	}
	
	

}
