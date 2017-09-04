package com.dtf.admin.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dtf.admin.common.ReturnVO;
import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.common.utils.UUIDGenerator;
import com.dtf.admin.common.utils.mybatis.DaoUtils;
import com.dtf.admin.manage.service.SysOrgBo;
import com.github.pagehelper.PageInfo;

@Service
public class SysOrgBoImpl implements SysOrgBo{
	
	private static final Log log = LogFactory.getLog(SysOrgBoImpl.class);
	
	@Autowired
	private DaoUtils daoUtils;
	
	@Override
	public List findSysOrg(Map param){
		List<Map<String, Object>> list = 
				daoUtils.getSqlSessionTemplate().selectList("SysOrg.findSysOrg");
		//如果只是页面的情况下，更改页面的图标
		for (Map<String, Object> map : list) {
			String type = MapUtils.getString(map, "type");
			String menuId = MapUtils.getString(map, "id");
			//默认展开全部菜单
			map.put("open", "true");
		}
		
		return list;
	}
	
	@Override
	public Map findSysOrgById(Map param) {
		return daoUtils.getSqlSessionTemplate().selectOne("SysOrg.findSysOrg", param);
	}
	
	@Override
	public ReturnVO insertSysOrg(Map param) {
		ReturnVO req = new ReturnVO();
		String org_id = UUIDGenerator.getInst().getUUID32();
		param.put("org_id", org_id);
		String parent_id = MapUtils.getString(param, "parent_id");
		
		Map queryParentParam = new HashMap();
		queryParentParam.put("org_id", parent_id);
		Map parent_menu = daoUtils.getSqlSessionTemplate().selectOne("SysOrg.findSysOrg", queryParentParam);
		String org_path = MapUtils.getString(parent_menu, "org_path")+"."+org_id;
		int org_level = MapUtils.getInt(parent_menu, "org_level")+1;
		
		param.put("org_path", org_path);
		param.put("org_level", org_level);
		
		int update_count = daoUtils.getSqlSessionTemplate().insert("SysOrg.insertSysOrg", param);
		
		return req;
	}

	@Override
	public ReturnVO updateSysOrgByConn(Map param) {
		ReturnVO req = new ReturnVO();
		int update_count = daoUtils.getSqlSessionTemplate().update("SysOrg.updateSysOrgByConn", param);
		return req;
	}

	@Override
	@Transactional
	public ReturnVO deleteSysOrgById(Map param) {
		ReturnVO req = new ReturnVO();
		//先删除按钮，再删除菜单
		daoUtils.getSqlSessionTemplate().delete("SysOrg.deleteSysOrgById", param);
		return req;
	}
	
}
