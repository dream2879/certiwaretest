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
	 * TB_PARTNER 테이블 조회(리스트)
	 * 조건에 따라 사용되는 쿼리문이 다르다.
	 * @return
	 * @throws Exception
	 */
	public List<SelectListModel> selectList(SelectListModel selectListModel) throws Exception{
		return partnerMapper.selectList(selectListModel);
	}
	
	/**
	 * TB_PARTNER 테이블 조회(단건)
	 * @param partnerId
	 * @return
	 * @throws Exception
	 */
	public SelectDetailModel selectDetail(int partnerId) throws Exception{
		return partnerMapper.selectPartnerByPartnerId(partnerId);
	}
	
	/**
	 * TB_PARTNER 테이블 입력
	 * @param partnerModel
	 * @return
	 * @throws Exception
	 */
	public boolean insertPartner(PartnerModel partnerModel) throws Exception{
		
		partnerMapper.insertPartner(partnerModel).getPartnerId();
		
		return true;
	}
	
	/**
	 * TB_PARTNER 테이블 변경
	 * @param partnerModel
	 * @return
	 * @throws Exception
	 */
	public boolean updatePartner(PartnerModel partnerModel) throws Exception{
		
		partnerMapper.updatePartnerByPartnerId(partnerModel);
		
		return true;
	}
	
	/**
	 * TB_PARTNER 테이블 삭제
	 * @param partnerId
	 * @return
	 * @throws Exception
	 */
	public boolean deletePartner(int partnerId) throws Exception {
		
		partnerMapper.deletePartnerByPartnerId(partnerId);
		
		return true;
	}
	
}
