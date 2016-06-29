package com.certiware.backend.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.common.PartnerModel;
import com.certiware.backend.model.common.UserModel;
import com.certiware.backend.model.main.SelectLoginModel;
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
	public String login(@RequestBody Map<String, String> json) throws ServletException {
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
			
			if (!password.equals(pwd)) {
			//if (!bCryptPasswordEncoder.matches(password, pwd)) {
				throw new ServletException("Invalid login. Please check your name and password.");
			}
			
		}catch(Exception e)
		{
			System.out.println("error!!! :" + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("login() end...");		
		return Jwts.builder().setSubject(userId).claim("roles", "user").setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact();		
	}// end login
	
	/**
	 * LOGIN 후 필요한 데이터 조회
	 * 코드성 테이블, USER정보, 메뉴리스트 등
	 * @param userId
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/selectLogin")
	public SelectLoginModel selectLogin(@RequestBody String userId) throws ServletException{
		
		System.out.println("selectLogin() start...");
		
		SelectLoginModel selectLoginModel = new SelectLoginModel();
		
		try{
			
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
	@RequestMapping("selectPartner")
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
	

}
