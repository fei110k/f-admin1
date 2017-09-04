package com.dtf.admin.manage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dtf.admin.common.ReturnVO;
import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.common.utils.manage.SessionUtil;
import com.dtf.admin.manage.service.SysRoleBo;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/SysRole")
public class SysRoleController {
	
	Log log = LogFactory.getLog(SysRoleController.class);
	
	@Autowired
	private SysRoleBo sysRoleBo;
	
	@ResponseBody
	@RequestMapping(value="/findSysRoleById")
	public Map findSysRoleById(@RequestParam Map param){
		return sysRoleBo.findSysRoleById(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/findSysRole")
	public PageInfo findSysRole(@RequestParam Map param){
		return sysRoleBo.findSysRole(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/insertSysRole")
	public ReturnVO insertSysRole(@RequestParam Map param){
		String staff_id = SessionUtil.getStaffUser().getStaff().getStaff_id();
		param.put("status_staff_id", staff_id);
		return sysRoleBo.insertSysRole(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateSysRoleById")
	public ReturnVO updateSysRoleById(@RequestParam Map param){
		String staff_id = SessionUtil.getStaffUser().getStaff().getStaff_id();
		param.put("status_staff_id", staff_id);
		return sysRoleBo.updateSysRoleById(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteSysRoleById")
	public ReturnVO deleteSysRoleById(@RequestParam Map param){
		return sysRoleBo.deleteSysRoleById(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/findRolePrivilege")
	public List findRolePrivilege(@RequestParam Map param){
		return sysRoleBo.findRolePrivilege(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/editRolePrivilege")
	public ReturnVO editRolePrivilege(@RequestParam Map param){
		return sysRoleBo.editRolePrivilege(param);
	}
}
