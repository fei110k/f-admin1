
package com.dtf.admin.manage.vo;

import java.io.Serializable;

public class SysRole implements Serializable{
	
	//角色ID
	private String role_id;
	//角色名称
	private String role_name;
	//角色说明
	private String role_desc;
	//变更人员
	private String status_staff_id;
	//变更时间
	private String status_date;


	public String getRole_id(){
		return role_id;
	}

	public void setRole_id(String role_id){
		this.role_id = role_id;
	}

	public String getRole_name(){
		return role_name;
	}

	public void setRole_name(String role_name){
		this.role_name = role_name;
	}

	public String getRole_desc(){
		return role_desc;
	}

	public void setRole_desc(String role_desc){
		this.role_desc = role_desc;
	}

	public String getStatus_staff_id(){
		return status_staff_id;
	}

	public void setStatus_staff_id(String status_staff_id){
		this.status_staff_id = status_staff_id;
	}

	public String getStatus_date(){
		return status_date;
	}

	public void setStatus_date(String status_date){
		this.status_date = status_date;
	}


}