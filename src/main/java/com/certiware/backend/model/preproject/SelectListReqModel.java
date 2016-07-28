package com.certiware.backend.model.preproject;

import java.util.Date;

public class SelectListReqModel {
	
	private String projectName;		// 프로젝트이름
	private String partnerName; 	// 거래처이름 
	private String deptCode;		// 부서코드
	private Date startDate;			// 시작일(clinet에서 기준년도를 받는다)
	private Date endDate;			// 종료일
	
	public String getProjectName() {		
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
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
