package com.certiware.backend.model.project;

import java.util.List;

import com.certiware.backend.model.common.OutsourcingModel;

public class ModifyOutsourcingModel {
	
	private List<SelectOutsourcingModel> mergeOutsourcingModels;
	private List<SelectOutsourcingModel> deleteOutsourcingModels;
	
	public List<SelectOutsourcingModel> getMergeOutsourcingModels() {
		return mergeOutsourcingModels;
	}
	public void setMergeOutsourcingModels(List<SelectOutsourcingModel> mergeOutsourcingModels) {
		this.mergeOutsourcingModels = mergeOutsourcingModels;
	}
	public List<SelectOutsourcingModel> getDeleteOutsourcingModels() {
		return deleteOutsourcingModels;
	}
	public void setDeleteOutsourcingModels(List<SelectOutsourcingModel> deleteOutsourcingModels) {
		this.deleteOutsourcingModels = deleteOutsourcingModels;
	}
	
	
	

	
	

}
