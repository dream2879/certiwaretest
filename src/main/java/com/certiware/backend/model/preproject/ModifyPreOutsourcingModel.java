package com.certiware.backend.model.preproject;

import java.sql.Date;

public class ModifyPreOutsourcingModel {	
	
	private int projectId;			// 프로젝트 아이디
	private int partnerId;			// 거래처 아이디
	private String partnerName;		// 거래처 식별코드
	private String partnerCode;		// 거래처 식별코드
	private String outsourcingCode;	// 외주구분코드
	private long sellingAmount;		// 매출단가
	private long outsourcingAmount;	// 외주계약단가
	private String ratingCode;		// 등급코드
	private String product;			// 상품/업무 내용
	private String locale;			// 납품/이행 장소
	private Date startDate;			// 시작일
	private Date endDate;			// 종료일
	private String remarks;			// 비고	
	
	
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public long getSellingAmount() {
		return sellingAmount;
	}
	public void setSellingAmount(long sellingAmount) {
		this.sellingAmount = sellingAmount;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
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
	public String getPartnerCode() {
		return partnerCode;
	}
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}
	public String getOutsourcingCode() {
		return outsourcingCode;
	}
	public void setOutsourcingCode(String outsourcingCode) {
		this.outsourcingCode = outsourcingCode;
	}
	public long getOutsourcingAmount() {
		return outsourcingAmount;
	}
	public void setOutsourcingAmount(long outsourcingAmount) {
		this.outsourcingAmount = outsourcingAmount;
	}
	public String getRatingCode() {
		return ratingCode;
	}
	public void setRatingCode(String ratingCode) {
		this.ratingCode = ratingCode;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
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
