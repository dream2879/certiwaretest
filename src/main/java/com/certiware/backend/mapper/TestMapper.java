package com.certiware.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.certiware.backend.model.SelectProgress;
import com.certiware.backend.model.TestExcelModel;
import com.certiware.backend.model.common.UserModel;
import com.certiware.backend.model.progress.SelectProgressListResModel;

public interface TestMapper {
	
	@Select("SELECT * FROM TB_EXCEL")
	public List<TestExcelModel> selectExcel() throws Exception;
	
	@Select(" ${userName} ")
	//public List<UserModel> selectTest(UserModel query) throws Exception;
	public List<SelectProgressListResModel> selectTest(UserModel query) throws Exception;
}
