package com.huzan.zanbaseframe.base.data;

/**
 * Created by ZAN on 2017/7/19.
 * 数据返回基类
 */

public class BaseResponse<T> {  
	
	private int code;
	private String message;
	private T data;
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
}
