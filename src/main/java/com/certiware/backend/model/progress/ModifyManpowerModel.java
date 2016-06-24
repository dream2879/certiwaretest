package com.certiware.backend.model.progress;

import java.util.List;

import com.certiware.backend.model.common.ManpowerModel;

public class ModifyManpowerModel {
	
	private List<ManpowerModel> mergeManpowerModels;
	private List<ManpowerModel> deleteManpowerModels;
	
	public List<ManpowerModel> getMergeManpowerModels() {
		return mergeManpowerModels;
	}
	public void setMergeManpowerModels(List<ManpowerModel> mergeManpowerModels) {
		this.mergeManpowerModels = mergeManpowerModels;
	}
	public List<ManpowerModel> getDeleteManpowerModels() {
		return deleteManpowerModels;
	}
	public void setDeleteManpowerModels(List<ManpowerModel> deleteManpowerModels) {
		this.deleteManpowerModels = deleteManpowerModels;
	}
	
	

}
