package com.certiware.backend.model.preproject;

import java.util.Date;

public class SelectManpowerMMModel {
	
	private String manpowerName;	// 투입인원명
	private String description;		// 등급
	private Date startDate;			// 시작일
	private Date endDate;			// 종료일
	private double mm;				// MM
	private long outsourcingAmount;	// 외주단가
	private long tot;				// 총액
	public String getManpowerName() {
		return manpowerName;
	}
	public void setManpowerName(String manpowerName) {
		this.manpowerName = manpowerName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public double getMm() {
		return mm;
	}
	public void setMm(double mm) {
		this.mm = mm;
	}
	public long getOutsourcingAmount() {
		return outsourcingAmount;
	}
	public void setOutsourcingAmount(long outsourcingAmount) {
		this.outsourcingAmount = outsourcingAmount;
	}
	public long getTot() {
		return tot;
	}
	public void setTot(long tot) {
		this.tot = tot;
	}
	
	

}
