package com.certiware.backend.model.main;

import java.util.Date;

public class SelectDashboardReqModel {
	
	private String deptCode;	// 부서코드
	private Date year;		// 기준년도
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public Date getYear() {
		return year;
	}
	public void setYear(Date year) {
		this.year = year;
	}
	
	
	
	
}
