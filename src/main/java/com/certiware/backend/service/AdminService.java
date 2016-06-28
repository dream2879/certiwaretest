package com.certiware.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SelectUserListModel> selectUserList(SelectUserListModel selectUserListModel) throws Exception{
		return adminMapper.selectUserList(selectUserListModel);
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserModel selectUserDetail(UserModel userModel) throws Exception{
		return adminMapper.selectUserByPK(userModel);
	}
	
	/**
	 * 
	 * @param userModel
	 * @throws Exception
	 */
	public void insertUser(UserModel userModel) throws Exception{
		adminMapper.insertUser(userModel);
	}
	
	/**
	 * 
	 * @param userModel
	 * @return
	 * @throws Exception
	 */
	public int updateUser(UserModel userModel) throws Exception{
		
		return adminMapper.updateUser(userModel);
		
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int deleteUser(String userId) throws Exception{
		return adminMapper.deleteUserByUserId(userId);
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<DeptCodeModel> SelectDeptCode() throws Exception{
		return commonService.SelectDeptCode();
	}
	
	/**
	 * 
	 * @param modifyDeptCodeModel
	 * @return
	 * @throws Exception
	 */
	public int modifyDeptCode(ModifyDeptCodeModel modifyDeptCodeModel) throws Exception{
		int result=0;
		
		result=+adminMapper.mergeDeptCode(modifyDeptCodeModel.getMergeDeptCodeModels());
		result=+adminMapper.deleteDeptCodeByPK(modifyDeptCodeModel.getDeptDeptCodeModels());
		
		return result;
	}
	
}
