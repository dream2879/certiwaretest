package com.certiware.backend.model.progress;

import java.util.Date;

public class ManpowerNameModel {
	
	private String projectId;		// 프로젝트아이디	
	private String manpowerName;	// 투입인력이름
	private int partnerId;			// 파트너아이디
	private String partnerGubun;	// 파트너 구분을 위해사용 (개인사업자/프리랜러의 경우 'A'로 담긴다)
	private String ratingCode;		// 등급코드
	private long sellingAmount;		// 매출단가
	private long outsourcingAmount;	// 외주단가
	private Date startDate;			// 시작일
	private Date endDate;			// 종료일
	private String remarks;			// 비고
	private String job;
	
	
	
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
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
	public String getPartnerGubun() {
		return partnerGubun;
	}
	public void setPartnerGubun(String partnerGubun) {
		this.partnerGubun = partnerGubun;
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
	
	
	
	
	

}
