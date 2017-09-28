package com.dtf.admin.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.dtf.admin.common.ReturnVO;
import com.dtf.admin.common.cache.SysAttrCache;
import com.dtf.admin.common.utils.MD5Utils;
import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.common.utils.StringUtil;
import com.dtf.admin.common.utils.UUIDGenerator;
import com.dtf.admin.common.utils.mybatis.DaoUtils;
import com.dtf.admin.manage.service.SysAttrBo;
import com.github.pagehelper.PageInfo;

@Service
public class SysAttrBoImpl implements SysAttrBo{
	
	private static final Log log = LogFactory.getLog(SysAttrBoImpl.class);
	
	@Autowired
	private DaoUtils daoUtils;
	
	@Override
	public Map findSysAttrById(Map param) {
		Map map = daoUtils.getSqlSessionTemplate().selectOne("SysAttr.findSysAttr", param);
		List list = daoUtils.getSqlSessionTemplate().selectList("SysAttr.findSysAttrValue", map);
		map.put("attr_values", list);
		return map;
	}
	
	@Override
	public PageInfo findSysAttr(Map param) {
		// TODO Auto-generated method stub
		String attr_name = MapUtils.getString(param, "attr_name");
		if (StringUtil.isNotEmpty(attr_name)) {
			param.put("attr_name", "%"+attr_name+"%");
		}
		return daoUtils.selectPage("SysAttr.findSysAttr", param);
	}
	
	@Override
	@Transactional
	public ReturnVO insertSysAttr(Map param) {
		ReturnVO req = new ReturnVO();
		String attr_id = UUIDGenerator.getInst().getUUID32();
		param.put("attr_id", attr_id);
		int update_count = daoUtils.getSqlSessionTemplate().insert("SysAttr.insertSysAttr", param);
		
		String attr_values_str = MapUtils.getString(param, "attr_values");
		attr_values_str = attr_values_str.replaceAll("＂", "\"");
		List<Map> list = JSON.parseArray(attr_values_str, Map.class);
		for (int i = 0; i < list.size(); i++) {
			Map paramMap = list.get(i);
			paramMap.put("attr_id", attr_id);
			daoUtils.getSqlSessionTemplate().insert("SysAttr.insertSysAttrValue", paramMap);
		}
		
		return req;
	}

	@Override
	@Transactional
	public ReturnVO updateSysAttrByConn(Map param) {
		ReturnVO req = new ReturnVO();
		String attr_code = MapUtils.getString(param, "attr_code");
		int update_count = daoUtils.getSqlSessionTemplate().update("SysAttr.updateSysAttrByConn", param);
		
		String attr_id = MapUtils.getString(param, "attr_id");
		
		update_count = daoUtils.getSqlSessionTemplate().delete("SysAttr.deleteSysAttrValue", param);
		
		String attr_values_str = MapUtils.getString(param, "attr_values");
		attr_values_str = attr_values_str.replaceAll("＂", "\"");
		List<Map> list = JSON.parseArray(attr_values_str, Map.class);
		for (int i = 0; i < list.size(); i++) {
			Map paramMap = list.get(i);
			paramMap.put("attr_id", attr_id);
			daoUtils.getSqlSessionTemplate().insert("SysAttr.insertSysAttrValue", paramMap);
		}
		SysAttrCache.removeSysAttr(attr_code);
		return req;
	}
	
	@Override
	@Transactional
	public ReturnVO deleteSysAttrById(Map param) {
		ReturnVO req = new ReturnVO();
		Map map = daoUtils.getSqlSessionTemplate().selectOne("SysAttr.findSysAttr", param);
		int update_count = daoUtils.getSqlSessionTemplate().delete("SysAttr.deleteSysAttrById", param);
		update_count = daoUtils.getSqlSessionTemplate().delete("SysAttr.deleteSysAttrValue", param);
		SysAttrCache.removeSysAttr(MapUtils.getString(map, "attr_code"));
		return req;
	}

}
