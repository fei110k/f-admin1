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
import com.dtf.admin.manage.service.SysMenuBo;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/SysMenu")
public class SysMenuController {
	
	Log log = LogFactory.getLog(SysMenuController.class);
	
	@Autowired
	private SysMenuBo sysMenuBo;
	
	@ResponseBody
	@RequestMapping(value="/findMenuBtns")
	public List findMenuBtns(@RequestParam Map param){
		return sysMenuBo.findMenuBtns(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/findSysMenu")
	public PageInfo findSysMenu(@RequestParam Map param){
		return sysMenuBo.findSysMenu(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/findSysMenuById")
	public Map findSysMenuById(@RequestParam Map param){
		return sysMenuBo.findSysMenuById(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/insertSysMenu")
	public ReturnVO insertSysMenu(@RequestParam Map param){
		return sysMenuBo.insertSysMenu(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateSysMenuByConn")
	public ReturnVO updateSysMenuByConn(@RequestParam Map param){
		return sysMenuBo.updateSysMenuByConn(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteSysMenuById")
	public ReturnVO deleteSysMenuById(@RequestParam Map param){
		return sysMenuBo.deleteSysMenuById(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteSysMenuByIds")
	public ReturnVO deleteSysMenuByIds(@RequestParam Map param){
		return sysMenuBo.deleteSysMenuByIds(param);
	}
	
}
