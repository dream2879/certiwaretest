package com.certiware.backend.model.main;

public class SelectMenuModel {
	
	private int mainMenuId;
	private String mainMenuName;
	private int subMenuId;
	private String subMenuName;
	private String requestURL;
	private String roleCode;
	private String showYN;
	public int getMainMenuId() {
		return mainMenuId;
	}
	public void setMainMenuId(int mainMenuId) {
		this.mainMenuId = mainMenuId;
	}
	public String getMainMenuName() {
		return mainMenuName;
	}
	public void setMainMenuName(String mainMenuName) {
		this.mainMenuName = mainMenuName;
	}
	public int getSubMenuId() {
		return subMenuId;
	}
	public void setSubMenuId(int subMenuId) {
		this.subMenuId = subMenuId;
	}
	public String getSubMenuName() {
		return subMenuName;
	}
	public void setSubMenuName(String subMenuName) {
		this.subMenuName = subMenuName;
	}
	public String getRequestURL() {
		return requestURL;
	}
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getShowYN() {
		return showYN;
	}
	public void setShowYN(String showYN) {
		this.showYN = showYN;
	}

}
