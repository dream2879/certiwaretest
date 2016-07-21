package com.certiware.backend.model.common;

public class UnitPriceModel {
	
	private String year;		// 기준년도
	private String ratingCode;	// 등급코드
	private long unitPrice;		// 단가
	private long priority;		// 우선순위(정렬용)
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getRatingCode() {
		return ratingCode;
	}
	public void setRatingCode(String ratingCode) {
		this.ratingCode = ratingCode;
	}
	public long getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(long unitPrice) {
		this.unitPrice = unitPrice;
	}
	public long getPriority() {
		return priority;
	}
	public void setPriority(long priority) {
		this.priority = priority;
	}
	
	

}
