package com.certiware.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certiware.backend.mapper.ProgressMapper;
import com.certiware.backend.model.common.ManpowerMmModel;
import com.certiware.backend.model.common.ManpowerModel;
import com.certiware.backend.model.progress.ModifyManpowerMmModel;
import com.certiware.backend.model.progress.ModifyManpowerModel;

@Service
public class ProgressService {
	
	@Autowired
	ProgressMapper progressMapper;
	@Autowired
	CommonService commonService;
	
	/**
	 * TB_MANPOWER 테이블조회
	 * @param projectId:프로젝트아이디
	 * @return
	 * @throws Exception
	 */
	public List<ManpowerModel> selectManpowerList(int projectId) throws Exception{
		return progressMapper.selectManpowerList(projectId);
	}
	
	/**
	 * TB_MANPOWER 테이블 MERGE, DELETE 수행
	 * @param modifyManpowerModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean modifyManpower(ModifyManpowerModel modifyManpowerModel) throws Exception{		
	
		// merge
		for (ManpowerModel manpowerModel : modifyManpowerModel.getMergeManpowerModels()) {
			progressMapper.mergeManpower(manpowerModel);
		}
		
		
		// delete
		for (ManpowerModel manpowerModel : modifyManpowerModel.getDeleteManpowerModels()) {
			progressMapper.deleteManpower(manpowerModel);
		}
		
		return true;
	}
	
	/**
	 * TB_MANPOWERMM 테이블조회
	 * @param projectId:프로젝트ID
	 * @param manpowerName:투입인력이름
	 * @return
	 * @throws Exception
	 */
	public List<ManpowerMmModel> selectManpowerMmList(int projectId, String manpowerName) throws Exception{
		return progressMapper.selectManpowerMmList(projectId, manpowerName);
	}
	
	/**
	 * TB_MANPOWERMM 테이블 MERGE, DELETE
	 * @param modifyManpowerMmModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean modifyManpowerMm(ModifyManpowerMmModel modifyManpowerMmModel) throws Exception{
		
		// update
		for (ManpowerMmModel manpowerMmModel : modifyManpowerMmModel.getMergeManpowerMmModels()) {
			progressMapper.mergeManpowerMm(manpowerMmModel);
		}
		
		// delete
		for (ManpowerMmModel manpowerMmModel : modifyManpowerMmModel.getDeleteManpowerMmModels()) {
			progressMapper.deleteManpowerMm(manpowerMmModel);
		}	
		
		return true;
	}
	
	

	

	
	
	

}
