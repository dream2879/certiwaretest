package com.certiware.backend.model.common;

public class RatingCodeModel {
	
	private String ratingCode;	// 등급코드
	private String description;	// 등급설명
	private int priority;		// 정렬(우선순위)
	
	public String getRatingCode() {
		return ratingCode;
	}
	public void setRatingCode(String ratingCode) {
		this.ratingCode = ratingCode;
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
