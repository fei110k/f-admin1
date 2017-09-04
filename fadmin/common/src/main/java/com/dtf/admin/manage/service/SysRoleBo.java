package com.dtf.admin.manage.service;

import java.util.List;
import java.util.Map;

import com.dtf.admin.common.ReturnVO;
import com.github.pagehelper.PageInfo;

public interface SysRoleBo {
	
	public PageInfo findSysRole(Map param);
	
	public ReturnVO insertSysRole(Map param);
	
	public ReturnVO updateSysRoleById(Map param);
	
	public ReturnVO deleteSysRoleById(Map param);
	
	public Map findSysRoleById(Map param);
	
	/**
	 * 查询角色所色所关联权限
	 * @param param
	 * @return
	 */
	public List findRolePrivilege(Map param);
	
	/**
	 * 删除用户所关联的角色
	 * @param param
	 * @return
	 */
	public ReturnVO editRolePrivilege(Map param);
}
