package com.dtf.admin.manage.service.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtf.admin.common.ReturnVO;
import com.dtf.admin.common.utils.MD5Utils;
import com.dtf.admin.common.utils.UUIDGenerator;
import com.dtf.admin.common.utils.mybatis.DaoUtils;
import com.dtf.admin.manage.service.SysStaffBo;
import com.github.pagehelper.PageInfo;

@Service
public class SysStaffBoImpl implements SysStaffBo{
	
	private static final Log log = LogFactory.getLog(SysStaffBoImpl.class);
	
	@Autowired
	private DaoUtils daoUtils;
	
	@Override
	public Map findSysStaffById(Map param) {
		// TODO Auto-generated method stub
		return daoUtils.getSqlSessionTemplate().selectOne("SysStaff.findSysStaff", param);
	}
	
	@Override
	public PageInfo findSysStaff(Map param) {
		// TODO Auto-generated method stub
		return daoUtils.selectPage("SysStaff.findSysStaff", param);
	}
	
	@Override
	public ReturnVO insertSysStaff(Map param) {
		ReturnVO req = new ReturnVO();
		String staff_id = UUIDGenerator.getInst().getUUID32();
		String password = "123456";		//系统默认密码123456
		password = MD5Utils.MD5(password);
		param.put("staff_id", staff_id);
		param.put("password", password);
		int update_count = daoUtils.getSqlSessionTemplate().insert("SysStaff.insertSysStaff", param);
		
		return req;
	}

	@Override
	public ReturnVO updateSysStaffByConn(Map param) {
		ReturnVO req = new ReturnVO();
		int update_count = daoUtils.getSqlSessionTemplate().update("SysStaff.updateSysStaffByConn", param);
		return req;
	}
	
	@Override
	public ReturnVO updateSysStaffPassword(Map param) {
		ReturnVO req = new ReturnVO();
		int update_count = daoUtils.getSqlSessionTemplate().update("SysStaff.updateSysStaffPassword", param);
		return req;
	}
	
	@Override
	public ReturnVO deleteSysStaffById(Map param) {
		ReturnVO req = new ReturnVO();
		int update_count = daoUtils.getSqlSessionTemplate().delete("SysStaff.deleteSysStaffById", param);
		return req;
	}

	@Override
	public ReturnVO deleteSysStaffByIds(Map param) {
		ReturnVO req = new ReturnVO();
		int update_count = daoUtils.getSqlSessionTemplate().delete("SysStaff.deleteSysStaffByIds", param);
		return req;
	}

	@Override
	public PageInfo findStaffRole(Map param) {
		return daoUtils.selectPage("SysStaff.findStaffRole", param);
	}

	@Override
	public ReturnVO deleteStaffRole(Map param) {
		ReturnVO req = new ReturnVO();
		int update_count = daoUtils.getSqlSessionTemplate().delete("SysStaff.deleteStaffRole", param);
		return req;
	}
	
	@Override
	public ReturnVO insertStaffRole(Map param) {
		ReturnVO req = new ReturnVO();
		String rel_id = UUIDGenerator.getInst().getUUID32();
		param.put("rel_id", rel_id);
		int update_count = daoUtils.getSqlSessionTemplate().delete("SysStaff.insertStaffRole", param);
		return req;
	}
	
	
}
