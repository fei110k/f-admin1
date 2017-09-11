package com.dtf.admin.weixin.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.dtf.admin.common.utils.HttpUtils;
import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.common.utils.StringUtil;
import com.dtf.admin.weixin.config.WxMpConfig;
/**
 * 微信JS API调用 页面认证
 * URL参考：
 * 
 * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx54499b8ade01ca49&redirect_uri=http%3A%2F%2Ffei110k.oicp.net%2FWxJSOAuth.do&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect 
 * @author fei
 *
 */
@Controller(value="/wechat/WxJSOAuth")
public class WxJSOAuthController {
	
	@Autowired
	private WxMpConfig wxConfig;
	
	@RequestMapping
	public void authGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String secret = wxConfig.getAppsecret();
		String appid = wxConfig.getAppid();
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
		Map param = new TreeMap();
		param.put("appid", appid);
		param.put("secret", secret);
		param.put("code", code);
		param.put("grant_type", "authorization_code");
		String reqStr = HttpUtils.sendGet(url, param);
		System.out.println(reqStr);
		Map reqMap = JSON.parseObject(reqStr, Map.class);
		String openid = MapUtils.getString(reqMap, "openid");
		
		request.setAttribute("message","openID:"+openid);
		if (StringUtil.isEmpty(openid)) {
			//没有获取到openID的情况，直接让用户手工登录
			
		}else{
			//查询用户是否有绑定过账号
			
			//如果用户有绑定过账号，将session注入
			if (true) {
				request.getRequestDispatcher("/weixin/index.jsp").forward(request, response);
				return;
			//如果用户没有绑定过账号，弹到用户绑定页面
			}else{
				
			}
		}
		
		try {
			request.setAttribute("message",reqStr);
			request.getRequestDispatcher("/weixin/index.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	public static void main(String[] args) {
		String u = "http://alove.imwork.net/WxJSOAuth.do";
		u = URLEncoder.encode(u);
		
		//需要用户确认
//		u = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx54499b8ade01ca49&redirect_uri=" + u
//				+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
//		//不需要用户确认
		u = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx54499b8ade01ca49&redirect_uri=" + u +
				"&response_type=code&scope=snsapi_base&state=123#wechat_redirect" ;
		
		System.out.println(u);
	}
}
