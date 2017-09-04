package com.dtf.admin.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtf.admin.common.ReturnVO;
import com.dtf.admin.common.StateCode;
import com.dtf.admin.common.utils.MD5Utils;
import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.common.utils.StringUtil;
import com.dtf.admin.common.utils.UUIDGenerator;
import com.dtf.admin.common.utils.mybatis.DaoUtils;
import com.dtf.admin.manage.service.MainBo;
import com.dtf.admin.manage.service.SysStaffBo;
import com.dtf.admin.manage.vo.SysStaff;
import com.github.pagehelper.PageInfo;

@Service
public class MainBoImpl implements MainBo{
	
	private static final Log log = LogFactory.getLog(MainBoImpl.class);
	
	@Autowired
	private DaoUtils daoUtils;

	@Override
	public ReturnVO login(Map param) {
		ReturnVO returnVO = new ReturnVO();
		String staff_code = MapUtils.getString(param, "staff_code");
		String password = MapUtils.getString(param, "password");
		if (StringUtil.isEmpty(password)) {
			returnVO.setStateCode(StateCode.STATE_1000);
			return returnVO;
		}
		password = MD5Utils.MD5(password);
		SysStaff staff = daoUtils.getSqlSessionTemplate().selectOne("Main.findSysStaff",staff_code);
		
		//如果找不到对应的用户名
		if (staff == null) {
			returnVO.setStateCode(StateCode.STATE_1000);
			return returnVO;
		}
		//如果密码不正确
		if (!password.equals(staff.getPassword())) {
			returnVO.setStateCode(StateCode.STATE_1000);
			return returnVO;
		}
		
		returnVO.setMore(staff);
		
		return returnVO;
	}
	
	@Override
	public List getUserMenuPrivilege(Map param){
		String staff_code = MapUtils.getString(param, "staff_code");
		List list = daoUtils.getSqlSessionTemplate().selectList("Main.findSysStaffMenu",staff_code);
		return list;
	}
	
	@Override
	public List getUserButtonPrivilege(Map param){
		String staff_code = MapUtils.getString(param, "staff_code");
		List list = daoUtils.getSqlSessionTemplate().selectList("Main.findSysStaffButton",staff_code);
		return list;
	}
}
