package com.certiware.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certiware.backend.mapper.PartnerMapper;
import com.certiware.backend.mapper.TestMapper;
import com.certiware.backend.model.TestExcelModel;
import com.certiware.backend.model.partner.SelectListModel;

@Service
public class TestService {
	
	@Autowired
	TestMapper testMapper;
	@Autowired
	PartnerMapper partnerMapper;
	
	public List<TestExcelModel> selectExcel() throws Exception {
		return testMapper.selectExcel();
	}
	
	/**
	 * TB_PARTNER 테이블 조회(리스트)
	 * 조건에 따라 사용되는 쿼리문이 다르다.
	 * @return
	 * @throws Exception
	 */
	public List<SelectListModel> selectList(SelectListModel selectListModel) throws Exception{
		return partnerMapper.selectList(selectListModel);
	}

}
