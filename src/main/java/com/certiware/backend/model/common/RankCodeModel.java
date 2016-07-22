package com.certiware.backend.model.common;

public class RankCodeModel {
	
	private String rankCode;	// 등급코드
	private String rankName;	// 등급이름
	private int priority;		// 우선순위(정렬)
	
	public String getRankCode() {
		return rankCode;
	}
	public void setRankCode(String rankCode) {
		this.rankCode = rankCode;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
	
	

}
