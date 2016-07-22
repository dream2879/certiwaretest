package com.certiware.backend.model.common;

public class OutsourcingCodeModel {
	
	private String outsourcingCode;	// 외주구분코드
	private String description;		// 코드설명
	private int priority;			// 우선순위(정렬)
	
	public String getOutsourcingCode() {
		return outsourcingCode;
	}
	public void setOutsourcingCode(String outsourcingCode) {
		this.outsourcingCode = outsourcingCode;
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
