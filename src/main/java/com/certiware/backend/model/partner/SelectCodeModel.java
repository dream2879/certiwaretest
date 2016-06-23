package com.certiware.backend.model.partner;

import java.util.List;

import com.certiware.backend.model.common.BusinessCodeModel;

public class SelectCodeModel {
	
	private List<com.certiware.backend.model.common.PartnerCodeModel> partnerCodeModels;
	private List<BusinessCodeModel> businessCodeModels;
	

	public List<com.certiware.backend.model.common.PartnerCodeModel> getPartnerCodeModels() {
		return partnerCodeModels;
	}
	public void setPartnerCodeModels(List<com.certiware.backend.model.common.PartnerCodeModel> partnerCodeModels) {
		this.partnerCodeModels = partnerCodeModels;
	}
	public List<BusinessCodeModel> getBusinessCodeModels() {
		return businessCodeModels;
	}
	public void setBusinessCodeModels(List<BusinessCodeModel> businessCodeModels) {
		this.businessCodeModels = businessCodeModels;
	}
	
	

}
