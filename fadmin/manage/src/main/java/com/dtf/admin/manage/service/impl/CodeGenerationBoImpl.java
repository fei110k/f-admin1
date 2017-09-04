package com.dtf.admin.manage.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.common.utils.mybatis.DaoUtils;
import com.dtf.admin.manage.service.CodeGenerationBo;
import com.github.pagehelper.PageInfo;

@Service
public class CodeGenerationBoImpl implements CodeGenerationBo{
	
	private static final Log log = LogFactory.getLog(CodeGenerationBoImpl.class);
	
	@Resource
	private DaoUtils daoUtils;
	
	@Override
	public PageInfo queryAllTable(Map param) {
		String table_name = MapUtils.getString(param, "table_name");
		table_name = "%"+table_name+"%";
		param.put("table_name", table_name);
		return daoUtils.selectPage("CodeGeneration.queryAllTable", param);
	}

	@Override
	public List codeGeneration(Map param) {
		List<Map> list =  daoUtils.getSqlSessionTemplate().selectList("CodeGeneration.queryTableColumns", param);
		return list;
	}
	
}
