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
import com.dtf.admin.common.utils.StringUtil;
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
		Map req = daoUtils.getSqlSessionTemplate().selectOne("SysMessage.findSysMessageById", param);;
		param.put("msg_state", "2");
		daoUtils.getSqlSessionTemplate().selectOne("SysMessage.updateSysMessageById", param);
		return daoUtils.getSqlSessionTemplate().selectOne("SysMessage.findSysMessageById", param);
	}
	
	@Override
	public PageInfo findSysMessage(Map param) {
		// TODO Auto-generated method stub
		String msg_title = MapUtils.getString(param, "msg_title");
		if (StringUtil.isNotEmpty(msg_title)) {
			param.put("msg_title", "%"+msg_title+"%");
		}
		return daoUtils.selectPage("SysMessage.findSysMessage", param);
	}
	
	@Override
	public ReturnVO insertSysMessage(Map param) {
		ReturnVO req = new ReturnVO();
		
		String msg_id = MapUtils.getString(param, "msg_id");
		//在外部调用时，没有输入msg_id的时候，就生成ID，否则不生成
		if(StringUtil.isEmpty(msg_id)){
			msg_id = UUIDGenerator.getInst().getUUID32();
			param.put("msg_id", msg_id);
		}
		int update_count = daoUtils.getSqlSessionTemplate().insert("SysMessage.insertSysMessage", param);
		
		return req;
	}
	

	@Override
	public ReturnVO updateSysMessageById(Map param) {
		ReturnVO req = new ReturnVO();
		
		List<Map> list = daoUtils.getSqlSessionTemplate().selectList("SysMessage.findSysMessageByName", param);
		if ((ListUtils.isNotEmpty(list) && list.size() > 1 )||
				(ListUtils.isNotEmpty(list) 
						&& !MapUtils.getString(param, "msg_id").equals(MapUtils.getString(list.get(0), "msg_id")))) {
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

	@Override
	public Map findStaffSysMessageCount(Map param) {
		param.put("msg_state", "1");
		return daoUtils.getSqlSessionTemplate().selectOne("SysMessage.findStaffSysMessageCount", param);
	}
	
}
