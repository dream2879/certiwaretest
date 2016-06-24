package com.certiware.backend.model.common;

import java.sql.Date;

public class ManpowerMmModel {
	
	private int projectId;
	private String manpowerName;
	private Date month;
	private float mm;
	
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
	public float getMm() {
		return mm;
	}
	public void setMm(float mm) {
		this.mm = mm;
	}
	
	

}
