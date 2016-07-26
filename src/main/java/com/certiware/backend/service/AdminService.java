package com.certiware.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certiware.backend.mapper.AdminMapper;
import com.certiware.backend.model.admin.DeleteDeptCodeModel;
import com.certiware.backend.model.admin.SelectUserListModel;
import com.certiware.backend.model.admin.UpdateUserModel;
import com.certiware.backend.model.common.DeptCodeModel;
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
	
}
