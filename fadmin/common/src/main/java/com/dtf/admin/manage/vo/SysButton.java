
package com.dtf.admin.manage.vo;

import java.io.Serializable;

public class SysButton implements Serializable{
	
	//按钮ID
	private String btn_id;
	//按钮HTML代码，可以写onclick事件等
	private String btn_html;
	//0:有效；1：无效
	private String status;
	private String btn_code;
	private String menu_id;
	private String btn_name;


	public String getBtn_id(){
		return btn_id;
	}

	public void setBtn_id(String btn_id){
		this.btn_id = btn_id;
	}

	public String getBtn_html(){
		return btn_html;
	}

	public void setBtn_html(String btn_html){
		this.btn_html = btn_html;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getBtn_code(){
		return btn_code;
	}

	public void setBtn_code(String btn_code){
		this.btn_code = btn_code;
	}

	public String getMenu_id(){
		return menu_id;
	}

	public void setMenu_id(String menu_id){
		this.menu_id = menu_id;
	}

	public String getBtn_name(){
		return btn_name;
	}

	public void setBtn_name(String btn_name){
		this.btn_name = btn_name;
	}


}