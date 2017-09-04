package com.dtf.admin.common;

import java.io.Serializable;
import java.util.Map;

public class ReturnVO implements Serializable{
	
	public ReturnVO(){
		setStateCode(StateCode.STATE_0000);
	}
	
	/**
	 * 判断返回结果是否正确
	 * @return
	 */
	public boolean isSuccess(){
		return StateCode.STATE_0000[0].equals(this.code);
	}
	/**
	 * 数组必须包含两个值
	 * {code,msg}
	 * @param state
	 */
	public ReturnVO(String[] state){
		setStateCode(state);
	}
	
	public void setStateCode(String[] state){
		this.code = state[0];
		this.msg = state[1];
	}
	
	private String code;
	private String msg;
	private Object more;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public <E> E getMore() {
		return (E)more;
	}
	public void setMore(Object more) {
		this.more = more;
	}
	
}
