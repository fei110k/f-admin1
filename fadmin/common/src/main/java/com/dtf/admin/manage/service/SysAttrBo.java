package com.dtf.admin.manage.service;

import java.util.Map;

import com.dtf.admin.common.ReturnVO;
import com.github.pagehelper.PageInfo;

public interface SysAttrBo {
	
	public PageInfo findSysAttr(Map param);
	
	public ReturnVO insertSysAttr(Map param);
	
	public ReturnVO updateSysAttrByConn(Map param);
	
	public ReturnVO deleteSysAttrById(Map param);
	
	public Map findSysAttrById(Map param);
	
}
