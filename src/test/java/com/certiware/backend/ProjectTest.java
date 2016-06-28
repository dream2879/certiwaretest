package com.certiware.backend;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.certiware.backend.model.common.ProjectModel;
import com.certiware.backend.model.project.SelectDetailModel;
import com.certiware.backend.model.project.SelectListModel;
import com.certiware.backend.model.project.SelectProjectListModel;
import com.certiware.backend.service.ProjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CertiwareApplication.class)
@WebAppConfiguration
public class ProjectTest {
	
	@Autowired
	ProjectService projectService;
	
	// @RequestMapping(value="/selectList")
	@Test
	public void selectList() throws Exception {
		
		// 변수 선언
		List<SelectListModel> selectListModels = null;
		SelectListModel selectListModel = new SelectListModel();
		
		// 데이타 설정		
		selectListModel.setProjectName("인터넷");
		selectListModel.setPartnerName("K");
		selectListModel.setDeptCode("5");
		
		// 서비스 호출
		selectListModels = projectService.selectList(selectListModel);		
		
		// 로그
		System.out.println("selectList() logging Start.." );
		
		for (int i = 0; i < selectListModels.size(); i++) {		
			
			System.out.println("class index("+ i +")");
			Log.setLog(selectListModels.get(i), "    ");
			
		}
		
		System.out.println("selectList() logging end.." );
	}// end
	
	// @RequestMapping(value="/selectDetail", method=RequestMethod.POST)
	@Test
	public void selectDetail() throws Exception {	
		
		// 변수 선언
		int req;
		SelectDetailModel res = new SelectDetailModel();
		
		// 데이타 설정		
		req = 1;
		
		// 서비스 호출
		res = projectService.selectDetail(res, req);
		
		// 로그
		System.out.println("selectDetail() logging Start.." );
		
		Log.setLog(res.getProjectModel(), "");
		
		for (int i = 0; i < res.getOutsourcingModels().size(); i++) {		
			
			System.out.println("class index("+ i +")");
			Log.setLog(res.getOutsourcingModels().get(i), "    ");			
		}
		
		System.out.println("selectDetail() logging end.." );
	}// end
	
	
	// @RequestMapping(value="/insertProject", method=RequestMethod.POST)
	@Test
	public void insertProject() throws Exception {	
		
		// 변수 선언
		ProjectModel req = new ProjectModel();
		int res = 0;
		
		// 데이타 설정		
		req.setProjectName("테스트 프로젝트1111");
		req.setDeptCode("1");
		req.setPartnerId(1);
		//req.setStartDate("2016-01-01");
		//req.setEndDate();
		req.setContractAmount(1000000);
		req.setSupplyAmount(500000);
		req.setVtaAmount(400000);
		req.setOutsourcingAmount(20000);
		req.setNetAmount(3500);		
		
		// 서비스 호출
		res = projectService.insertProject(req);
		
		// 로그
		/*
		System.out.println("insertProject() logging Start.." );
		
		for (int i = 0; i < selectListModels.size(); i++) {		
			
			System.out.println("class index("+ i +")");
			Log.setLog(selectListModels.get(i), "    ");
			
		}
		
		System.out.println("insertProject() logging end.." );
		*/
	}// end
	
	// @RequestMapping(value="/updateProject", method=RequestMethod.POST)
	@Test
	public void updateProject() throws Exception {	
		
		// 변수 선언
		ProjectModel req = new ProjectModel();
		int res = 0;
		
		// 데이타 설정
		req.setProjectId(4);
		req.setProjectName("테스트 프로젝트22222");
		req.setDeptCode("1");
		req.setPartnerId(1);
		//req.setStartDate("2016-01-01");
		//req.setEndDate();
		req.setContractAmount(1000000);
		req.setSupplyAmount(500000);
		req.setVtaAmount(400000);
		req.setOutsourcingAmount(20000);
		req.setNetAmount(3500);	
		
		// 서비스 호출
		res = projectService.updateProject(req);
		
		// 로그
		System.out.println("updateProject() update count : " + res );			

	}// end
	
	
	// @RequestMapping(value="/deleteProject", method=RequestMethod.POST)
	@Test
	public void deleteProject() throws Exception {	
		
		// 변수 선언
		int req;
		int res = 0;
		
		// 데이타 설정		
		req = 7;
		
		// 서비스 호출
		res = projectService.deleteProject(req);
		
		// 로그
		System.out.println("deleteProject() logging delete count : " + res );
	
	}// end
	
	
	/*
	// @RequestMapping(value="/modifyOutsourcing")
	@Test
	public void modifyOutsourcing() throws Exception {	
		
		// 변수 선언
		ModifyOutsourcingModel req = new ModifyOutsourcingModel();
		int res = 0;
		
		// 데이타 설정		
		req.setMergeOutsourcingModels(mergeOutsourcingModels);
		
		// 서비스 호출
		res = projectService.modifyOutsourcing(req);
		
		// 로그
		System.out.println("modifyOutsourcing() logging count : " + res );
		
	}// end
	*/
	
	
	// @RequestMapping("/selectProjectList")
	@Test
	public void selectProjectList() throws Exception {	
		
		// 변수 선언
		String req;
		List<SelectProjectListModel> res = null;
		
		// 데이타 설정		
		req = "1";
		
		// 서비스 호출
		res = projectService.selectProjectList(req);
		
		// 로그
		System.out.println("selectDetail() logging Start.." );
		
		for (int i = 0; i < res.size(); i++) {		
			
			System.out.println("class index("+ i +")");
			Log.setLog(res.get(i), "    ");
			
		}
		
		System.out.println("selectProjectList() logging end.." );
	}// end
	/*
	// @RequestMapping("selectCustomerPatner")
	@Test
	public void selectCustomerPatner() throws Exception {	
		
		// 변수 선언
		String req;
		List<SelectProjectListModel> res = null;
			
		// 데이타 설정		
		req = "1";
			
		// 서비스 호출
		res = projectService.selectProjectList(req);
			
		// 로그
		System.out.println("selectDetail() logging Start.." );
			
		for (int i = 0; i < res.size(); i++) {		
				
			System.out.println("class index("+ i +")");
			Log.setLog(res.get(i), "    ");
			
		}
			
		System.out.println("selectProjectList() logging end.." );
	}// end
	*/
	
	
}
