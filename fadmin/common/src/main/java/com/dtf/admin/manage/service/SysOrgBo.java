package com.dtf.admin.manage.service;

import java.util.List;
import java.util.Map;

import com.dtf.admin.common.ReturnVO;

public interface SysOrgBo {
	
	public List findSysOrg(Map param);
	
	public ReturnVO insertSysOrg(Map param);
	
	public ReturnVO updateSysOrgByConn(Map param);
	
	public ReturnVO deleteSysOrgById(Map param);

	public Map findSysOrgById(Map param);
	
}
