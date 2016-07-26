package com.certiware.backend.model.admin;


/**
 * 코드성 테이블의 DML문 쿼리시 사용되는 조건값들을 담는 공통 모델 정의
 * @author K
 *
 */
public class CodeModel {
	
	private String code;	// 코드값
	private String name;	// 코드이름 or 설명
	private int priority;	// 우선순위
	private String pk;		// 해당 테이블에서 pk
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	
	
	

}
