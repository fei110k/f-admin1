
package com.dtf.admin.manage.vo;

import java.io.Serializable;

public class SysMenu implements Serializable{
	
	//菜单ID
	private String menu_id;
	//菜单显示名称
	private String menu_name;
	//菜单链接
	private String menu_url;
	//菜单路径：父菜单ID.自己ID
	private String menu_path;
	//父节点ID
	private String parent_id;
	//0有效；1无效
	private String status;
	//排序
	private String sort_order;
	//菜单样式，可以是icon图标
	private String class_style;
	//菜单级别
	private String menu_level;


	public String getMenu_id(){
		return menu_id;
	}

	public void setMenu_id(String menu_id){
		this.menu_id = menu_id;
	}

	public String getMenu_name(){
		return menu_name;
	}

	public void setMenu_name(String menu_name){
		this.menu_name = menu_name;
	}

	public String getMenu_url(){
		return menu_url;
	}

	public void setMenu_url(String menu_url){
		this.menu_url = menu_url;
	}

	public String getMenu_path(){
		return menu_path;
	}

	public void setMenu_path(String menu_path){
		this.menu_path = menu_path;
	}

	public String getParent_id(){
		return parent_id;
	}

	public void setParent_id(String parent_id){
		this.parent_id = parent_id;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getSort_order(){
		return sort_order;
	}

	public void setSort_order(String sort_order){
		this.sort_order = sort_order;
	}

	public String getClass_style(){
		return class_style;
	}

	public void setClass_style(String class_style){
		this.class_style = class_style;
	}

	public String getMenu_level(){
		return menu_level;
	}

	public void setMenu_level(String menu_level){
		this.menu_level = menu_level;
	}


}