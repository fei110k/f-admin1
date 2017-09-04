
package com.dtf.admin.manage.vo;

import java.io.Serializable;

public class SysParams implements Serializable{
	
	//系统参数名
	private String param_code;
	//系统参数值
	private String param_val;
	//系统参数说明
	private String param_name;


	public String getParam_code(){
		return param_code;
	}

	public void setParam_code(String param_code){
		this.param_code = param_code;
	}

	public String getParam_val(){
		return param_val;
	}

	public void setParam_val(String param_val){
		this.param_val = param_val;
	}

	public String getParam_name(){
		return param_name;
	}

	public void setParam_name(String param_name){
		this.param_name = param_name;
	}


}