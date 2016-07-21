package com.certiware.backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.common.PartnerModel;
import com.certiware.backend.model.common.ResultModel;
import com.certiware.backend.model.common.UnitPriceModel;
import com.certiware.backend.model.common.UserModel;
import com.certiware.backend.model.main.SelectDashboardReqModel;
import com.certiware.backend.model.main.SelectDashboardResModel;
import com.certiware.backend.model.main.SelectLoginModel;
import com.certiware.backend.model.main.TokeModel;
import com.certiware.backend.service.MainService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/main")
public class MainController {
	
	@Autowired
	private MainService mainService;
	//@Autowired
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * 로그인한다. 로그인이 성공하면 JWT Token을 생성한 후 Front-end로 전달한다. 
	 * @param json
	 * @return 
	 * @throws ServletException
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public TokeModel login(@RequestBody Map<String, String> json) throws ServletException {
		TokeModel tokeModel = new TokeModel(); 
		UserModel userModel = new UserModel();
		String userId = null;
		String password = null;
		try{
			System.out.println("login() start...");
			
			if (json.get("userId")==null || json.get("password") == null) {
				throw new ServletException ("Please fill in username and password");
			}
			
			userId =json.get("userId");
			password = json.get("password");		

			userModel = mainService.selectUserByPK(userId);
			
			if (userModel == null) {
				throw new ServletException ("User name not found.");
			}
			
			String pwd = userModel.getPassword();			
			
			//if (!password.equals(pwd)) {
			if (!BCrypt.checkpw(password, pwd)) {
				throw new ServletException("Invalid login. Please check your name and password.");
			}
			
		}catch(Exception e)
		{
			System.out.println("error!!! :" + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("login() end...");
		
		tokeModel.setTokens(Jwts.builder().setSubject(userId).claim("roles", "user").setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact());
		tokeModel.setDate(System.currentTimeMillis());
		return tokeModel; 		
	}// end login
	
	/**
	 * LOGIN 후 필요한 데이터 조회
	 * 코드성 테이블, USER정보, 메뉴리스트 등
	 * @param userId
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/selectLogin")
	public SelectLoginModel selectLogin(@RequestBody Map<String, String> json) throws ServletException{
		
		System.out.println("selectLogin() start...");
		
		String userId = null;		
		SelectLoginModel selectLoginModel = new SelectLoginModel();
		
		try{
			
			userId = json.get("userId");
			
			selectLoginModel = mainService.selectLogin(selectLoginModel, userId);
			
		}
		catch(Exception e)
		{
			System.out.println("error!!! :" + e.toString());
			throw new ServletException(e.toString());
		}
	
		System.out.println("selectLogin() end...");		
		return selectLoginModel;
		
	}// end selectLogin
	
	
	/**
	 * 매출처를 조회한다.
	 * @param partnerName:매출처명
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectPartner")
	public List<PartnerModel> selectCustomerPatner(@RequestBody PartnerModel partnerModel) throws ServletException{
		System.out.println("selectCustomerPatner() start... ");
		List<PartnerModel> partnerModels = null;
		
		try{
			
			partnerModels=mainService.selectCustomerPatner(partnerModel);
			
		}
		catch(Exception e)
		{
			System.out.println("error!! :" + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectCustomerPatner() end... ");
		return partnerModels;
	}
	
	
	/**
	 * 메인 페이지 구성을 위한 통계정보를 조회한다.
	 * @param dashboardReqModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectDashboard")
	public SelectDashboardResModel selectDashboard(@RequestBody SelectDashboardReqModel dashboardReqModel) throws ServletException{
		
		System.out.println("selectDashboard() start... ");
		SelectDashboardResModel selectDashboardResModel = new SelectDashboardResModel();
		
		try{
			
			selectDashboardResModel=mainService.selectDashboard(dashboardReqModel, selectDashboardResModel);
			
		}
		catch(Exception e)
		{
			System.out.println("error!! :" + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectDashboard() end... ");
		return selectDashboardResModel;		
	}
	
	/**
	 * 단가 정보를 조회한다
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectUnitPrice")
	public List<UnitPriceModel> selectUnitPrice() throws ServletException{
		System.out.println("selectUnitPrice() start... ");
		List<UnitPriceModel> unitPriceModels = new ArrayList<>(); 
		
		try{
			
			unitPriceModels=mainService.selectUnitPrice("");
			
		}
		catch(Exception e)
		{
			System.out.println("error!! :" + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectUnitPrice() end... ");
		
		return unitPriceModels;		
	}
	
	/**
	 * 단가 테이블의 정보를 merge한다.
	 * @param unitPriceModels
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/mergeUnitPrice")
	public ResultModel mergeUnitPrice(@RequestBody List<UnitPriceModel> unitPriceModels) throws ServletException{
		System.out.println("mergeUnitPrice() start... ");
		ResultModel resultModel = new ResultModel(); 
		
		try{
			
			resultModel.setResult(mainService.mergeUnitPrice(unitPriceModels));
			
		}
		catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error!! :" + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("mergeUnitPrice() end... ");
		
		return resultModel;		
	}
	
	
	/**
	 * 단가 테이블의 정보를 삭제한다(년단위)
	 * @param json
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/deleteUnitPrice")
	public ResultModel deleteUnitPrice(@RequestBody Map<String, String> json) throws ServletException{
		
		System.out.println("deleteUnitPrice() start... ");
		
		ResultModel resultModel = new ResultModel();
		String year = null;
		
		try{
			
			// 유효성 검사
			if (json.get("year")==null) {
				throw new ServletException ("Please fill in year");
			}
			
			// 파라미터 추출
			year =json.get("year");
			
			// 서비스 호출
			resultModel.setResult(mainService.deleteUnitPrice(year));
			
		}
		catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			System.out.println("error!! :" + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("deleteUnitPrice() end... ");
		
		return resultModel;		
	}

}



