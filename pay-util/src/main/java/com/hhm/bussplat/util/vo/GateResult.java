package com.hhm.bussplat.util.vo;

import java.io.Serializable;

import com.hhm.bussplat.util.util.JsonUtil;

import lombok.Data;

@Data
public class GateResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String return_code;
	
	private String return_msg;
	
	private static String SUCCESS = "SUCCESS";
	
	private static String FAIL = "FAIL";
	
	private static String SUCCCESS_DESC = "操作成功";
	
	private static String FAIL_DESC = "操作失败";
	
	public GateResult() {
	}
	
	public GateResult(String return_code,String return_msg) {
		this.return_code = return_code;
		this.return_msg = return_msg;
	
	}
	public static GateResult success() {
		return new GateResult(SUCCESS,SUCCCESS_DESC);
	}
	
	public static GateResult error() {
		return new GateResult(FAIL,FAIL_DESC);
	}
	
	public String toJson() {
		return JsonUtil.toJson(this);
	}
		
}
