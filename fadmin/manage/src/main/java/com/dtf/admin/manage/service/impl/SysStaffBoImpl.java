package com.dtf.admin.manage.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtf.admin.common.ReturnVO;
import com.dtf.admin.common.utils.DateUtil;
import com.dtf.admin.common.utils.MD5Utils;
import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.common.utils.UUIDGenerator;
import com.dtf.admin.common.utils.mybatis.DaoUtils;
import com.dtf.admin.manage.service.SysMessageBo;
import com.dtf.admin.manage.service.SysStaffBo;
import com.github.pagehelper.PageInfo;

@Service
public class SysStaffBoImpl implements SysStaffBo{
	
	private static final Log log = LogFactory.getLog(SysStaffBoImpl.class);
	
	@Autowired
	private SysMessageBo sysMessageBo;
	
	@Autowired
	private DaoUtils daoUtils;
	
	@Override
	public Map findSysStaffById(Map param) {
		// TODO Auto-generated method stub
		return daoUtils.getSqlSessionTemplate().selectOne("SysStaff.findSysStaffById", param);
	}
	
	@Override
	public PageInfo findSysStaff(Map param) {
		// TODO Auto-generated method stub
		String staff_name = MapUtils.getString(param, "staff_name");
		param.put("staff_name_like", "%"+staff_name+"%");
		
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
		
		String staff_id = MapUtils.getString(param, "staff_id");
		
		//插入sys_message消息
		Map msg = new HashMap();
		msg.put("msg_state", "1");  //SYS_STAFF_MSG_STATE 1:未读 ; 2:已阅
		msg.put("msg_type", "2");	//SYS_STAFF_MSG_TYPE 字典表中2：表示密码修改
		msg.put("receive_staff_id", staff_id);	//接受消息用户ID
		msg.put("send_staff_id", staff_id);		//发送消息用户ID
		msg.put("msg_title", "密码修改提示！");		//发送消息标题
		msg.put("msg_content", "您好，你的用户登录密码于："+DateUtil.getFormatedDateTime()+
				"发生变更，如不是本人操作，请联系管理员！");		//发送消息内容
		msg.put("is_top", "1"); //0;置顶	;1:不置顶
		msg.put("msg_url", "");	//点击消息跳转的URL
		
		sysMessageBo.insertSysMessage(msg);
		
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
