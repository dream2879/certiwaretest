package com.certiware.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certiware.backend.mapper.CommonMapper;
import com.certiware.backend.model.common.BusinessCodeModel;
import com.certiware.backend.model.common.DeptCodeModel;
import com.certiware.backend.model.common.OutsourcingCodeModel;
import com.certiware.backend.model.common.PartnerCodeModel;
import com.certiware.backend.model.common.RankCodeModel;
import com.certiware.backend.model.common.RatingCodeModel;
import com.certiware.backend.model.common.RoleCodeModel;

@Service
public class CommonService {
	
	@Autowired
	private CommonMapper commonMapper;
	
	public List<BusinessCodeModel> SelectBusinessCode() throws Exception{		
		return commonMapper.SelectBusinessCode();
	}
	
	public List<DeptCodeModel> SelectDeptCode() throws Exception{
		return commonMapper.SelectDeptCode();
	}
	
	public List<OutsourcingCodeModel> SelectOutsourcingCode() throws Exception{
		return commonMapper.SelectOutsourcingCode();
	}
	
	public List<PartnerCodeModel> SelectPartnerCode() throws Exception{
		return commonMapper.SelectPartnerCode();
	}
	
	public List<RankCodeModel> SelectRankCode() throws Exception{
		return commonMapper.SelectRankCode();
	}
	
	public List<RatingCodeModel> SelectRatingCode() throws Exception{
		return commonMapper.SelectRatingCode();
	}
	
	public List<RoleCodeModel> SelectRoleCode() throws Exception{
		return commonMapper.SelectRoleCode();
	}

}
