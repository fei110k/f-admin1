package com.dtf.admin.manage.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.dtf.admin.common.Consts;
import com.dtf.admin.common.utils.StringUtil;
import com.dtf.admin.common.utils.manage.SessionUtil;
import com.dtf.admin.manage.vo.StaffUser;
import com.dtf.admin.manage.vo.SysMenu;

/**
 * 按钮鉴权标签
 * @author fei
 *
 */
public class ButtonAuthTag extends TagSupport{
	
	private String code;
	
	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		int res = SKIP_BODY;
		HttpSession session =  super.pageContext.getSession();
		StaffUser staff = (StaffUser) session.getAttribute(Consts.SESSION_USER_KEY);
		if (checkButtonAuth(code,staff)) {
			res = EVAL_BODY_INCLUDE;
		}
		return res;
	}
	
	@Override
	public void release(){
		super.release();
	}
	
	/**
	 * 判断用户是否有权限访问按钮
	 * @return
	 */
	public boolean checkButtonAuth(String code,StaffUser staff){
		List btns = staff.getBtnList();
		if (btns.contains(code)) {
			return true;
		}
		
		return false;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
