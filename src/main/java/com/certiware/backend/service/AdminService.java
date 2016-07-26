package com.certiware.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certiware.backend.mapper.AdminMapper;
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
import com.certiware.backend.model.common.UserModel;

@Service
public class AdminService {
	
	@Autowired
	AdminMapper adminMapper;
	@Autowired
	CommonService commonService;
	
	/**
	 * TB_USER 테이블(리스트 조회)
	 * @return
	 * @throws Exception
	 */
	public List<SelectUserListModel> selectUserList(SelectUserListModel selectUserListModel) throws Exception{
		return adminMapper.selectUserList(selectUserListModel);
	}
	
	/**
	 * TB_USER 테이블 조회(단건 조회)
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserModel selectUserDetail(UserModel userModel) throws Exception{
		return adminMapper.selectUserByPK(userModel);
	}
	
	/**
	 * TB_USER 테이블 입력
	 * @param userModel
	 * @throws Exception
	 */
	public boolean insertUser(UserModel userModel) throws Exception{
		adminMapper.insertUser(userModel);
		return true;
	}
	
	/**
	 * TB_USER 테이블 변경
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	public boolean updateUser(UpdateUserModel updateUserModel) throws Exception{
		
		adminMapper.updateUser(updateUserModel);
		
		return true;
	}
	
	/**
	 * TB_USER 테이블 삭제
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean deleteUser(String userId) throws Exception{
		adminMapper.deleteUserByUserId(userId);
		
		return true;
	}
	
	/******************************************** TB_DEPTCODE ****************************************************/
	
	/**
	 * TB_DEPTCODE 조회
	 * @return
	 * @throws Exception
	 */
	public List<DeptCodeModel> SelectDeptCode() throws Exception{
		return commonService.SelectDeptCode();
	}	
	/**
	 * 부서를 입력한다.
	 * @param deptName
	 * @return
	 * @throws Exception
	 */
	public boolean insertDeptCode(String deptName) throws Exception{
		
		adminMapper.insertDeptCode(deptName);
		
		return true;
	}
	
	/**
	 * 부서를 변경한다.
	 * @param deptCodeModels
	 * @return
	 * @throws Exception
	 */
	public boolean updateDeptCode(List<DeptCodeModel> deptCodeModels) throws Exception{
		
		
		// 반복시켜준다.
		for (DeptCodeModel deptCodeModel : deptCodeModels) {
			
			adminMapper.updateDeptCode(deptCodeModel);			
		}	
		
		return true;
		
	}
	
	/**
	 * 부서를 삭제한다.
	 * 삭제하기전 TB_USER와 TB_PROJECT의 테이블명을 변경한다.
	 * @param deptCodeModels
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean deleteDeptCode(List<DeleteDeptCodeModel> deptCodeModels) throws Exception{
		
		for (DeleteDeptCodeModel deleteDeptCodeModel : deptCodeModels) {
			
			// 유저테이블 업데이트
			adminMapper.updateUserDeptCode(deleteDeptCodeModel);
			
			// 프로젝트테이블 업데이트
			adminMapper.updateProjectDeptCode(deleteDeptCodeModel);
			
			// 부서 테이블 삭제
			adminMapper.deleteDeptCodeByPK(deleteDeptCodeModel);
			
		}
		
		return true;
	}
	
	/******************************************** TB_RANK ****************************************************/
	
	/**
	 * 직급코드를 조회한다.
	 * @return
	 * @throws Exception
	 */
	public List<RankCodeModel> selectRankCode() throws Exception{
		
		// 서비스 호출 및 결과 리턴
		return commonService.SelectRankCode();
	}
		
	/**
	 * 직급코드를 입력한다. 
	 * @param codeModel
	 * @return
	 * @throws Exception
	 */
	public boolean insertRankCode(CodeModel codeModel) throws Exception{
		
		// insert
		adminMapper.insertRankCode(codeModel);		
		
		// 결과 리턴(에러시 Exception을 던지고 Controller에서 false 세팅
		return true;
	}
	
	/**
	 * 직급명을 변경한다.
	 * @param codeModel
	 * @return
	 * @throws Exception
	 */
	public boolean updateRankCode(List<CodeModel> codeModels) throws Exception{

		for (CodeModel codeModel : codeModels) {
			
			// update
			adminMapper.updateRankCode(codeModel);
			
		}			
		
		// 결과 리턴(에러시 Exception을 던지고 Controller에서 false 세팅
		return true;
	}
	
	/**
	 * 직급코드를 삭제한다.
	 * 삭제 이전에 참조하는 테이블의 코드 값을 다른 코드로 변경한다.
	 * @param codeModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean deleteRankCode(List<CodeModel> codeModels) throws Exception{
		
		for (CodeModel codeModel : codeModels) {
			
			// update
			adminMapper.updateUserRankCode(codeModel);
			
			// delete
			adminMapper.deleteRankCode(codeModel);
			
		}		
		
		// 결과 리턴(에러시 Exception을 던지고 Controller에서 false 세팅
		return true;
	}
	
	
	/******************************************** TB_BUSINESSCODE ****************************************************/
	
	/**
	 * 거래처분류 코드를 조회한다.
	 * @return
	 * @throws Exception
	 */
	public List<BusinessCodeModel> selectBusinessCode() throws Exception{
		
		// 서비스 호출 및 결과 리턴
		return commonService.SelectBusinessCode();
	}
	
	/**
	 * 거래처분류 코드를 입력한다.
	 * @param codeModel
	 * @return
	 * @throws Exception
	 */
	public boolean insertBusinessCode(CodeModel codeModel) throws Exception{
		
		// insert
		adminMapper.insertBusinessCode(codeModel);
				
		// 결과 리턴(에러시 Exception을 던지고 Controller에서 false 세팅
		return true;
	}
	
	/**
	 * 거래처분류 코드를 변경한다.
	 * @param codeModels
	 * @return
	 * @throws Exception
	 */
	public boolean updateBusinessCode(List<CodeModel> codeModels) throws Exception{
		
		
		for (CodeModel codeModel : codeModels) {
			
			// update
			adminMapper.updateBusinessCode(codeModel);
		}		
		
		// 결과 리턴(에러시 Exception을 던지고 Controller에서 false 세팅
		return true;
	}
	
	/**
	 * 거래처분류 코드를 삭제한다
	 * 삭제 이전에 참조하는 테이블의 코드 값을 다른 코드로 변경한다. 
	 * @param codeModels
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean deleteBusinessCode(List<CodeModel> codeModels) throws Exception{
				
		for (CodeModel codeModel : codeModels) {
			
			// update
			adminMapper.updatePartnerBusinessCode(codeModel);
			
			// delete
			adminMapper.deleteBusinessCode(codeModel);			
							
		}		
		
		// 결과 리턴(에러시 Exception을 던지고 Controller에서 false 세팅
		return true;
	}
	
	
	/******************************************** TB_PARTNERCODE ****************************************************/
	
	/**
	 * 거래처성격(자사,법인 등) 코드값을 조회한다.
	 * @return
	 * @throws Exception
	 */
	public List<PartnerCodeModel> selectPartnerCode() throws Exception{
		
		// 서비스 호출 및 결과 리턴
		return commonService.SelectPartnerCode();
	}
	
	/**
	 * 거래처성격(자사,법인 등) 코드를 입력한다.
	 * @param codeModel
	 * @return
	 * @throws Exception
	 */
	public boolean insertPartnerCode(CodeModel codeModel) throws Exception{
		
		// insert
		adminMapper.insertPartnerCode(codeModel);
		
		// 결과 리턴(에러시 Exception을 던지고 Controller에서 false 세팅
		return true;
	}
	
	/**
	 * 거래처성격(자사,법인 등) 코드를 변경한다.
	 * @param codeModels
	 * @return
	 * @throws Exception
	 */
	public boolean updatePartnerCode(List<CodeModel> codeModels) throws Exception{
				                                      
		for (CodeModel codeModel : codeModels) {
			
			// update
			adminMapper.updatePartnerCode(codeModel);
					                                  
		}  		
		
		// 결과 리턴(에러시 Exception을 던지고 Controller에서 false 세팅
		return true;
	}
	
	/**
	 * 거래처성격(자사,법인 등) 코드를 삭제한다.
	 * 삭제 이전에 참조하는 테이블의 코드 값을 다른 코드로 변경한다.
	 * @param codeModels
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean deletePartnerCode(List<CodeModel> codeModels) throws Exception{		
                                    
		for (CodeModel codeModel : codeModels) {
			
			// update
			adminMapper.updatePartnerPartnerCode(codeModel);
			
			// delete
			adminMapper.deletePartnerCode(codeModel);
					                                  
		}  		
		
		// 결과 리턴(에러시 Exception을 던지고 Controller에서 false 세팅
		return true;
	}
	
	
	/******************************************** TB_OUTSOURCINGCODE ****************************************************/
	
	/**
	 * 외주구분 코드를 조회한다.
	 * @return
	 * @throws Exception
	 */
	public List<OutsourcingCodeModel> selectOutsourcingCode() throws Exception{
		
		// 서비스 호출 및 결과 리턴
		return commonService.SelectOutsourcingCode();
	}
	
	/**
	 * 외주구분 코드를 입력한다.
	 * @param codeModel
	 * @return
	 * @throws Exception
	 */
	public boolean insertOutsourcingCode(CodeModel codeModel) throws Exception{
		
		// insert
		adminMapper.insertOutsourcingCode(codeModel);
		
		// 결과 리턴(에러시 Exception을 던지고 Controller에서 false 세팅
		return true;
	}
	
	/**
	 * 외주구분 코드를 변경한다.
	 * @param codeModels
	 * @return
	 * @throws Exception
	 */
	public boolean updateOutsourcingCode(List<CodeModel> codeModels) throws Exception{
		                                    
		for (CodeModel codeModel : codeModels) {
			
			// update
			adminMapper.updateOutsourcingCode(codeModel);
					                                  
		}  		
		
		// 결과 리턴(에러시 Exception을 던지고 Controller에서 false 세팅
		return true;
	}
	
	/**
	 * 외주구분 코드를 삭제한다.
	 * 삭제 이전에 참조하는 테이블의 코드 값을 다른 코드로 변경한다.
	 * @param codeModels
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean deleteOutsourcingCode(List<CodeModel> codeModels) throws Exception{
		                                    
		for (CodeModel codeModel : codeModels) {
			
			// update
			adminMapper.updateOutsourcingOutsourcingCode(codeModel);
			
			// delete
			adminMapper.deleteOutsourcingCode(codeModel);
					                                  
		}  		
		
		// 결과 리턴(에러시 Exception을 던지고 Controller에서 false 세팅
		return true;
	}
	
	
	/******************************************** TB_RATINGCODE ****************************************************/
	
	/**
	 * 등급 코드를 조회한다.
	 * @return
	 * @throws Exception
	 */
	public List<RatingCodeModel> selectRatingCode() throws Exception{
		
		// 서비스 호출 및 결과 리턴
		return commonService.SelectRatingCode();
	}
	
	/**
	 * 등급 코드를 입력한다.
	 * @param codeModel
	 * @return
	 * @throws Exception
	 */
	public boolean insertRatingCode(CodeModel codeModel) throws Exception{
		
		// insert
		adminMapper.insertRatingCode(codeModel);
		
		// 결과 리턴(에러시 Exception을 던지고 Controller에서 false 세팅
		return true;
	}
	
	/**
	 * 등급 코드를 변경한다.
	 * @param codeModels
	 * @return
	 * @throws Exception
	 */
	public boolean updateRatingCode(List<CodeModel> codeModels) throws Exception{
		 
		for (CodeModel codeModel : codeModels) {
			
			// update
			adminMapper.updateRatingCode(codeModel);
					                                  
		}  	
		
		// 결과 리턴(에러시 Exception을 던지고 Controller에서 false 세팅
		return true;
	}
	
	/**
	 * 등급 코드를 삭제한다.
	 * 삭제 이전에 참조하는 테이블의 코드 값을 다른 코드로 변경한다.
	 * @param codeModels
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean deleteRatingCode(List<CodeModel> codeModels) throws Exception{
		                                      
		for (CodeModel codeModel : codeModels) {
			
			// update
			adminMapper.updateManpowerRatingCode(codeModel);
			
			// update
			adminMapper.updateUnitPriceRatingCode(codeModel);
			
			// delete
			adminMapper.deleteRatingCode(codeModel);
					                                  
		}  		
		
		// 결과 리턴(에러시 Exception을 던지고 Controller에서 false 세팅
		return true;
	}
	
}
