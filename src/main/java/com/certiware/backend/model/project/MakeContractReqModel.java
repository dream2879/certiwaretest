package com.certiware.backend.model.project;

public class MakeContractReqModel {
	
	private int projectId;			// 프로젝트아이디
	private int partnerId;			// 거래처아이디
	private String outsourcingCode;	// 외주구분코드
	private String partnerCode;		// 거래처구분코드(법인,프리..)
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}
	public String getOutsourcingCode() {
		return outsourcingCode;
	}
	public void setOutsourcingCode(String outsourcingCode) {
		this.outsourcingCode = outsourcingCode;
	}
	public String getPartnerCode() {
		return partnerCode;
	}
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}
	
	

}
