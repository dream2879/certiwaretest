package com.certiware.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.certiware.backend.mapper.AdminMapper;
import com.certiware.backend.model.admin.ModifyDeptCodeModel;
import com.certiware.backend.model.admin.SelectUserListModel;
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
	public boolean updateUser(UserModel userModel) throws Exception{
		
		adminMapper.updateUser(userModel);
		
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
	 * TB_DEPTCODE 테이블의 MERGE, DELETE 문수행
	 * @param modifyDeptCodeModel
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean modifyDeptCode(ModifyDeptCodeModel modifyDeptCodeModel) throws Exception{
		
		// merge
		for (DeptCodeModel deptCodeModel : modifyDeptCodeModel.getMergeDeptCodeModels()) {
			adminMapper.mergeDeptCode(deptCodeModel);
		}
		
		// delete
		for (DeptCodeModel deptCodeModel : modifyDeptCodeModel.getDeleteDeptCodeModels()) {
			adminMapper.deleteDeptCodeByPK(deptCodeModel);
		}	
		
		return true;
	}
	
}
