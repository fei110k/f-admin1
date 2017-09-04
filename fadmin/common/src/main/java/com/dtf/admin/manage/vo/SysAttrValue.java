
package com.dtf.admin.manage.vo;

import java.io.Serializable;

public class SysAttrValue implements Serializable{
	
	private String attr_id;
	private String attr_value_id;
	private String attr_value;
	private String attr_value_name;


	public String getAttr_id(){
		return attr_id;
	}

	public void setAttr_id(String attr_id){
		this.attr_id = attr_id;
	}

	public String getAttr_value_id(){
		return attr_value_id;
	}

	public void setAttr_value_id(String attr_value_id){
		this.attr_value_id = attr_value_id;
	}

	public String getAttr_value(){
		return attr_value;
	}

	public void setAttr_value(String attr_value){
		this.attr_value = attr_value;
	}

	public String getAttr_value_name(){
		return attr_value_name;
	}

	public void setAttr_value_name(String attr_value_name){
		this.attr_value_name = attr_value_name;
	}


}