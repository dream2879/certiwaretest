package com.certiware.backend;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.certiware.backend.config.JwtFilter;
import com.certiware.backend.config.RoleFilter;
import com.certiware.backend.model.main.SelectMenuModel;
import com.certiware.backend.service.MainService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CertiwareApplication.class)
@WebAppConfiguration
public class CertiwareApplicationTests {
	
	@Autowired
	RoleFilter filter;
	/*
	@Autowired
	MainService main;
	@Autowired
	JwtFilter jwtFilter;
*/
	//@RequestMapping("/selectUserList")
	@Test
	public void selectUserList() throws Exception {
		
		
		
		boolean result = false;
		
		List<SelectMenuModel> selectMenuModels = null;
		
		//selectMenuModels = main.selectMenuList("4");
		
		//result = filter.checkRole("4", "/partner/delete");
		result = filter.checkRole("0", "/main/login");
		
		System.out.println(result);
		
		// 로그
				System.out.println("selectMenuModels() logging Start.." );
						
				for (int i = 0; i < selectMenuModels.size(); i++) {		
							
					System.out.println("class index("+ i +")");
					Log.setLog(selectMenuModels.get(i), "    ");
							
				}
						
				System.out.println("selectMenuModels() logging end.." );
				
		
		
	}
	
	
	//////////////////////////////////////////////////////////////////////////

}
