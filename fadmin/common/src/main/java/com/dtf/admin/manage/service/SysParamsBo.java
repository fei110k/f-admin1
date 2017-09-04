package com.dtf.admin.manage.service;

import java.util.Map;

import com.dtf.admin.common.ReturnVO;
import com.github.pagehelper.PageInfo;

public interface SysParamsBo {
	
	public PageInfo findSysParams(Map param);
	
	public ReturnVO insertSysParams(Map param);
	
	public ReturnVO updateSysParamsByConn(Map param);
	
	public ReturnVO deleteSysParamsById(Map param);
	
	public Map findSysParamsById(Map param);
	
}
