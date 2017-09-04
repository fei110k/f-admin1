package com.dtf.admin.common.utils.manage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dtf.admin.common.Consts;
import com.dtf.admin.manage.vo.StaffUser;

/**
 * 此类只能放在有session的层中使用
 * @author fei
 *
 */
public class SessionUtil {
	
	public static final HttpSession getSession(){
		HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		return session;
	}
	
	public static final HttpServletRequest getRequest(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	public static final StaffUser getStaffUser(){
		return (StaffUser) getSession().getAttribute(Consts.SESSION_USER_KEY);
	}
}
