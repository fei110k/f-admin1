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
import com.dtf.admin.manage.service.SysButtonBo;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/SysButton")
public class SysButtonController {
	
	Log log = LogFactory.getLog(SysButtonController.class);
	
	@Autowired
	private SysButtonBo sysButtonBo;
	
	@ResponseBody
	@RequestMapping(value="/findSysButtonById")
	public Map findSysButtonById(@RequestParam Map param){
		return sysButtonBo.findSysButtonById(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/insertSysButton")
	public ReturnVO insertSysButton(@RequestParam Map param){
		return sysButtonBo.insertSysButton(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateSysButtonByConn")
	public ReturnVO updateSysButtonByConn(@RequestParam Map param){
		return sysButtonBo.updateSysButtonByConn(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteSysButtonById")
	public ReturnVO deleteSysButtonById(@RequestParam Map param){
		return sysButtonBo.deleteSysButtonById(param);
	}
	
}
