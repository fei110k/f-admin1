package com.dtf.admin.manage.vo;

import java.util.List;

public class StaffUser {
	
	public SysStaff staff;
	
	public List<SysMenu> menuList;
	
	public List<SysButton> btnList;

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

	public List<SysButton> getBtnList() {
		return btnList;
	}

	public void setBtnList(List<SysButton> btnList) {
		this.btnList = btnList;
	}
	
	
}
