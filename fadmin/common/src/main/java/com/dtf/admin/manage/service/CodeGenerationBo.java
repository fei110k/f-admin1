package com.dtf.admin.manage.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

public interface CodeGenerationBo {
	
	/**
	 * 查询数据库下，所有表
	 * @param param
	 * @return
	 */
	public PageInfo queryAllTable(Map param);
	
	/**
	 * 生成代码
	 * @param param
	 * @return
	 */
	public List codeGeneration(Map param);
}
