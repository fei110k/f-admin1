package com.dtf.admin.manage.service;

import java.util.List;
import java.util.Map;

import com.dtf.admin.common.ReturnVO;
import com.github.pagehelper.PageInfo;

public interface MainBo {
	
	/**
	 * 用户登录
	 * @param param
	 * @return
	 */
	public ReturnVO login(Map param);
	
	/**
	 * 获取用户菜单权限
	 * @param param
	 * @return
	 */
	public List getUserMenuPrivilege(Map param);
	
	/**
	 * 获取用户按钮权限
	 * @param param
	 * @return
	 */
	public List getUserButtonPrivilege(Map param);
}
