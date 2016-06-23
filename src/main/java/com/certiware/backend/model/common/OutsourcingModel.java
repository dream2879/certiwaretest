package com.certiware.backend.model.common;

import java.sql.Date;

public class OutsourcingModel {
	
	private int projectId;
	private int partner_Id;
	private String outsourcingCode;
	private int outsourcing_amount;
	private String ratingCode;
	private String product;
	private Date startDate;
	private String endDate;
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getPartner_Id() {
		return partner_Id;
	}
	public void setPartner_Id(int partner_Id) {
		this.partner_Id = partner_Id;
	}
	public String getOutsourcingCode() {
		return outsourcingCode;
	}
	public void setOutsourcingCode(String outsourcingCode) {
		this.outsourcingCode = outsourcingCode;
	}
	public int getOutsourcing_amount() {
		return outsourcing_amount;
	}
	public void setOutsourcing_amount(int outsourcing_amount) {
		this.outsourcing_amount = outsourcing_amount;
	}
	public String getRatingCode() {
		return ratingCode;
	}
	public void setRatingCode(String ratingCode) {
		this.ratingCode = ratingCode;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
		
}
