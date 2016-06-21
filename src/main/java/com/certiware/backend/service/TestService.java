package com.certiware.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certiware.backend.mapper.TestMapper;
import com.certiware.backend.model.TestExcelModel;

@Service
public class TestService {
	
	@Autowired
	TestMapper testMapper;
	
	public List<TestExcelModel> selectExcel() throws Exception {
		return testMapper.selectExcel();
	}

}
