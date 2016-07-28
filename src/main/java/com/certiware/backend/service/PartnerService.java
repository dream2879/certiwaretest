package com.certiware.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certiware.backend.component.CommonComponent;
import com.certiware.backend.mapper.CommonMapper;
import com.certiware.backend.mapper.PartnerMapper;
import com.certiware.backend.model.common.PartnerModel;
import com.certiware.backend.model.partner.PartnerWorkListReqModel;
import com.certiware.backend.model.partner.PartnerWorkListResModel;
import com.certiware.backend.model.partner.SelectListModel;

@Service
public class PartnerService {
	
	@Autowired
	PartnerMapper partnerMapper;
	@Autowired
	CommonMapper commonMapper;
	@Autowired
	CommonComponent commonComponent;
	
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
	public PartnerModel selectDetail(int partnerId) throws Exception{
		return partnerMapper.selectPartnerByPartnerId(partnerId);
	}
	
	/**
	 * TB_PARTNER 테이블 입력
	 * @param partnerModel
	 * @return
	 * @throws Exception
	 */
	public boolean insertPartner(PartnerModel partnerModel) throws Exception{
		
		partnerMapper.insertPartner(partnerModel);
		
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
	
	
	/**
	 * 외주업체의 계약 내역을 조회한다.
	 * @param partnerWorkListReqModel
	 * @return
	 * @throws Exception
	 */
	public List<PartnerWorkListResModel> selectPartnerWorkList(PartnerWorkListReqModel partnerWorkListReqModel) throws Exception{
		
		
		// 시작일이 들어왔으면 날짜 조건을 추가해준다.
		if(partnerWorkListReqModel.getStartDate() != null){			
			
			Date date = partnerWorkListReqModel.getStartDate();			
			partnerWorkListReqModel.setStartDate(commonComponent.makeDate(date, "start"));
			partnerWorkListReqModel.setEndDate(commonComponent.makeDate(date, "end"));
			
		}
		
		// DB 조회 후 결과 리턴		
		return partnerMapper.selectPartnerWorkList(partnerWorkListReqModel);
		
	}
	
}
