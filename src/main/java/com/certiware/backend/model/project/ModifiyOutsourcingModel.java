package com.certiware.backend.model.project;

import java.util.List;

import com.certiware.backend.model.common.OutsourcingModel;

public class ModifiyOutsourcingModel {
	
	private List<OutsourcingModel> mergeOutsourcingModels;
	private List<OutsourcingModel> deleteOutsourcingModels;
	
	public List<OutsourcingModel> getMergeOutsourcingModels() {
		return mergeOutsourcingModels;
	}
	public void setMergeOutsourcingModels(List<OutsourcingModel> mergeOutsourcingModels) {
		this.mergeOutsourcingModels = mergeOutsourcingModels;
	}
	public List<OutsourcingModel> getDeleteOutsourcingModels() {
		return deleteOutsourcingModels;
	}
	public void setDeleteOutsourcingModels(List<OutsourcingModel> deleteOutsourcingModels) {
		this.deleteOutsourcingModels = deleteOutsourcingModels;
	}
	
	

}
