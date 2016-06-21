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
	private int supplyAumount;
	private int vtaAmount;
	private int outsourcing_aomut;
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
	public int getSupplyAumount() {
		return supplyAumount;
	}
	public void setSupplyAumount(int supplyAumount) {
		this.supplyAumount = supplyAumount;
	}
	public int getVtaAmount() {
		return vtaAmount;
	}
	public void setVtaAmount(int vtaAmount) {
		this.vtaAmount = vtaAmount;
	}
	public int getOutsourcing_aomut() {
		return outsourcing_aomut;
	}
	public void setOutsourcing_aomut(int outsourcing_aomut) {
		this.outsourcing_aomut = outsourcing_aomut;
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
