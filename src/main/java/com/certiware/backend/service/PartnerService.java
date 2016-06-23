package com.certiware.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certiware.backend.mapper.CommonMapper;
import com.certiware.backend.mapper.PartnerMapper;
import com.certiware.backend.model.common.PartnerModel;
import com.certiware.backend.model.partner.SelectCodeModel;
import com.certiware.backend.model.partner.SelectDetailModel;
import com.certiware.backend.model.partner.SelectListModel;

@Service
public class PartnerService {
	
	@Autowired
	PartnerMapper partnerMapper;
	@Autowired
	CommonMapper commonMapper;
	
	/**
	 * 
	 * @param partnerCodeModel
	 * @return
	 * @throws Exception
	 */
	public SelectCodeModel selectCode(SelectCodeModel selectCodeModel) throws Exception{
		
		selectCodeModel.setBusinessCodeModels(commonMapper.SelectBusinessCode());
		selectCodeModel.setPartnerCodeModels(commonMapper.SelectPartnerCode());
		
		return selectCodeModel;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SelectListModel> selectList() throws Exception{
		return partnerMapper.selectList();
	}
	
	/**
	 * 
	 * @param partnerId
	 * @return
	 * @throws Exception
	 */
	public SelectDetailModel selectDetail(int partnerId) throws Exception{
		return partnerMapper.selectPartnerByPartnerId(partnerId);
	}
	
	/**
	 * 
	 * @param partnerModel
	 * @return
	 * @throws Exception
	 */
	public int insertPartner(PartnerModel partnerModel) throws Exception{
		return partnerMapper.insertPartner(partnerModel).getPartnerId();
	}
	
	/**
	 * 
	 * @param partnerModel
	 * @return
	 * @throws Exception
	 */
	public int updatePartner(PartnerModel partnerModel) throws Exception{
		return partnerMapper.updatePartnerByPartnerId(partnerModel);
	}
	
	/**
	 * 
	 * @param partnerId
	 * @return
	 * @throws Exception
	 */
	public int deletePartner(int partnerId) throws Exception {
		return partnerMapper.deletePartnerByPartnerId(partnerId);				
	}
	
}
