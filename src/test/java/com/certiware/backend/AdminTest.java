package com.certiware.backend;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.certiware.backend.model.admin.SelectUserListModel;
import com.certiware.backend.service.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CertiwareApplication.class)
@WebAppConfiguration
public class AdminTest {
	
	@Autowired
	AdminService adminService; 
	
	//@RequestMapping("/selectUserList")
	@Test
	public void selectUserList() throws Exception {
			
		// 변수선언
		List<SelectUserListModel> selectUserListModels = null;
		SelectUserListModel selectUserListModel = new SelectUserListModel();
		
		// 데이타설정
		selectUserListModel.setUserName("안");
		selectUserListModel.setDeptName("지원실");
		selectUserListModel.setRankName("대리");
		
	
		// 서비스 호출
		selectUserListModels=adminService.selectUserList(selectUserListModel);
			
		// 로그
		System.out.println("selectUserList() logging Start.." );
				
		for (int i = 0; i < selectUserListModels.size(); i++) {		
					
			System.out.println("class index("+ i +")");
			Log.setLog(selectUserListModels.get(i), "    ");
					
		}
				
		System.out.println("selectUserList() logging end.." );
			
	}

}
