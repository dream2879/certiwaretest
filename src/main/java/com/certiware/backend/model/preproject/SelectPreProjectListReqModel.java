package com.certiware.backend.model.preproject;

import java.util.Date;

public class SelectPreProjectListReqModel {
	
	private String deptCode;	// 부서코드
	private Date startDate;		// 시작일
	private Date endDate;		// 종료일
	
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	

}
