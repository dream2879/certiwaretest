package com.certiware.backend.model.common;

public class PartnerCodeModel {
	
	private String partnerCode;	// 거래처종류코드
	private String description;	// 거래처코드설명
	private int priority;		// 우선순위정렬
	
	public String getPartnerCode() {
		return partnerCode;
	}
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	

	
	

}
