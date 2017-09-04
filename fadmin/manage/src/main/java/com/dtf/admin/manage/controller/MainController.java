package com.dtf.admin.manage.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dtf.admin.common.Consts;
import com.dtf.admin.common.ReturnVO;
import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.common.utils.StringUtil;
import com.dtf.admin.common.utils.manage.SessionUtil;
import com.dtf.admin.manage.service.MainBo;
import com.dtf.admin.manage.vo.StaffUser;
import com.dtf.admin.manage.vo.SysStaff;
import com.google.code.kaptcha.Constants;

@Controller
@RequestMapping("/")
public class MainController {
	
	Log log = LogFactory.getLog(MainController.class);
	
	@Autowired
	private MainBo mainBo;
	
	@Autowired
	private HttpServletRequest request;
	
	@ResponseBody
	@RequestMapping(value="/loginout")
	public ReturnVO loginout(@RequestParam Map param){
		ReturnVO returnVO = new ReturnVO();
		SessionUtil.getSession().removeAttribute(Consts.SESSION_USER_KEY);
		SessionUtil.getSession().removeAttribute("staff_name");
		return returnVO;
	}
	
	@ResponseBody
	@RequestMapping(value="/login")
	public ReturnVO login(@RequestParam Map param){
		ReturnVO returnVO = new ReturnVO();
		
		String verificationCode = MapUtils.getString(param, "verificationCode").toUpperCase();
		
		String kaptcha_session_key = SessionUtil.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY)+"";
		
		if (!StringUtil.equals(verificationCode, kaptcha_session_key)) {
			returnVO.setCode("1001");
			returnVO.setMsg("验证码输入错误！");
			return returnVO;
		}
		
		returnVO = mainBo.login(param);
		
		if (!returnVO.isSuccess()) {
			return returnVO;
		}
		
		StaffUser staffUser = new StaffUser();
		SysStaff staff = returnVO.getMore();
		
		staffUser.setStaff(staff);
		//菜色权限
		List menuList = mainBo.getUserMenuPrivilege(param);
		staffUser.setMenuList(menuList);
		
		//菜色子菜单页面URL
		List menuChildrenList = mainBo.getSysStaffMenuChildren(param);
		staffUser.setMenuChildrenList(menuChildrenList);
		
		//按钮权限
		List btnList = mainBo.getUserButtonPrivilege(param);
		staffUser.setBtnList(btnList);
		
		request.getSession().setAttribute("staff_name", staffUser.getStaff().getStaff_name());
		
		request.getSession().setAttribute(Consts.SESSION_USER_KEY, staffUser);
		
		return returnVO;
	}
	
}
