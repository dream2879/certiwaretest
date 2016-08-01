package com.certiware.backend.model.project;

import java.util.Date;

public class SelectProjectPartnerModel {
	
	
	private String projectName;	// 프로젝트이름
	private Date startDate;		// 시작일
	private Date endDate;		// 종료일
	private String product;		// 업무/상품
	private String locale;		// 이행/납품장소
	private String contractWarranty;	// 계약이행보증
	private String delayWarranty;		// 지체상금
	private String defectWarranty;		// 하자이행보증
	private String paymentsTerm;		// 대금지급조건
	private String partnerName;	// 거래처명
	private long businessNumber;	// 사업자/주민 번호
	private String ceoName;		// 대표자명
	private String address;		// 거래처 주소
	private String remarks;		// 비고
	
	
	
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
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public long getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(long businessNumber) {
		this.businessNumber = businessNumber;
	}
	public String getCeoName() {
		return ceoName;
	}
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
