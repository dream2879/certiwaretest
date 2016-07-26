package com.certiware.backend.model.admin;

public class DeleteDeptCodeModel {
	
	private String updateDeptCode; 	// 삭제 할 부서를 변경할 부서코드
	private String deptCode; 		// 삭제 할 부서코드
	private String deptName;		// 부서명
	private int priority;			// 우선순위
	
	public String getUpdateDeptCode() {
		return updateDeptCode;
	}
	public void setUpdateDeptCode(String updateDeptCode) {
		this.updateDeptCode = updateDeptCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
	

}
