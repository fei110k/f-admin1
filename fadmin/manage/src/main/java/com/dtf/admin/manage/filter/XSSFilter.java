package com.dtf.admin.manage.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.dtf.admin.common.utils.StringUtil;

/**
 * 防止XSS攻击
 * @author fei
 *
 */
public class XSSFilter implements Filter {
	
	public FilterConfig config;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }
	
	@Override
	public void destroy() {
		config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest)request;
		
		String includeStrings = config.getInitParameter("includeStrings");    // 过滤资源后缀参数
		String[] includeList = includeStrings.split(";");
		
		if (!StringUtil.isContains(hrequest.getRequestURI(), includeList)) {// 只对指定过滤参数后缀进行过滤
            chain.doFilter(request, response);
            return;
        }
		//使用自定的RequestWrapper定义获取参数时要添加的过滤
		chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
	}

	

}
