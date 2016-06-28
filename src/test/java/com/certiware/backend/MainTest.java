package com.certiware.backend;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.certiware.backend.model.admin.SelectUserListModel;
import com.certiware.backend.model.common.PartnerModel;
import com.certiware.backend.service.AdminService;
import com.certiware.backend.service.MainService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CertiwareApplication.class)
@WebAppConfiguration
public class MainTest {
	
	@Autowired
	MainService mainService;
	
	//@RequestMapping("/selectUserList")
		@Test
		public void selectPartner() throws Exception {				
		// 변수선언
		PartnerModel req = new PartnerModel();
		List<PartnerModel> res = null;
			
		// 데이타설정
		req.setPartnerName("K");
		req.setBusinessCode("1");
		
			
		
		// 서비스 호출
		res=mainService.selectCustomerPatner(req);
		
		// 로그
		System.out.println("selectPartner() logging Start..");
					
		for (int i = 0; i < res.size(); i++) {		
						
			System.out.println("class index("+ i +")");
			Log.setLog(res.get(i), "    ");
						
		}
					
		System.out.println("selectPartner() logging end.." );
				
	}

}
