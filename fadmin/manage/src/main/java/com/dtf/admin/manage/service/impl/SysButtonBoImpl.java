package com.dtf.admin.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtf.admin.common.ReturnVO;
import com.dtf.admin.common.utils.ListUtils;
import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.common.utils.StringUtil;
import com.dtf.admin.common.utils.UUIDGenerator;
import com.dtf.admin.common.utils.mybatis.DaoUtils;
import com.dtf.admin.manage.service.SysButtonBo;

@Service
public class SysButtonBoImpl implements SysButtonBo{
	
	private static final Log log = LogFactory.getLog(SysButtonBoImpl.class);
	
	@Autowired
	private DaoUtils daoUtils;
	
	@Override
	public Map findSysButtonById(Map param) {
		return daoUtils.getSqlSessionTemplate().selectOne("SysButton.findSysButtonByConn", param);
	}

	@Override
	public ReturnVO updateSysButtonByConn(Map param) {
		ReturnVO req = new ReturnVO();
		String btn_id = MapUtils.getString(param, "btn_id");
		String btn_code = MapUtils.getString(param, "btn_code");
		Map selectParam = new HashMap();
		selectParam.put("btn_code", btn_code);
		List<Map<String,String>> list = daoUtils.getSqlSessionTemplate().selectList("SysButton.findSysButtonByConn", selectParam);
		if (ListUtils.isNotEmpty(list) && 
				(list.size() > 1 
						|| !StringUtil.equals(btn_id, MapUtils.getString(list.get(0), "btn_id")))) {
			req.setCode("9999");
			req.setMsg("按钮编码重复，将会导致按钮权限无法界定，请重新输入编码！");
			return req;
		}
		
		int update_count = daoUtils.getSqlSessionTemplate().update("SysButton.updateSysButtonByConn", param);
		return req;
	}

	@Override
	public ReturnVO deleteSysButtonById(Map param) {
		ReturnVO req = new ReturnVO();
		int update_count = daoUtils.getSqlSessionTemplate().delete("SysButton.deleteSysButtonById", param);
		return req;
	}
	
	@Override
	public ReturnVO insertSysButton(Map param) {
		ReturnVO req = new ReturnVO();
		String btn_id = UUIDGenerator.getInst().getUUID32();
		param.put("btn_id", btn_id);
		
		String btn_code = MapUtils.getString(param, "btn_code");
		Map selectParam = new HashMap();
		selectParam.put("btn_code", btn_code);
		List list = daoUtils.getSqlSessionTemplate().selectList("SysButton.findSysButtonByConn", selectParam);
		if (ListUtils.isNotEmpty(list)) {
			req.setCode("9999");
			req.setMsg("按钮编码重复，将会导致按钮权限无法界定，请重新输入编码！");
			return req;
		}
		int update_count = daoUtils.getSqlSessionTemplate().insert("SysButton.insertSysButton", param);
		return req;
	}
	
}
