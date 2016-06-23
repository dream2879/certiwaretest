package com.certiware.backend.model.project;

import java.sql.Date;

public class SelectListModel {
	
	private int projectId;
	private String projectName;
	private int partnerId;
	private String partnerName;
	private int contractAmount;
	private int outsourcingAmount;
	private int netAmountl;
	private Date startDate;
	private Date endDate;
	
	
	public int getOutsourcingAmount() {
		return outsourcingAmount;
	}
	public void setOutsourcingAmount(int outsourcingAmount) {
		this.outsourcingAmount = outsourcingAmount;
	}	
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
	public int getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(int contractAmount) {
		this.contractAmount = contractAmount;
	}
	public int getNetAmountl() {
		return netAmountl;
	}
	public void setNetAmountl(int netAmountl) {
		this.netAmountl = netAmountl;
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
