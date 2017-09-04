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
import com.dtf.admin.common.cache.SysParamsCache;
import com.dtf.admin.common.utils.MD5Utils;
import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.common.utils.StringUtil;
import com.dtf.admin.common.utils.UUIDGenerator;
import com.dtf.admin.common.utils.mybatis.DaoUtils;
import com.dtf.admin.manage.service.SysParamsBo;
import com.github.pagehelper.PageInfo;

@Service
public class SysParamsBoImpl implements SysParamsBo{
	
	private static final Log log = LogFactory.getLog(SysParamsBoImpl.class);
	
	@Autowired
	private DaoUtils daoUtils;
	
	@Override
	public Map findSysParamsById(Map param) {
		Map map = daoUtils.getSqlSessionTemplate().selectOne("SysParams.findSysParams", param);
		return map;
	}
	
	@Override
	public PageInfo findSysParams(Map param) {
		// TODO Auto-generated method stub
		String param_name = MapUtils.getString(param, "param_name");
		if (StringUtil.isNotEmpty(param_name)) {
			param.put("param_name", "%"+param_name+"%");
		}
		return daoUtils.selectPage("SysParams.findSysParams", param);
	}
	
	@Override
	@Transactional
	public ReturnVO insertSysParams(Map param) {
		ReturnVO req = new ReturnVO();
		int update_count = daoUtils.getSqlSessionTemplate().insert("SysParams.insertSysParams", param);
		
		return req;
	}

	@Override
	@Transactional
	public ReturnVO updateSysParamsByConn(Map param) {
		ReturnVO req = new ReturnVO();
		String param_code = MapUtils.getString(param, "param_code");
		int update_count = daoUtils.getSqlSessionTemplate().update("SysParams.updateSysParamsByConn", param);
		
		SysParamsCache.removeSysParams(param_code);
		return req;
	}
	
	@Override
	@Transactional
	public ReturnVO deleteSysParamsById(Map param) {
		ReturnVO req = new ReturnVO();
		int update_count = daoUtils.getSqlSessionTemplate().delete("SysParams.deleteSysParamsById", param);
		SysParamsCache.removeSysParams(MapUtils.getString(param, "param_code"));
		return req;
	}

}
