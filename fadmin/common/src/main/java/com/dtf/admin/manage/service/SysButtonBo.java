package com.dtf.admin.manage.service;

import java.util.List;
import java.util.Map;

import com.dtf.admin.common.ReturnVO;
import com.github.pagehelper.PageInfo;

public interface SysButtonBo {
	
	public Map findSysButtonById(Map param);
	
	public ReturnVO updateSysButtonByConn(Map param);
	
	public ReturnVO deleteSysButtonById(Map param);

	public ReturnVO insertSysButton(Map param);
	
}
