package com.certiware.backend;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.certiware.backend.model.project.SelectListModel;
import com.certiware.backend.service.ProjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CertiwareApplication.class)
@WebAppConfiguration
public class ProjectTest {
	
	@Autowired
	ProjectService projectService;
	
	@Test
	public void selectList() throws Exception {
		
		List<SelectListModel> selectListModels = null;
		SelectListModel selectListModel = new SelectListModel();
		selectListModel.setProjectName("인터넷");
		selectListModel.setPartnerName("K");
		selectListModel.setDeptCode("5");
		
		selectListModels = projectService.selectList(selectListModel);
		
		System.out.println(selectListModels.size());		
		
	}

}
