package com.certiware.backend.model.project;

import java.sql.Date;

public class SelectOutsourcingResModel {
	
	private int projectId;
	private int partnerId;
	private String partnerName;
	private String partnerCode;
	private String outsourcingCode;
	private long outsourcingAmount;
	private String ratingCode;
	private String product;			// 상품/업무 내용
	private String locale;			// 납품/이행 장소
	private String contractWarranty;	// 계약이행보증
	private String delayWarranty;		// 지체상금
	private String defectWarranty;		// 하자이행보증
	private String paymentsTerm;		// 대금지급조건
	private Date startDate;
	private String endDate;
	private String remarks;
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
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
