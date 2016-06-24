package com.certiware.backend.model.progress;

import java.util.List;

import com.certiware.backend.model.common.ManpowerMmModel;

public class ModifyManpowerMmModel {
	
	private List<ManpowerMmModel> mergeManpowerMmModels;
	private List<ManpowerMmModel> deleteManpowerMmModels;
	
	public List<ManpowerMmModel> getMergeManpowerMmModels() {
		return mergeManpowerMmModels;
	}
	public void setMergeManpowerMmModels(List<ManpowerMmModel> mergeManpowerMmModels) {
		this.mergeManpowerMmModels = mergeManpowerMmModels;
	}
	public List<ManpowerMmModel> getDeleteManpowerMmModels() {
		return deleteManpowerMmModels;
	}
	public void setDeleteManpowerMmModels(List<ManpowerMmModel> deleteManpowerMmModels) {
		this.deleteManpowerMmModels = deleteManpowerMmModels;
	}	
}
