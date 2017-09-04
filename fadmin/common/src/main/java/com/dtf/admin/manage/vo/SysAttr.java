
package com.dtf.admin.manage.vo;

import java.io.Serializable;
import java.util.List;

public class SysAttr implements Serializable{
	
	private String attr_id;
	private String attr_code;
	//0：有效；1：失效
	private String struts;
	private String attr_name;
	private String create_date;
	
	private List<SysAttrValue> attrValues;

	public String getAttr_id(){
		return attr_id;
	}

	public void setAttr_id(String attr_id){
		this.attr_id = attr_id;
	}

	public String getAttr_code(){
		return attr_code;
	}

	public void setAttr_code(String attr_code){
		this.attr_code = attr_code;
	}

	public String getStruts(){
		return struts;
	}

	public void setStruts(String struts){
		this.struts = struts;
	}

	public String getAttr_name(){
		return attr_name;
	}

	public void setAttr_name(String attr_name){
		this.attr_name = attr_name;
	}

	public String getCreate_date(){
		return create_date;
	}

	public void setCreate_date(String create_date){
		this.create_date = create_date;
	}

	public List<SysAttrValue> getAttrValues() {
		return attrValues;
	}

	public void setAttrValues(List attrValues) {
		this.attrValues = attrValues;
	}

}