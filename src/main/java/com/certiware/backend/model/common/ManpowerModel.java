package com.certiware.backend.model.common;

import java.util.Date;

public class ManpowerModel {
	
	private int projectId;			// 프로젝트 아이디
	private String manpowerName;	// 투입인력이름
	private int partnerId;			// 소속 아이디
	private String ratingCode;		// 등급
	private long sellingAmount;		// 매출단가
	private long outsourcingAmount;	// 외주단가
	private Date startDate;			// 시작일
	private Date endDate;			// 종료일
	private String remarks;	
	// 비고
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
		return ratingCode;
	}
	public void setRatingCode(String ratingCode) {
		this.ratingCode = ratingCode;
	}
	public long getSellingAmount() {
		return sellingAmount;
	}
	public void setSellingAmount(long sellingAmount) {
		this.sellingAmount = sellingAmount;
	}
	public long getOutsourcingAmount() {
		return outsourcingAmount;
	}
	public void setOutsourcingAmount(long outsourcingAmount) {
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
