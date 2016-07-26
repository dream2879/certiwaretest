package com.certiware.backend.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.admin.DeleteDeptCodeModel;
import com.certiware.backend.model.admin.SelectUserListModel;
import com.certiware.backend.model.admin.UpdateUserModel;
import com.certiware.backend.model.common.DeptCodeModel;
import com.certiware.backend.model.common.ResultModel;
import com.certiware.backend.model.common.UserModel;
import com.certiware.backend.service.AdminService;

@RestController
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	//@Autowired
	//BCryptPasswordEncoder bCryptPasswordEncoder;
			
	/**
	 * 유저정보를 조회한다.
	 * 조건에 맞는 리스트조회
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectUserList")
	public List<SelectUserListModel> selectUserList(@RequestBody SelectUserListModel selectUserListModel) throws ServletException{
		System.out.println("selectUserList() start...");
		
		List<SelectUserListModel> selectUserListModels = null;
		
		try{			
			
			selectUserListModels = adminService.selectUserList(selectUserListModel);			
			
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectUserList() end...");
		
		return selectUserListModels;
	}// end selectUserList
	
	/**
	 * 유저정보를 조회한다.
	 * userId에 해당하는 단건 조회
	 * @param userId
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectUserDetail")
	public UserModel selectUserDetail(@RequestBody UserModel userModel) throws ServletException{
		System.out.println("selectUserDetail() start...");
		
		//UserModel userModelre = new UserModel();
		
		try{	
			// validate
			if(userModel.getUserId() == null || userModel.getUserId().equals("") ){
				throw new ServletException("유저아이디는 필수 항목입니다.");
			}
			
			userModel = adminService.selectUserDetail(userModel);			
			
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectUserDetail() end...");
		
		return userModel;
	}// end selectUserDetail
	
	
	/**
	 * 유저정보를 입력한다.
	 * @param userModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/insertUser")
	public ResultModel insertUser(@RequestBody UserModel userModel) throws ServletException{
		System.out.println("insertUser() start...");
		
		ResultModel resultModel = new ResultModel();
		try{
			// 넘어온 Password를 BCrypt알고리즘을 이용하여 변환한다.			
			userModel.setPassword(BCrypt.hashpw(userModel.getPassword(), BCrypt.gensalt()));			
			System.out.println("BCRYPT : " + userModel.getPassword());
			
			resultModel.setResult(adminService.insertUser(userModel));
			
		}catch(Exception e)
		{
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("insertUser() end...");
		
		return resultModel;
	}// end insertUser
	
	/**
	 * 유저정보를 업데이트한다.
	 * @param userModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/updateUser")	
	public ResultModel updateUser(@RequestBody UpdateUserModel updateUserModel) throws ServletException{
		
		System.out.println("updateUser() start...");
		
		ResultModel resultModel = new ResultModel();
		
		try{
			
			// 넘어온 Password를 BCrypt알고리즘을 이용하여 변환한다.			
			updateUserModel.setPassword(BCrypt.hashpw(updateUserModel.getPassword(), BCrypt.gensalt()));			
			System.out.println("BCRYPT : " + updateUserModel.getPassword());
			
			resultModel.setResult(adminService.updateUser(updateUserModel));
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("selectList() end...");
		
		return resultModel;
	}// end updateUser
	
	/**
	 * 유저정보를 삭제한다.
	 * @param userId
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/deleteUser")
	public ResultModel deleteUser(@RequestBody Map<String, String> json) throws ServletException{
		System.out.println("deleteUser() start...");
		
		String userId;
		ResultModel resultModel = new ResultModel();
		
		try{
			
			userId = json.get("userId");
			
			resultModel.setResult(adminService.deleteUser(userId));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("deleteUser() end...");
		
		return resultModel;
	}// end deleteUser
	
	/**
	 * 부서정보를 조회한다.
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectDeptCode")
	public List<DeptCodeModel> selectDeptCode() throws ServletException{
		System.out.println("selectDeptCode() start...");
		
		List<DeptCodeModel> deptCodeModels = null;
		
		try{
			
			deptCodeModels = adminService.SelectDeptCode();
			
		}catch(Exception e)
		{
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}
		
		System.out.println("selectDeptCode() end...");
		
		return deptCodeModels;
	}// end selectDeptCodet
	
//	/**
//	 * 부서정보를 변경한다.
//	 * @param modifyDeptCodeModel
//	 * @return
//	 * @throws ServletException
//	 */
//	@RequestMapping("/modifyDoptCode")
//	public ResultModel modifyDoptCode(ModifyDeptCodeModel modifyDeptCodeModel) throws ServletException{
//		System.out.println("modifyDoptCode() start...");
//		
//		ResultModel resultModel = new ResultModel();
//		
//		try{
//			
//			resultModel.setResult(adminService.modifyDeptCode(modifyDeptCodeModel));
//			
//		}catch(Exception e)
//		{
//			resultModel.setMessage(e.toString());
//			
//			System.out.println("error : " + e.toString());
//			//throw new ServletException(e.toString());
//		}
//		
//		System.out.println("modifyDoptCode() end...");
//		
//		return resultModel;
//	}// end modifyDoptCode
	
	/**
	 * 부서을 입력한다.
	 * @param json
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/insertDeptCode")
	public ResultModel insertDeptCode(@RequestBody Map<String, String> json) throws ServletException{
		System.out.println("insertDeptCode() start...");
		
		String deptName;
		ResultModel resultModel = new ResultModel();
		
		try{
			
			deptName = json.get("deptName");
			
			resultModel.setResult(adminService.insertDeptCode(deptName));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("insertDeptCode() end...");
		
		return resultModel;
	}// end insertDeptCode
	
	
	/**
	 * 부서정보를 업데이트한다.
	 * @param deptCodeModels
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/updateDeptCode")
	public ResultModel updateDeptCode(@RequestBody List<DeptCodeModel> deptCodeModels) throws ServletException{
		System.out.println("updateDeptCode() start...");
		
		
		ResultModel resultModel = new ResultModel();
		
		try{		
			resultModel.setResult(adminService.updateDeptCode(deptCodeModels));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("updateDeptCode() end...");
		
		return resultModel;
	}// end updateDeptCode
	
	/**
	 * 부서코드를 삭제한다.
	 * @param json
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/deleteDeptCode")
	public ResultModel deleteDeptCode(@RequestBody List<DeleteDeptCodeModel> deptCodeModels) throws ServletException{
		System.out.println("deleteDeptCode() start...");		
		
		ResultModel resultModel = new ResultModel();
		
		try{	
			
			resultModel.setResult(adminService.deleteDeptCode(deptCodeModels));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("deleteDeptCode() end...");
		
		return resultModel;
	}// end deleteDeptCode
	
}
