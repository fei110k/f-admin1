package com.dtf.admin.manage.controller;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dtf.admin.common.ReturnVO;
import com.dtf.admin.common.utils.MD5Utils;
import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.common.utils.manage.SessionUtil;
import com.dtf.admin.manage.service.SysStaffBo;
import com.dtf.admin.manage.vo.StaffUser;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/SysStaff")
public class SysStaffController {
	
	Log log = LogFactory.getLog(SysStaffController.class);
	
	@Autowired
	private SysStaffBo sysStaffBo;
	
	@ResponseBody
	@RequestMapping(value="/findSysStaffById")
	public Map findSysStaffById(@RequestParam Map param){
		return sysStaffBo.findSysStaffById(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/getStaffUserInfo")
	public Map getStaffUserInfo(@RequestParam Map param){
		StaffUser user = SessionUtil.getStaffUser();
		param.put("staff_id", user.staff.getStaff_id());
		return sysStaffBo.findSysStaffById(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/findSysStaff")
	public PageInfo findSysStaff(@RequestParam Map param){
		return sysStaffBo.findSysStaff(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/insertSysStaff")
	public ReturnVO insertSysStaff(@RequestParam Map param){
		return sysStaffBo.insertSysStaff(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateSysStaffByConn")
	public ReturnVO updateSysStaffByConn(@RequestParam Map param){
		return sysStaffBo.updateSysStaffByConn(param);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/updateSysStaffPassword")
	public ReturnVO updateSysStaffPassword(@RequestParam Map param){
		ReturnVO returnVO = new ReturnVO();
		String password = SessionUtil.getStaffUser().staff.getPassword();
		String old_password = MapUtils.getString(param, "old_password");
		old_password = MD5Utils.MD5(old_password);
		if (!password.equals(old_password)) {
			returnVO.setCode("9999");
			returnVO.setMsg("原密码输入错误，请重新输入！");
			return returnVO;
		}
		param.put("staff_id", SessionUtil.getStaffUser().staff.getStaff_id());
		param.put("password", MD5Utils.MD5(MapUtils.getString(param, "new_password")));
		return sysStaffBo.updateSysStaffPassword(param);
	}
	@ResponseBody
	@RequestMapping(value="/deleteSysStaffById")
	public ReturnVO deleteSysStaffById(@RequestParam Map param){
		return sysStaffBo.deleteSysStaffById(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteSysStaffByIds")
	public ReturnVO deleteSysStaffByIds(@RequestParam Map param){
		return sysStaffBo.deleteSysStaffByIds(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/findStaffRole")
	public PageInfo findStaffRole(@RequestParam Map param){
		return sysStaffBo.findStaffRole(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteStaffRole")
	public ReturnVO deleteStaffRole(@RequestParam Map param){
		return sysStaffBo.deleteStaffRole(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/insertStaffRole")
	public ReturnVO insertStaffRole(@RequestParam Map param){
		return sysStaffBo.insertStaffRole(param);
	}
	
}
