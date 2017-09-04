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

import com.dtf.admin.common.ReturnVO;
import com.dtf.admin.common.cache.SysAttrCache;
import com.dtf.admin.common.utils.MD5Utils;
import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.common.utils.manage.SessionUtil;
import com.dtf.admin.manage.service.SysAttrBo;
import com.dtf.admin.manage.vo.StaffUser;
import com.dtf.admin.manage.vo.SysAttr;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/SysAttr")
public class SysAttrController {
	
	Log log = LogFactory.getLog(SysAttrController.class);
	
	@Autowired
	private SysAttrBo sysAttrBo;
	
	@ResponseBody
	@RequestMapping(value="/findSysAttrByCode")
	public SysAttr findSysAttrByCode(@RequestParam Map param){
		String code = MapUtils.getString(param, "data_code");
		SysAttr attr = SysAttrCache.getSysAttr(code);
		return attr;
	}
	
	@ResponseBody
	@RequestMapping(value="/findSysAttrByCodes")
	public List<SysAttr> findSysAttrByCodes(@RequestParam Map param){
		String codestr = MapUtils.getString(param, "data_codes");
		String[] codes = codestr.split(",");
		List<SysAttr> attrs = new ArrayList<SysAttr>();
		for (int i = 0; i < codes.length; i++) {
			SysAttr attr = SysAttrCache.getSysAttr(codes[i]);
			attrs.add(attr);
		}
		return attrs;
	}
	
	@ResponseBody
	@RequestMapping(value="/findSysAttrById")
	public Map findSysAttrById(@RequestParam Map param){
		return sysAttrBo.findSysAttrById(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/findSysAttr")
	public PageInfo findSysAttr(@RequestParam Map param){
		return sysAttrBo.findSysAttr(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/insertSysAttr")
	public ReturnVO insertSysAttr(@RequestParam Map param){
		return sysAttrBo.insertSysAttr(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateSysAttrByConn")
	public ReturnVO updateSysAttrByConn(@RequestParam Map param){
		return sysAttrBo.updateSysAttrByConn(param);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteSysAttrById")
	public ReturnVO deleteSysAttrById(@RequestParam Map param){
		return sysAttrBo.deleteSysAttrById(param);
	}
	
}
