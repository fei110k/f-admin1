
package com.dtf.admin.manage.vo;

import java.io.Serializable;

public class SysOrg implements Serializable{
	
	//组织ID
	private String org_id;
	//组织名称
	private String org_name;
	//上级组织ID
	private String parent_id;
	//组织级别
	private String org_level;
	//状态：0有效；1无效
	private String status;
	//组织路径
	private String org_path;
	//是否是叶子菜单
	private String is_leaf;


	public String getOrg_id(){
		return org_id;
	}

	public void setOrg_id(String org_id){
		this.org_id = org_id;
	}

	public String getOrg_name(){
		return org_name;
	}

	public void setOrg_name(String org_name){
		this.org_name = org_name;
	}

	public String getParent_id(){
		return parent_id;
	}

	public void setParent_id(String parent_id){
		this.parent_id = parent_id;
	}

	public String getOrg_level(){
		return org_level;
	}

	public void setOrg_level(String org_level){
		this.org_level = org_level;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getOrg_path(){
		return org_path;
	}

	public void setOrg_path(String org_path){
		this.org_path = org_path;
	}

	public String getIs_leaf(){
		return is_leaf;
	}

	public void setIs_leaf(String is_leaf){
		this.is_leaf = is_leaf;
	}


}