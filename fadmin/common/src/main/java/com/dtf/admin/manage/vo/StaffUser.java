package com.dtf.admin.manage.vo;

import java.util.List;

public class StaffUser {
	
	private SysStaff staff;
	
	//用户所拥有的菜单权限
	private List<SysMenu> menuList;
	//用户所拥有的按钮权限
	private List<String> btnList;
	
	//用户能访问的子菜单url列表
	private List<String> menuChildrenList;
	
	public SysStaff getStaff() {
		return staff;
	}

	public void setStaff(SysStaff staff) {
		this.staff = staff;
	}

	public List<SysMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<SysMenu> menuList) {
		this.menuList = menuList;
	}

	public List<String> getBtnList() {
		return btnList;
	}

	public void setBtnList(List<String> btnList) {
		this.btnList = btnList;
	}

	public List<String> getMenuChildrenList() {
		return menuChildrenList;
	}

	public void setMenuChildrenList(List<String> menuChildrenList) {
		this.menuChildrenList = menuChildrenList;
	}
	
}
