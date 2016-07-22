package com.certiware.backend.model.common;

public class ResultModel {
	
	private boolean result=false;	// 연동결과
	private String message;			// 메세지(실패시 오류메세지 담는다)
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
