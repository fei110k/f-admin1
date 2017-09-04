package com.dtf.admin.manage.service;

import java.util.Map;

import com.dtf.admin.common.ReturnVO;
import com.github.pagehelper.PageInfo;

public interface SysStaffBo {
	
	public PageInfo findSysStaff(Map param);
	
	public ReturnVO insertSysStaff(Map param);
	
	public ReturnVO updateSysStaffByConn(Map param);
	
	public ReturnVO deleteSysStaffById(Map param);
	
	public ReturnVO deleteSysStaffByIds(Map param);

	public Map findSysStaffById(Map param);
	/**
	 * 修改用户密码
	 * @param param
	 * @return
	 */
	public ReturnVO updateSysStaffPassword(Map param);
	
	/**
	 * 用户所关联的角色
	 * @param param
	 * @return
	 */
	public PageInfo findStaffRole(Map param);
	
	/**
	 * 删除用户所关联的角色
	 * @param param
	 * @return
	 */
	public ReturnVO deleteStaffRole(Map param);

	/**
	 * 添加用户所关联的角色
	 * @param param
	 * @return
	 */
	public ReturnVO insertStaffRole(Map param);
}
