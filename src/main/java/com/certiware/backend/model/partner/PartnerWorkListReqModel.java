package com.certiware.backend.model.partner;

import java.util.Date;

public class PartnerWorkListReqModel {
	
	private int partnerId;		// 파트너아이디
	private Date startDate;		// 시작일(시작일이 들어오지 않는다면 날짜 조건 검색 안함)
	private Date endDate;		// 종료일
	
	public int getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
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
	
	

}
