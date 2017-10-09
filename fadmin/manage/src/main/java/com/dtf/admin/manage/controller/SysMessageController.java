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
import com.dtf.admin.manage.service.SysMessageBo;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/SysMessage")
public class SysMessageController {
	
	Log log = LogFactory.getLog(SysMessageController.class);
	
	@Autowired
	private SysMessageBo sysMessageBo;
	
	@ResponseBody
	@RequestMapping(value="/findSysMessageById")
	public Map findSysMessageById(@RequestParam Map param){
		return sysMessageBo.findSysMessageById(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/findSysMessage")
	public PageInfo findSysMessage(@RequestParam Map param){
		param.put("staff_id", SessionUtil.getStaffUser().getStaff().getStaff_id());
		return sysMessageBo.findSysMessage(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/findStaffSysMessageCount")
	public Map findStaffSysMessageCount(@RequestParam Map param){
		param.put("staff_id", SessionUtil.getStaffUser().getStaff().getStaff_id());
		return sysMessageBo.findStaffSysMessageCount(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/insertSysMessage")
	public ReturnVO insertSysMessage(@RequestParam Map param){
		String staff_id = SessionUtil.getStaffUser().getStaff().getStaff_id();
		param.put("status_staff_id", staff_id);
		return sysMessageBo.insertSysMessage(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateSysMessageById")
	public ReturnVO updateSysMessageById(@RequestParam Map param){
		String staff_id = SessionUtil.getStaffUser().getStaff().getStaff_id();
		param.put("status_staff_id", staff_id);
		return sysMessageBo.updateSysMessageById(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteSysMessageById")
	public ReturnVO deleteSysMessageById(@RequestParam Map param){
		return sysMessageBo.deleteSysMessageById(param);
	}
}
