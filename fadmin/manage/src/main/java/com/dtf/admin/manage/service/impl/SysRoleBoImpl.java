package com.dtf.admin.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dtf.admin.common.Consts;
import com.dtf.admin.common.ReturnVO;
import com.dtf.admin.common.utils.ListUtils;
import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.common.utils.UUIDGenerator;
import com.dtf.admin.common.utils.mybatis.DaoUtils;
import com.dtf.admin.manage.service.SysRoleBo;
import com.github.pagehelper.PageInfo;

@Service
public class SysRoleBoImpl implements SysRoleBo{
	
	private static final Log log = LogFactory.getLog(SysRoleBoImpl.class);
	
	@Autowired
	private DaoUtils daoUtils;
	
	@Override
	public Map findSysRoleById(Map param) {
		// TODO Auto-generated method stub
		return daoUtils.getSqlSessionTemplate().selectOne("SysRole.findSysRoleById", param);
	}
	
	@Override
	public PageInfo findSysRole(Map param) {
		// TODO Auto-generated method stub
		String role_name = MapUtils.getString(param, "role_name");
		param.put("role_name", "%"+role_name+"%");
		return daoUtils.selectPage("SysRole.findSysRole", param);
	}
	
	@Override
	public ReturnVO insertSysRole(Map param) {
		ReturnVO req = new ReturnVO();
		
		List list = daoUtils.getSqlSessionTemplate().selectList("SysRole.findSysRoleByName", param);
		if (ListUtils.isNotEmpty(list)) {
			req.setCode("9999");
			req.setMsg("角色名称重复，请修改角色名称后再重新提交！");
			return req;
		}
		
		String role_id = UUIDGenerator.getInst().getUUID32();
		param.put("role_id", role_id);
		int update_count = daoUtils.getSqlSessionTemplate().insert("SysRole.insertSysRole", param);
		
		return req;
	}

	@Override
	public ReturnVO updateSysRoleById(Map param) {
		ReturnVO req = new ReturnVO();
		
		List<Map> list = daoUtils.getSqlSessionTemplate().selectList("SysRole.findSysRoleByName", param);
		if ((ListUtils.isNotEmpty(list) && list.size() > 1 )||
				(ListUtils.isNotEmpty(list) 
						&& !MapUtils.getString(param, "role_id").equals(MapUtils.getString(list.get(0), "role_id")))) {
			req.setCode("9999");
			req.setMsg("角色名称重复，请修改角色名称后再重新提交！");
			return req;
		}
		
		int update_count = daoUtils.getSqlSessionTemplate().update("SysRole.updateSysRoleById", param);
		return req;
	}
	
	@Override
	public ReturnVO deleteSysRoleById(Map param) {
		ReturnVO req = new ReturnVO();
		int update_count = daoUtils.getSqlSessionTemplate().delete("SysRole.deleteSysRoleById", param);
		return req;
	}

	@Override
	public List findRolePrivilege(Map param) {
		return daoUtils.getSqlSessionTemplate().selectList("SysRole.findRolePrivilege", param);
	}

	@Override
	public ReturnVO editRolePrivilege(Map param) {
		ReturnVO req = new ReturnVO();
		String json = MapUtils.getString(param, "json");
		json = json.replaceAll("＇","\'").replaceAll("＂","\"");
		List<Map> list = JSON.parseArray(json, Map.class);
		
		String role_id = MapUtils.getString(param, "role_id");
		Map map = new HashMap();
		map.put("role_id", role_id);
		int update_count = daoUtils.getSqlSessionTemplate().delete("SysRole.deleteRolePrivilege", map);
		
		for (int i = 0; i < list.size(); i++) {
			Map paramMap = list.get(i);
			Map insertMap = new HashMap();
			String privilege_rel_id = UUIDGenerator.getInst().getUUID32();
			insertMap.put("privilege_rel_id", privilege_rel_id);
			insertMap.put("privilege_type", MapUtils.getString(paramMap, "type"));
			insertMap.put("privilege_obj_id", MapUtils.getString(paramMap, "id"));
			insertMap.put("role_id", role_id);
			daoUtils.getSqlSessionTemplate().insert("SysRole.addRolePrivilege", insertMap);
		}
		return req;
	}
	
}
