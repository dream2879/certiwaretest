package com.certiware.backend.model.common;

public class RoleCodeModel {
	
	private String roleCode;	// 권한코드
	private String description;	// 권한설명
	private int priority;		// 우선순위(정렬)
	
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
}
