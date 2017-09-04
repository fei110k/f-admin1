package com.dtf.admin.manage.service;

import java.util.List;
import java.util.Map;

import com.dtf.admin.common.ReturnVO;
import com.github.pagehelper.PageInfo;

public interface SysMenuBo {
	
	public PageInfo findSysMenu(Map param);
	
	public ReturnVO insertSysMenu(Map param);
	
	public ReturnVO updateSysMenuByConn(Map param);
	
	public ReturnVO deleteSysMenuById(Map param);
	
	public ReturnVO deleteSysMenuByIds(Map param);

	public List findMenuBtns(Map param);

	public Map findSysMenuById(Map param);
	
}
