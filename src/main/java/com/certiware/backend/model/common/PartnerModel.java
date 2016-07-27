package com.certiware.backend.model.common;

import java.sql.Date;

public class PartnerModel {
	
	private int partnerId;			// 거래처 아이디
	private String partnerName;		// 거래처이름
	private String partnerCode;		// 거래처 구분코드
	private long businessNumber;		// 회사번호
	private String businessCode;	// 회사 구분코드
	private String ceoName;			// 대표자 이름
	private String address;			// 거래처 회사 주소
	private Date createDate;		// 등록일
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
	public long getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(long businessNumber) {
		this.businessNumber = businessNumber;
	}
	public String getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

}
