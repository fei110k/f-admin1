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
import com.dtf.admin.manage.service.SysMessageBo;
import com.github.pagehelper.PageInfo;

@Service
public class SysMessageBoImpl implements SysMessageBo{
	
	private static final Log log = LogFactory.getLog(SysMessageBoImpl.class);
	
	@Autowired
	private DaoUtils daoUtils;
	
	@Override
	public Map findSysMessageById(Map param) {
		// TODO Auto-generated method stub
		return daoUtils.getSqlSessionTemplate().selectOne("SysMessage.findSysMessageById", param);
	}
	
	@Override
	public PageInfo findSysMessage(Map param) {
		// TODO Auto-generated method stub
		String role_name = MapUtils.getString(param, "role_name");
		param.put("role_name", "%"+role_name+"%");
		return daoUtils.selectPage("SysMessage.findSysMessage", param);
	}
	
	@Override
	public ReturnVO insertSysMessage(Map param) {
		ReturnVO req = new ReturnVO();
		
		List list = daoUtils.getSqlSessionTemplate().selectList("SysMessage.findSysMessageByName", param);
		if (ListUtils.isNotEmpty(list)) {
			req.setCode("9999");
			req.setMsg("角色名称重复，请修改角色名称后再重新提交！");
			return req;
		}
		
		String role_id = UUIDGenerator.getInst().getUUID32();
		param.put("role_id", role_id);
		int update_count = daoUtils.getSqlSessionTemplate().insert("SysMessage.insertSysMessage", param);
		
		return req;
	}

	@Override
	public ReturnVO updateSysMessageById(Map param) {
		ReturnVO req = new ReturnVO();
		
		List<Map> list = daoUtils.getSqlSessionTemplate().selectList("SysMessage.findSysMessageByName", param);
		if ((ListUtils.isNotEmpty(list) && list.size() > 1 )||
				(ListUtils.isNotEmpty(list) 
						&& !MapUtils.getString(param, "role_id").equals(MapUtils.getString(list.get(0), "role_id")))) {
			req.setCode("9999");
			req.setMsg("角色名称重复，请修改角色名称后再重新提交！");
			return req;
		}
		
		int update_count = daoUtils.getSqlSessionTemplate().update("SysMessage.updateSysMessageById", param);
		return req;
	}
	
	@Override
	public ReturnVO deleteSysMessageById(Map param) {
		ReturnVO req = new ReturnVO();
		int update_count = daoUtils.getSqlSessionTemplate().delete("SysMessage.deleteSysMessageById", param);
		return req;
	}
	
}
