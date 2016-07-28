package com.certiware.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.certiware.backend.config.RoleFilter;
import com.certiware.backend.model.preproject.MovePreProjectReqModel;
import com.certiware.backend.service.PreProjectService;
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
	PreProjectService preProjectService; 
	
	@Test
	public void test() throws Exception {
		
		MovePreProjectReqModel movePreProjectReqModel = new MovePreProjectReqModel();
		
		movePreProjectReqModel.setProjectId(7);
		
		System.out.println(preProjectService.movePreProject(movePreProjectReqModel));
		
	}
	
	
//	
//	
//	@Test
//	public void test2() throws Exception {
//		
//		System.out.println("test2() logging Start.." );
//		
//		SelectProgressListReqModel selectProgressListReqModel = new SelectProgressListReqModel();
//		
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		
//		Date startDate = df.parse("2016-01-02");
//		Date endDate = df.parse("2016-03-31");
//		
//		selectProgressListReqModel.setStartDate(startDate);
//		selectProgressListReqModel.setEndDate(endDate);
//		selectProgressListReqModel.setDeptCode("5");
//		selectProgressListReqModel.setProjectName("AAA");
//		
//		testService.DateTest(selectProgressListReqModel);
//		
//
//		List<SelectProgressListResModel> selectProgresses = progressService.selectProgressList(selectProgressListReqModel); 
//		
//		// 로그
//		
//		System.out.println("test2() logging Start.." );
//				
//		for (int i = 0; i < selectProgresses.size(); i++) {		
//					
//			System.out.println("class index("+ i +")");
//			Log.setLog(selectProgresses.get(i), "    ");
//					
//		}
//				
//		System.out.println("test2() logging end.." + selectProgresses.size());
//		
//	}
	
//	
//	@Test
//	public void test2() throws Exception {
//		
//		System.out.println("test2() logging Start.." );
//		
//		List<SelectOutsourcingModel> selectOutsourcingModels = new ArrayList<>();
//		
//		Map<String, String> json = new HashMap<>();
//		
//		json.put("projectId", "1");	
//		
//		selectOutsourcingModels = projectController.selectOutsourcingList(json);
//		
//		// 로그
//		
//		System.out.println("test2() logging Start.." );
//				
//		for (int i = 0; i < selectOutsourcingModels.size(); i++) {		
//					
//			System.out.println("class index("+ i +")");
//			Log.setLog(selectOutsourcingModels.get(i), "    ");
//					
//		}
//				
//		System.out.println("test2() logging end.." + selectOutsourcingModels.size());
//		
//	}
//	
	
	//////////////////////////////////////////////////////////////////////////

}
