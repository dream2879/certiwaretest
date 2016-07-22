package com.certiware.backend.model.common;

public class DeptCodeModel {
	
	private String deptCode;	// 부서코드
	private String deptName;	// 부서이름
	private int priority;		// 우선순위(정렬)
	
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
