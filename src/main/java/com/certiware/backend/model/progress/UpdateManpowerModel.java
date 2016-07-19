package com.certiware.backend.model.progress;

import java.util.Date;

public class UpdateManpowerModel {
	private int pk1;
	private String pk2;
	private int projectId;
	private String manpowerName;
	private int partnerId;
	private String RatingCode;
	private int sellingAmount;
	private int outsourcingAmount;
	private Date startDate;
	private Date endDate;
	private String remarks;
	public int getPk1() {
		return pk1;
	}
	public void setPk1(int pk1) {
		this.pk1 = pk1;
	}
	public String getPk2() {
		return pk2;
	}
	public void setPk2(String pk2) {
		this.pk2 = pk2;
	}
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
	public int getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}
	public String getRatingCode() {
		return RatingCode;
	}
	public void setRatingCode(String ratingCode) {
		RatingCode = ratingCode;
	}
	public int getSellingAmount() {
		return sellingAmount;
	}
	public void setSellingAmount(int sellingAmount) {
		this.sellingAmount = sellingAmount;
	}
	public int getOutsourcingAmount() {
		return outsourcingAmount;
	}
	public void setOutsourcingAmount(int outsourcingAmount) {
		this.outsourcingAmount = outsourcingAmount;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
