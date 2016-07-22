package com.certiware.backend.model.common;

public class BusinessCodeModel {
	
	private String businessCode;	// 사업자코드
	private String description;		// 코드설명
	private int priority;			// 우선순위(정렬)
	
	public String getBusinessCode() {
		return businessCode;
	}
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
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
