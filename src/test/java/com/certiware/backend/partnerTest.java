package com.certiware.backend;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.certiware.backend.model.common.ResultModel;
import com.certiware.backend.model.partner.SelectListModel;
import com.certiware.backend.service.PartnerService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CertiwareApplication.class)
@WebAppConfiguration
public class partnerTest {
	
	@Autowired
	PartnerService partnerService;
	
	
	//@RequestMapping(value="/selectList")
	@Test
	public void selectList() throws Exception {
	
		// 변수선언
		SelectListModel req = new SelectListModel();
		List<SelectListModel> res = null;
		
		
		// 데이타설정
		req.setPartnerName("인포");
		req.setBusinessNumber(2);
		
		// 서비스호출
		res = partnerService.selectList(req);
		
		// 로그
		System.out.println("selectList() logging Start.." );
		
		for (int i = 0; i < res.size(); i++) {		
					
			System.out.println("class index("+ i +")");
			Log.setLog(res.get(i), "    ");
				
		}		
	
	}
}
