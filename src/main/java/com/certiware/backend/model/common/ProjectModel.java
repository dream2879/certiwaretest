package com.certiware.backend.model.common;

import java.sql.Date;

public class ProjectModel {
	
	private int projectId;
	private String projectName;
	private String deptCode;
	private int partnerId;
	private Date startDate;
	private Date endDate;
	private int contractAmount;
	private int supplyAmount;
	private int vtaAmount;
	private int outsourcingAmount;
	private int netAmount;
	private String remarks;
	
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
	public int getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(int contractAmount) {
		this.contractAmount = contractAmount;
	}
	

	public int getSupplyAmount() {
		return supplyAmount;
	}
	public void setSupplyAmount(int supplyAmount) {
		this.supplyAmount = supplyAmount;
	}
	public int getVtaAmount() {
		return vtaAmount;
	}
	public void setVtaAmount(int vtaAmount) {
		this.vtaAmount = vtaAmount;
	}
	public int getOutsourcingAmount() {
		return outsourcingAmount;
	}
	public void setOutsourcingAmount(int outsourcingAmount) {
		this.outsourcingAmount = outsourcingAmount;
	}
	public int getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(int netAmount) {
		this.netAmount = netAmount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}	

}
