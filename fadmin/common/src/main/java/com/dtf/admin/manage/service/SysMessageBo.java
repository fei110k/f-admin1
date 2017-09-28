package com.dtf.admin.manage.service;

import java.util.Map;

import com.dtf.admin.common.ReturnVO;
import com.github.pagehelper.PageInfo;

public interface SysMessageBo {
	
	public PageInfo findSysMessage(Map param);
	
	public ReturnVO insertSysMessage(Map param);
	
	public ReturnVO updateSysMessageById(Map param);
	
	public ReturnVO deleteSysMessageById(Map param);
	
	public Map findSysMessageById(Map param);
	
}
