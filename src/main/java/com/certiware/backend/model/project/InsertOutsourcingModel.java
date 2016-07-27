package com.certiware.backend.model.project;

import java.sql.Date;

public class InsertOutsourcingModel {
	
	private int projectId;			// 프로젝트 아이디
	private String projectName;		// 프로젝트 이름
	private String deptCode;		// 부서코드
	private int partnerId;			// 거래처 아이디
	private String partnerCode;		// 거래처 식별코드
	private Date startDate;			// 시작일
	private Date endDate;			// 종료일
	private long contractAmount;	// 계약금액
	private long supplyAmount;		// 공급가액
	private long vtaAmount;			// 부가세액
	private long outsourcingAmount;	// 외주금액
	private long netAmount;			// 순매출
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
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public int getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}
	public String getPartnerCode() {
		return partnerCode;
	}
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
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
	public long getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(long contractAmount) {
		this.contractAmount = contractAmount;
	}
	public long getSupplyAmount() {
		return supplyAmount;
	}
	public void setSupplyAmount(long supplyAmount) {
		this.supplyAmount = supplyAmount;
	}
	public long getVtaAmount() {
		return vtaAmount;
	}
	public void setVtaAmount(long vtaAmount) {
		this.vtaAmount = vtaAmount;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
