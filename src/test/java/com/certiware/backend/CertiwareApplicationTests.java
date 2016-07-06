package com.certiware.backend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.certiware.backend.config.RoleFilter;
import com.certiware.backend.controller.ProjectController;
import com.certiware.backend.model.SelectProgress;
import com.certiware.backend.model.common.UserModel;
import com.certiware.backend.model.progress.SelectProgressListReqModel;
import com.certiware.backend.model.progress.SelectProgressListResModel;
import com.certiware.backend.model.project.SelectOutsourcingModel;
import com.certiware.backend.service.ProgressService;
import com.certiware.backend.service.TestService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CertiwareApplication.class)
@WebAppConfiguration
public class CertiwareApplicationTests {
	
	@Autowired
	RoleFilter filter;
	@Autowired 
	TestService testService;  
	
	@Autowired
	ProgressService progressService;
	
	@Autowired
	ProjectController projectController;
	/*
	@Test
	public void test() throws Exception {
		
		// String req = "USER ";
		UserModel req = new UserModel();
		List<UserModel> res = new ArrayList<>();
		
		req.setUserName( "SELECT * FROM TB_USER ");
		
		
		res = testService.selectTest(req);
		
		// 로그
				System.out.println("selectUserList() logging Start.." );
						
				for (int i = 0; i < res.size(); i++) {		
							
					System.out.println("class index("+ i +")");
					Log.setLog(res.get(i), "    ");
							
				}
						
				System.out.println("selectUserList() logging end.." );
		
		
	}
	*/
	
	
	/*
	@Test
	public void test2() throws Exception {
		
		System.out.println("test2() logging Start.." );
		
		SelectProgressListReqModel selectProgressListReqModel = new SelectProgressListReqModel();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Date startDate = df.parse("2016-01-01");
		Date endDate = df.parse("2018-02-01");
		
		selectProgressListReqModel.setStartDate(startDate);
		selectProgressListReqModel.setEndDate(endDate);
		List<SelectProgressListResModel> selectProgresses = progressService.selectProgressList(selectProgressListReqModel); 
		
		// 로그
		
		System.out.println("test2() logging Start.." );
				
		for (int i = 0; i < selectProgresses.size(); i++) {		
					
			System.out.println("class index("+ i +")");
			Log.setLog(selectProgresses.get(i), "    ");
					
		}
				
		System.out.println("test2() logging end.." + selectProgresses.size());
		
	}
	*/
	
	@Test
	public void test2() throws Exception {
		
		System.out.println("test2() logging Start.." );
		
		List<SelectOutsourcingModel> selectOutsourcingModels = new ArrayList<>();
		
		Map<String, String> json = new HashMap<>();
		
		json.put("projectId", "1");	
		
		selectOutsourcingModels = projectController.selectOutsourcingList(json);
		
		// 로그
		
		System.out.println("test2() logging Start.." );
				
		for (int i = 0; i < selectOutsourcingModels.size(); i++) {		
					
			System.out.println("class index("+ i +")");
			Log.setLog(selectOutsourcingModels.get(i), "    ");
					
		}
				
		System.out.println("test2() logging end.." + selectOutsourcingModels.size());
		
	}
	
	
	//////////////////////////////////////////////////////////////////////////

}
