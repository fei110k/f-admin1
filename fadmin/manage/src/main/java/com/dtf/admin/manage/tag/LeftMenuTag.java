package com.dtf.admin.manage.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.dtf.admin.common.Consts;
import com.dtf.admin.common.utils.StringUtil;
import com.dtf.admin.manage.vo.StaffUser;
import com.dtf.admin.manage.vo.SysMenu;


public class LeftMenuTag extends TagSupport{
	
	
	@Override
	public int doEndTag() throws JspException {
		show();
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub

		return super.doStartTag();
	}
	
	@Override
	public void release(){
	      super.release();
	}

	public void show(){
		HttpSession session =  super.pageContext.getSession();
		StaffUser staff = (StaffUser) session.getAttribute(Consts.SESSION_USER_KEY);
		
		String htm = "";
		if (staff == null) {
			htm = "<script>alert('请先登录');window.location.href = '/manage/login.jsp?mes=未登录';</script>";
		}else{
//			List<Map<String,Object>> menus = (List<Map<String, Object>>) session.getAttribute(Contanst.CURRENT_MENU_KEY);
			
			List<SysMenu> menus = staff.getMenuList();
			
			htm = objToHtml(menus);
//			System.out.println(htm);
		}
		
		JspWriter out = pageContext.getOut();
		try {
			out.print(htm);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String objToHtml(List<SysMenu> menus){
		String html = "<div class=\"menu_dropdown bk_2\">";
		//组装一级菜单
		for (SysMenu menu : menus) {
			if ("1".equals(menu.getMenu_level())) {
				String classStyle = menu.getClass_style();
				classStyle = StringUtil.isEmpty(classStyle)?"":"<i class=\"Hui-iconfont\">"+classStyle+"</i>";
				html += "<dl id=\"menu-article\">"+
							"<dt>"+
								classStyle+ menu.getMenu_name()+
								"<i class=\"Hui-iconfont menu_dropdown-arrow\">&#xe6d5;</i>"+
							"</dt>";
				int cs = 0;
				String h2 = "";
				h2 += "<dd><ul>";
				for (SysMenu m2 : menus) {
					if ("2".equals(m2.getMenu_level()) 
							&& StringUtil.equals(m2.getParent_id(),menu.getMenu_id())) {
						cs++;
						h2 += "<li><a data-href=\""+m2.getMenu_url()+"\" "
								+ "data-title=\""+m2.getMenu_name()+"\" href=\"javascript:void(0)\">"+m2.getMenu_name()+"</a></li>";
					}
				}
				h2 += "</ul></dd>";
				if (cs == 0) {
					h2 = "";
				}
				html += h2;
				html += "</dl>";
			}
		}
		html += "</div>";
		return html;
	}
	
	
	public String objToHtml(List<SysMenu> menus,String pid){
		
		String html = "";
		if ("-1".equals(pid)) {
			html += "<div class=\"menu_dropdown bk_2\">";
		}else{
			html += "<dd>";
		}
		int count = 0;
		for (SysMenu menu : menus) {
			String parent_id = menu.getParent_id();
			if (!parent_id.equals(pid)) {
				continue;
			}
			count ++;
			String menu_id = menu.getMenu_id();
			String menu_name = menu.getMenu_name();
			String menu_url = menu.getMenu_url();
			String class_style = menu.getClass_style();
			String level = menu.getMenu_level();
			String liClass = "";
			
			if ("2".equals(level)) {
				
			}
			
			String basePath = "/baseWeb/main.do?p="+menu_id;
			String chtm = objToHtml(menus, menu_id);
			String hasC = "".equals(chtm)? "" : "class='dropdown-toggle' ";
			String chtm2 = "".equals(chtm)? "" : "<b class='arrow fa fa-angle-down'></b>";
			html += "<li class='"+liClass+"' >"+
							"<a href='"+basePath+"' "+hasC+" >"+
								"<i class=\"menu-icon fa "+class_style+"\"></i>" +
								"<span class='menu-text'>"+menu_name+"</span>"+
								chtm2 +
							"</a>"+
							"<b class='arrow'></b>";
			html += chtm;
			html += "</li>";
		}
		if ("-1".equals(pid)) {
			html += "</div";
		}else{
			html += "</dd>";
		}
		if (count == 0) {
			html = "";
		}
		return html;
	}
}
