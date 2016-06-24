package com.certiware.backend.controller;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.admin.AdminCodeModel;
import com.certiware.backend.model.admin.ModifyDeptCodeModel;
import com.certiware.backend.model.common.DeptCodeModel;
import com.certiware.backend.model.common.UserModel;
import com.certiware.backend.model.project.SelectListModel;
import com.certiware.backend.service.AdminService;

@RestController
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	/**
	 * 
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectCode")
	public AdminCodeModel selectCode() throws ServletException{
		System.out.println("selectCode() start...");
		
		AdminCodeModel adminCodeModel = new AdminCodeModel();
		
		try{
			
			adminCodeModel = adminService.selectCode(adminCodeModel);
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectCode() end...");
		
		return adminCodeModel;
	}//end selectCode
	
	/**
	 * 
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectUserList")
	public List<UserModel> selectUserList() throws ServletException{
		System.out.println("selectUserList() start...");
		
		List<UserModel> userModels = null;
		
		try{
			
			userModels = adminService.selectUserList();			
			
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectUserList() end...");
		
		return userModels;
	}// end selectUserList
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectUserDetail")
	public UserModel selectUserDetail(@RequestBody String userId) throws ServletException{
		System.out.println("selectUserDetail() start...");
		
		UserModel userModel = new UserModel();
		
		try{
			
			userModel = adminService.selectUserDetail(userId);			
			
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectUserDetail() end...");
		
		return userModel;
	}// end selectUserDetail
	
	
	/**
	 * 
	 * @param userModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/insertUser")
	public String insertUser(@RequestBody UserModel userModel) throws ServletException{
		System.out.println("insertUser() start...");
				
		try{
			
			adminService.insertUser(userModel);
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("insertUser() end...");
		
		return "insert Success";
	}// end insertUser
	
	/**
	 * 
	 * @param userModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/updateUser")	
	public int updateUser(@RequestBody UserModel userModel) throws ServletException{
		System.out.println("updateUser() start...");
		
		int result = 0;
		
		try{
			
			result = adminService.updateUser(userModel);
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectList() end...");
		
		return result;
	}// end updateUser
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws ServletException
	 */
	//@RequestMapping("/deleteUser")
	public int deleteUser(@RequestBody String userId) throws ServletException{
		System.out.println("deleteUser() start...");
		
		int result = 0;
		
		try{
			
			result = adminService.deleteUser(userId);			
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("deleteUser() end...");
		
		return result;
	}// end deleteUser
	
	/**
	 * 
	 * @return
	 * @throws ServletException
	 */
	//@RequestMapping("/selectDeptCodet")
	public List<DeptCodeModel> selectDeptCodet() throws ServletException{
		System.out.println("selectDeptCodet() start...");
		
		List<DeptCodeModel> deptCodeModels = null;
		
		try{
			
			deptCodeModels = adminService.SelectDeptCode();
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectDeptCodet() end...");
		
		return deptCodeModels;
	}// end selectDeptCodet
	
	/**
	 * 
	 * @param modifyDeptCodeModel
	 * @return
	 * @throws ServletException
	 */
	//@RequestMapping("/modifyDoptCode")
	public int modifyDoptCode(ModifyDeptCodeModel modifyDeptCodeModel) throws ServletException{
		System.out.println("modifyDoptCode() start...");
		
		int result = 0;
		
		try{
			
			result = adminService.modifyDeptCode(modifyDeptCodeModel);
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("modifyDoptCode() end...");
		
		return result;
	}// end modifyDoptCode
	
}
