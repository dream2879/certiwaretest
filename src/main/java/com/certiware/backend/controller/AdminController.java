package com.certiware.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certiware.backend.model.admin.CodeModel;
import com.certiware.backend.model.admin.DeleteDeptCodeModel;
import com.certiware.backend.model.admin.SelectUserListModel;
import com.certiware.backend.model.admin.UpdateUserModel;
import com.certiware.backend.model.common.BusinessCodeModel;
import com.certiware.backend.model.common.DeptCodeModel;
import com.certiware.backend.model.common.OutsourcingCodeModel;
import com.certiware.backend.model.common.PartnerCodeModel;
import com.certiware.backend.model.common.RankCodeModel;
import com.certiware.backend.model.common.RatingCodeModel;
import com.certiware.backend.model.common.ResultModel;
import com.certiware.backend.model.common.UserModel;
import com.certiware.backend.service.AdminService;

@RestController
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
			
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
	
	/**********************************************************************************/
	
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
	
	
	/******************************************** TB_RANK ****************************************************/	
	
	/**
	 * 직급코드를 조회한다.
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("selectRankCode")
	public List<RankCodeModel> selectRankCode() throws ServletException{
		System.out.println("selectRankCode() start...");
		
		List<RankCodeModel> rankCodeModels = new ArrayList<>();
		
		try{	
			
			rankCodeModels = adminService.selectRankCode();			
			
		}catch(Exception e)
		{	
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}	
		
		System.out.println("selectRankCode() end...");
		
		return rankCodeModels;		
	}// end
	
	/**
	 * 직급 정보를 입력한다.
	 * @param codeModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/insertRankCode")
	public ResultModel insertRankCode(@RequestBody CodeModel codeModel) throws ServletException{
		System.out.println("insertRankCode() start...");		
		
		ResultModel resultModel = new ResultModel();
		
		try{	
			
			// 서비스 호출
			resultModel.setResult(adminService.insertRankCode(codeModel));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("insertRankCode() end...");
		
		return resultModel;
	}// end insertRankCode
	
	/**
	 * 직급 정보를 변경한다.
	 * @param codeModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/updateRankCode")
	public ResultModel updateRankCode(@RequestBody List<CodeModel> codeModels) throws ServletException{
		System.out.println("updateRankCode() start...");		
		
		ResultModel resultModel = new ResultModel();
		
		try{	
			
			// 서비스 호출
			resultModel.setResult(adminService.updateRankCode(codeModels));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("updateRankCode() end...");
		
		return resultModel;
	}// end updateRankCode
	
	/**
	 * 직급 정보를 삭제한다.
	 * @param codeModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/deleteRankCode")
	public ResultModel deleteRankCode(@RequestBody List<CodeModel> codeModels) throws ServletException{
		System.out.println("deleteRankCode() start...");		
		
		ResultModel resultModel = new ResultModel();
		
		try{	
			// 서비스 호출
			resultModel.setResult(adminService.deleteRankCode(codeModels));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("deleteRankCode() end...");
		
		return resultModel;
	}// end deleteRankCode
	
	
	/******************************************** TB_BUSINESSCODE ****************************************************/	
	
	/**
	 * 거래처분류 정보를 조회한다.
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("selectBusinessCode")
	public List<BusinessCodeModel> selectBusinessCode() throws ServletException{
		System.out.println("selectBusinessCode() start...");
		
		List<BusinessCodeModel> businessCodeModels = new ArrayList<>();
		
		try{	
			// 서비스 호출
			businessCodeModels = adminService.selectBusinessCode();			
			
		}catch(Exception e)
		{	
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}	
		
		System.out.println("selectBusinessCode() end...");
		
		return businessCodeModels;		
	}// end selectBusinessCode
	
	/**
	 * 거래처분류 정보를 입력한다.
	 * @param codeModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/insertBusinessCode")
	public ResultModel insertBusinessCode(@RequestBody CodeModel codeModel) throws ServletException{
		System.out.println("insertBusinessCode() start...");		
		
		ResultModel resultModel = new ResultModel();
		
		try{	
			
			// 서비스 호출
			resultModel.setResult(adminService.insertBusinessCode(codeModel));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("insertBusinessCode() end...");
		
		return resultModel;
	}// end insertBusinessCode
	
	/**
	 * 거래처분류 정보를 변경한다.
	 * @param codeModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/updateBusinessCode")
	public ResultModel updateBusinessCode(@RequestBody List<CodeModel> codeModels) throws ServletException{
		System.out.println("updateBusinessCode() start...");		
		
		ResultModel resultModel = new ResultModel();
		
		try{	
			
			// 서비스 호출
			resultModel.setResult(adminService.updateBusinessCode(codeModels));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("updateBusinessCode() end...");
		
		return resultModel;
	}// end updateBusinessCode
	
	/**
	 * 거래처 정보를 삭제한다.
	 * @param codeModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/deleteBusinessCode")
	public ResultModel deleteBusinessCode(@RequestBody List<CodeModel> codeModels) throws ServletException{
		System.out.println("deleteRankCode() start...");		
		
		ResultModel resultModel = new ResultModel();
		
		try{	
			// 서비스 호출
			resultModel.setResult(adminService.deleteBusinessCode(codeModels));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("deleteBusinessCode() end...");
		
		return resultModel;
	}// end deleteBusinessCode
	
	
	/******************************************** TB_PARTNERCODE ****************************************************/	
	
	/**
	 * 거래처성격(자사,법인 등) 정보를 조회한다.
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectPartnerCode")
	public List<PartnerCodeModel> selectPartnerCode() throws ServletException{
		System.out.println("selectPartnerCode() start...");
		
		List<PartnerCodeModel> partnerCodeModels = new ArrayList<>();
		
		try{	
			// 서비스 호출
			partnerCodeModels = adminService.selectPartnerCode();			
			
		}catch(Exception e)
		{	
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}	
		
		System.out.println("selectPartnerCode() end...");
		
		return partnerCodeModels;		
	}// end selectPartnerCode
	
	/**
	 * 거래처성격(자사,법인 등) 정보를 입력한다.
	 * @param codeModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/insertPartnerCode")
	public ResultModel insertPartnerCode(@RequestBody CodeModel codeModel) throws ServletException{
		System.out.println("insertPartnerCode() start...");		
		
		ResultModel resultModel = new ResultModel();
		
		try{	
			
			// 서비스 호출
			resultModel.setResult(adminService.insertPartnerCode(codeModel));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("insertPartnerCode() end...");
		
		return resultModel;
	}// end insertPartnerCode
	
	/**
	 * 거래처성격(자사,법인 등) 정보를 변경한다.
	 * @param codeModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/updatePartnerCode")
	public ResultModel updatePartnerCode(@RequestBody List<CodeModel> codeModels) throws ServletException{
		System.out.println("updatePartnerCode() start...");		
		
		ResultModel resultModel = new ResultModel();
		
		try{	
			
			// 서비스 호출
			resultModel.setResult(adminService.updatePartnerCode(codeModels));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("updatePartnerCode() end...");
		
		return resultModel;
	}// end updatePartnerCode
	
	/**
	 * 거래처성격(자사,법인 등) 정보를 삭제한다.
	 * @param codeModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/deletePartnerCode")
	public ResultModel deletePartnerCode(@RequestBody List<CodeModel> codeModels) throws ServletException{
		System.out.println("deletePartnerCode() start...");		
		
		ResultModel resultModel = new ResultModel();
		
		try{	
			// 서비스 호출
			resultModel.setResult(adminService.deletePartnerCode(codeModels));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("deletePartnerCode() end...");
		
		return resultModel;
	}// end deletePartnerCode
	
	
	/******************************************** TB_OUTSOURCINGCODE ****************************************************/	
	
	/**
	 * 외주구분 정보를 조회한다.
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectOutsourcingCode")
	public List<OutsourcingCodeModel> selectOutsourcingCode() throws ServletException{
		System.out.println("selectOutsourcingCode() start...");
		
		List<OutsourcingCodeModel> outsourcingCodeModels = new ArrayList<>();
		
		try{	
			// 서비스 호출
			outsourcingCodeModels = adminService.selectOutsourcingCode();			
			
		}catch(Exception e)
		{	
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}	
		
		System.out.println("selectOutsourcingCode() end...");
		
		return outsourcingCodeModels;		
	}// end selectOutsourcingCode
	
	/**
	 * 외주구분 정보를 입력한다.
	 * @param codeModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/insertOutsourcingCode")
	public ResultModel insertOutsourcingCode(@RequestBody CodeModel codeModel) throws ServletException{
		System.out.println("insertOutsourcingCode() start...");		
		
		ResultModel resultModel = new ResultModel();
		
		try{	
			
			// 서비스 호출
			resultModel.setResult(adminService.insertOutsourcingCode(codeModel));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("insertOutsourcingCode() end...");
		
		return resultModel;
	}// end insertOutsourcingCode
	
	/**
	 * 외주구분 정보를 변경한다.
	 * @param codeModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/updateOutsourcingCode")
	public ResultModel updateOutsourcingCode(@RequestBody List<CodeModel> codeModels) throws ServletException{
		System.out.println("updateOutsourcingCode() start...");		
		
		ResultModel resultModel = new ResultModel();
		
		try{	
			
			// 서비스 호출
			resultModel.setResult(adminService.updateOutsourcingCode(codeModels));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("updateOutsourcingCode() end...");
		
		return resultModel;
	}// end updateOutsourcingCode
	
	/**
	 * 외주구분 정보를 삭제한다.
	 * @param codeModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/deleteOutsourcingCode")
	public ResultModel deleteOutsourcingCode(@RequestBody List<CodeModel> codeModels) throws ServletException{
		System.out.println("deleteOutsourcingCode() start...");		
		
		ResultModel resultModel = new ResultModel();
		
		try{	
			// 서비스 호출
			resultModel.setResult(adminService.deleteOutsourcingCode(codeModels));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("deleteOutsourcingCode() end...");
		
		return resultModel;
	}// end deleteOutsourcingCode
	
	
	/******************************************** TB_OUTSOURCINGCODE ****************************************************/	
	
	/**
	 * 등급 정보를 조회한다.
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/selectRatingCode")
	public List<RatingCodeModel> selectRatingCode() throws ServletException{
		System.out.println("selectRatingCode() start...");
		
		List<RatingCodeModel> ratingCodeModels = new ArrayList<>();
		
		try{	
			// 서비스 호출
			ratingCodeModels = adminService.selectRatingCode();			
			
		}catch(Exception e)
		{	
			System.out.println("error : " + e.toString());
			throw new ServletException(e.toString());
		}	
		
		System.out.println("selectRatingCode() end...");
		
		return ratingCodeModels;		
	}// end selectRatingCode
	
	/**
	 * 등급 정보를 입력한다.
	 * @param codeModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/insertRatingCode")
	public ResultModel insertRatingCode(@RequestBody CodeModel codeModel) throws ServletException{
		System.out.println("insertRatingCode() start...");		
		
		ResultModel resultModel = new ResultModel();
		
		try{	
			
			// 서비스 호출
			resultModel.setResult(adminService.insertRatingCode(codeModel));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("insertRatingCode() end...");
		
		return resultModel;
	}// end insertRatingCode
	
	/**
	 * 등급 정보를 변경한다.
	 * @param codeModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/updateRatingCode")
	public ResultModel updateRatingCode(@RequestBody List<CodeModel> codeModels) throws ServletException{
		System.out.println("updateRatingCode() start...");		
		
		ResultModel resultModel = new ResultModel();
		
		try{	
			
			// 서비스 호출
			resultModel.setResult(adminService.updateRatingCode(codeModels));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("updateRatingCode() end...");
		
		return resultModel;
	}// end updateRatingCode
	
	/**
	 * 등급 정보를 삭제한다.
	 * @param codeModel
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping("/deleteRatingCode")
	public ResultModel deleteRatingCode(@RequestBody List<CodeModel> codeModels) throws ServletException{
		System.out.println("deleteRatingCode() start...");		
		
		ResultModel resultModel = new ResultModel();
		
		try{	
			// 서비스 호출
			resultModel.setResult(adminService.deleteRatingCode(codeModels));			
			
		}catch(Exception e)
		{			
			resultModel.setMessage(e.toString());
			
			System.out.println("error : " + e.toString());
			//throw new ServletException(e.toString());
		}
		
		System.out.println("deleteRatingCode() end...");
		
		return resultModel;
	}// end deleteRatingCode
	
}
