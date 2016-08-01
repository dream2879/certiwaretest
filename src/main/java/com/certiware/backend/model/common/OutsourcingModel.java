package com.certiware.backend.model.common;

import java.sql.Date;

public class OutsourcingModel {
	
	private int projectId;				// 프로젝트 아이디
	private int partnerId;				// 거래처 아이디
	private String outsourcingCode;		// 외주구분코드
	private long outsourcingAmount;		// 외주계약단가
	private String ratingCode;			// 등급코드
	private String product;				// 상품/업무 내용
	private String locale;				// 장소
	private String contractWarranty;	// 계약이행보증
	private String delayWarranty;		// 지체상금
	private String defectWarranty;		// 하자이행보증
	private String paymentsTerm;		// 대금지급조건
	private Date startDate;				// 시작일
	private Date endDate;				// 종료일
	private String remarks;				// 종료일
	
	
	
	public String getContractWarranty() {
		return contractWarranty;
	}
	public void setContractWarranty(String contractWarranty) {
		this.contractWarranty = contractWarranty;
	}
	public String getDelayWarranty() {
		return delayWarranty;
	}
	public void setDelayWarranty(String delayWarranty) {
		this.delayWarranty = delayWarranty;
	}
	public String getDefectWarranty() {
		return defectWarranty;
	}
	public void setDefectWarranty(String defectWarranty) {
		this.defectWarranty = defectWarranty;
	}
	public String getPaymentsTerm() {
		return paymentsTerm;
	}
	public void setPaymentsTerm(String paymentsTerm) {
		this.paymentsTerm = paymentsTerm;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
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
