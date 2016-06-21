package com.certiware.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.certiware.backend.model.TestExcelModel;

public interface TestMapper {
	
	@Select("SELECT * FROM TB_EXCEL")
	public List<TestExcelModel> selectExcel() throws Exception;
}
