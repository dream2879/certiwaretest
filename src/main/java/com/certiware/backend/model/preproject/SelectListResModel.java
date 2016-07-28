package com.certiware.backend.model.preproject;

import java.util.Date;

public class SelectListResModel {
	
	private int projectId; 			// 프로젝트아이디
	private String projectName;		// 프로젝트이름
	private int partnerId;			// 거래처아이디
	private String partnerName; 	// 거래처이름 
	private long contractAmount; 	// 계약단가
	private long outsourcingAmount; // 외주단가
	private long netAmount; 		// 순매출
	private Date startDate; 		// 시작일
	private Date endDate; 			// 종료일
	private String deptCode;		// 부서코드
	private String remarks;			// 비고
	
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
	public int getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public long getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(long contractAmount) {
		this.contractAmount = contractAmount;
	}
	public long getOutsourcingAmount() {
		return outsourcingAmount;
	}
	public void setOutsourcingAmount(long outsourcingAmount) {
		this.outsourcingAmount = outsourcingAmount;
	}
	public long getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(long netAmount) {
		this.netAmount = netAmount;
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
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
}
