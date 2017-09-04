package com.dtf.admin.manage.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dtf.admin.common.ReturnVO;
import com.dtf.admin.manage.service.SysOrgBo;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/SysOrg")
public class SysOrgController {
	
	Log log = LogFactory.getLog(SysOrgController.class);
	
	@Autowired
	private SysOrgBo sysOrgBo;
	
	@ResponseBody
	@RequestMapping(value="/findSysOrg")
	public List findSysOrg(@RequestParam Map param){
		return sysOrgBo.findSysOrg(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/findSysOrgById")
	public Map findSysOrgById(@RequestParam Map param){
		return sysOrgBo.findSysOrgById(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/insertSysOrg")
	public ReturnVO insertSysOrg(@RequestParam Map param){
		return sysOrgBo.insertSysOrg(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateSysOrgByConn")
	public ReturnVO updateSysOrgByConn(@RequestParam Map param){
		return sysOrgBo.updateSysOrgByConn(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteSysOrgById")
	public ReturnVO deleteSysOrgById(@RequestParam Map param){
		return sysOrgBo.deleteSysOrgById(param);
	}
	
}
