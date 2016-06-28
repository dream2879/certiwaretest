package com.certiware.backend;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.certiware.backend.model.admin.SelectUserListModel;
import com.certiware.backend.model.common.UserModel;
import com.certiware.backend.service.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CertiwareApplication.class)
@WebAppConfiguration
public class CertiwareApplicationTests {
	
	@Autowired
	AdminService adminService;	


	//@RequestMapping("/selectUserList")
	@Test
	public void selectUserList() throws Exception {
		List<SelectUserListModel> userModels = null;
		UserModel userModel = new UserModel();
		
		userModel.setDeptCode("1");
		userModel.setUserName("철");
		userModel.setRankCode("3");
				
		userModels=adminService.selectUserList(userModel);
		
		System.out.println(userModels.size());
		
	}
	
	
	//////////////////////////////////////////////////////////////////////////

}
