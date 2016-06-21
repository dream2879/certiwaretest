package com.certiware.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certiware.backend.mapper.CommonMapper;
import com.certiware.backend.model.common.BusinessCode;
import com.certiware.backend.model.common.DeptCode;
import com.certiware.backend.model.common.OutsourcingCode;
import com.certiware.backend.model.common.PartnerCodeModel;
import com.certiware.backend.model.common.RankCode;
import com.certiware.backend.model.common.RatingCode;
import com.certiware.backend.model.common.RoleCode;

@Service
public class CommonService {
	
	@Autowired
	private CommonMapper commonMapper;
	
	public List<BusinessCode> SelectBusinessCode() throws Exception{		
		return commonMapper.SelectBusinessCode();
	}
	
	public List<DeptCode> SelectDeptCode() throws Exception{
		return commonMapper.SelectDeptCode();
	}
	
	public List<OutsourcingCode> SelectOutsourcingCode() throws Exception{
		return commonMapper.SelectOutsourcingCode();
	}
	
	public List<PartnerCodeModel> SelectPartnerCode() throws Exception{
		return commonMapper.SelectPartnerCode();
	}
	
	public List<RankCode> SelectRankCode() throws Exception{
		return commonMapper.SelectRankCode();
	}
	
	public List<RatingCode> SelectRatingCode() throws Exception{
		return commonMapper.SelectRatingCode();
	}
	
	public List<RoleCode> SelectRoleCode() throws Exception{
		return commonMapper.SelectRoleCode();
	}

}
