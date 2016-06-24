package com.certiware.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certiware.backend.mapper.ProgressMapper;
import com.certiware.backend.model.common.ManpowerMmModel;
import com.certiware.backend.model.common.ManpowerModel;
import com.certiware.backend.model.progress.ModifyManpowerMmModel;
import com.certiware.backend.model.progress.ModifyManpowerModel;
import com.certiware.backend.model.progress.SelectCodeModel;

@Service
public class ProgressService {
	
	@Autowired
	ProgressMapper progressMapper;
	@Autowired
	CommonService commonService;
	
	public SelectCodeModel selectCodeModel(SelectCodeModel selectCodeModel, int projectId, String deptCode) throws Exception{
		
		selectCodeModel.setDeptCodeModels(commonService.SelectDeptCode());
		selectCodeModel.setManpowerNameListModels(progressMapper.selectManpowerNameList(projectId));
		selectCodeModel.setProjectListModels(progressMapper.selectProjectList(deptCode));
		selectCodeModel.setProjectPartnerModels(progressMapper.selectProjectPartner(projectId));
		selectCodeModel.setRatingCodeModels(commonService.SelectRatingCode());
		
		return selectCodeModel;	
	}
	
	/**
	 * 
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	public List<ManpowerModel> selectManpowerList(int projectId) throws Exception{
		return progressMapper.selectManpowerList(projectId);
	}
	
	/**
	 * 
	 * @param modifyManpowerModel
	 * @return
	 * @throws Exception
	 */
	public int modifyManpower(ModifyManpowerModel modifyManpowerModel) throws Exception{
		int result=0;
	
		progressMapper.mergeManpower(modifyManpowerModel.getMergeManpowerModels());
		
		result =+ progressMapper.deleteManpower(modifyManpowerModel.getDeleteManpowerModels());
		
		return result;
	}
	
	/**
	 * 
	 * @param projectId
	 * @param manpowerName
	 * @return
	 * @throws Exception
	 */
	public List<ManpowerMmModel> selectManpowerMmList(int projectId, String manpowerName) throws Exception{
		return progressMapper.selectManpowerMmList(projectId, manpowerName);
	}
	
	/**
	 * 
	 * @param modifyManpowerMmModel
	 * @return
	 * @throws Exception
	 */
	public int modifyManpowerMm(ModifyManpowerMmModel modifyManpowerMmModel) throws Exception{
		
		int result = 0;
		
		progressMapper.mergeManpowerMm(modifyManpowerMmModel.getMergeManpowerMmModels());
		result = progressMapper.deleteManpowerMm(modifyManpowerMmModel.getDeleteManpowerMmModels());	
		
		return result;
	}
	
	

	

	
	
	

}
