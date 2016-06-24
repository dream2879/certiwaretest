package com.certiware.backend.model.project;

import java.util.List;

import com.certiware.backend.model.common.BusinessCodeModel;
import com.certiware.backend.model.common.DeptCodeModel;
import com.certiware.backend.model.common.PartnerCodeModel;
import com.certiware.backend.model.common.PartnerModel;
import com.certiware.backend.model.common.RatingCodeModel;

public class SelectCodeModel {
	
	private List<DeptCodeModel> deptCodeModels; 
	private List<PartnerModel> partnerModels;
	private List<BusinessCodeModel> businessCodeModels;
	private List<RatingCodeModel> ratingCodeModels;
	
	public List<PartnerModel> getPartnerModels() {
		return partnerModels;
	}

	public void setPartnerModels(List<PartnerModel> partnerModels) {
		this.partnerModels = partnerModels;
	}

	public List<DeptCodeModel> getDeptCodeModels() {
		return deptCodeModels;
	}

	public void setDeptCodeModels(List<DeptCodeModel> deptCodeModels) {
		this.deptCodeModels = deptCodeModels;
	}

	public List<BusinessCodeModel> getBusinessCodeModels() {
		return businessCodeModels;
	}

	public void setBusinessCodeModels(List<BusinessCodeModel> businessCodeModels) {
		this.businessCodeModels = businessCodeModels;
	}

	public List<RatingCodeModel> getRatingCodeModels() {
		return ratingCodeModels;
	}

	public void setRatingCodeModels(List<RatingCodeModel> ratingCodeModels) {
		this.ratingCodeModels = ratingCodeModels;
	}
	
	
	
	
	

}
