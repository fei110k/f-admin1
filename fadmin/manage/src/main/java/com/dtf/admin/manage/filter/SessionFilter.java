package com.dtf.admin.manage.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.dtf.admin.common.Consts;
import com.dtf.admin.common.utils.StringUtil;
import com.dtf.admin.manage.vo.StaffUser;
import com.dtf.admin.manage.vo.SysMenu;

/**
 *    判断用户是否登录,未登录则退出系统
 */
public class SessionFilter implements Filter {

    public FilterConfig config;

    public void destroy() {
        this.config = null;
    }
    
    public static boolean isContains(String container, String[] regx) {
        boolean result = false;
        for (int i = 0; i < regx.length; i++) {
            if (container.indexOf(regx[i]) != -1) {
                return true;
            }
        }
        return result;
    }
    
    /**
     * 检查用户是否含有访问的菜单权限
     * @param url	访问的菜单URL
     * @param user  登录的用户
     * @return
     */
    public boolean hasPri(String url,StaffUser user) {
        boolean result = false;
        if (url.endsWith(".do")) {
			return true;
		}
        //检查主菜单权限
        List<SysMenu> menuList = user.getMenuList();
        for (SysMenu sysMenu : menuList) {
			if (StringUtil.isEmpty(sysMenu.getMenu_url())) {
				continue;
			}
        	if (sysMenu.getMenu_url().startsWith(url)) {
        		return true;
        	}
		}
        
        //检查子菜单权限 
        List<String> mcList = user.getMenuChildrenList();
        for (String menuUrl : mcList) {
        	if (StringUtil.isEmpty(menuUrl)) {
				continue;
			}
        	if (menuUrl.startsWith(url)) {
        		return true;
        	}
		}
        
        return result;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hrequest = (HttpServletRequest)request;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);

        String logonStrings = config.getInitParameter("logonStrings");        // 登录登陆页面
        String includeStrings = config.getInitParameter("includeStrings");    // 过滤资源后缀参数
        String redirectPath = hrequest.getContextPath() + config.getInitParameter("redirectPath");// 没有登陆转向页面
        String disabletestfilter = config.getInitParameter("disabletestfilter");// 过滤器是否有效

        if (disabletestfilter.toUpperCase().equals("Y")) {    // 过滤无效
            chain.doFilter(request, response);
            return;
        }
        String[] logonList = logonStrings.split(";");
        String[] includeList = includeStrings.split(";");

        if (!this.isContains(hrequest.getRequestURI(), includeList)) {// 只对指定过滤参数后缀进行过滤
            chain.doFilter(request, response);
            return;
        }

        if (this.isContains(hrequest.getRequestURI(), logonList)) {// 对登录页面不进行过滤
            chain.doFilter(request, response);
            return;
        }

        StaffUser user = (StaffUser) hrequest.getSession().getAttribute(Consts.SESSION_USER_KEY);//判断用户是否登录
        if (user == null) {
            wrapper.sendRedirect(redirectPath+"?type=nologin");
            return;
        }
        
        if (!hasPri(hrequest.getRequestURI(), user)) {// 对访问的页面进行权限过滤
        	wrapper.sendRedirect(redirectPath+"?type=nopri");
            return;
        }
        chain.doFilter(request, response);
        return;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }
}