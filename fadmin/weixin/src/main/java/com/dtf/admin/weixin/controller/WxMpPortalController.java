package com.dtf.admin.weixin.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dtf.admin.weixin.service.WeixinService;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 所有的从微信输入框过来的请求、或者验证服务端的请求，都会通过此类来进行分发
 */
@RestController
@RequestMapping("/wechat/receiveMessages")
public class WxMpPortalController {
	@Autowired
	private WeixinService wxService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 此方法用于接收微信的所有消息
	 * 当请求是get时，默认为验证客户端
	 * 当请求是post时，为微信向服务器传输消息
	 * @param requestBody
	 * @return
	 */
	@ResponseBody
	@RequestMapping
	public String receiveMessages(@RequestBody String requestBody) {
		String method = request.getMethod();
		if ("GET".equals(method)) {
			return doGet();
		}else{
			return doPost(requestBody);
		}
	}
	
	public String doGet(){
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", 
				signature,timestamp, nonce, echostr);
		
//				 if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
//					 throw new IllegalArgumentException("请求参数非法，请核实!");
//				 }
		
		 if (this.getWxService().checkSignature(timestamp, nonce, signature))
		 {
			 return echostr;
		 }

		return "非法请求";
	}
	
	public String doPost(String requestBody) {
		
		String signature = request.getParameter("signature");
		String encrypt_type = request.getParameter("encrypt_type");
		String msg_signature = request.getParameter("msg_signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		
		
		
		this.logger.info(
				"\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
						+ " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
				signature, encrypt_type, msg_signature, timestamp, nonce, requestBody);

		if (!this.wxService.checkSignature(timestamp, nonce, signature)) {
			throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
		}

		String out = null;
		if (encrypt_type == null) {
			// 明文传输的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
			WxMpXmlOutMessage outMessage = this.getWxService().route(inMessage);
			if (outMessage == null) {
				return "";
			}

			out = outMessage.toXml();
		} else if ("aes".equals(encrypt_type)) {
			// aes加密的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody,
					this.getWxService().getWxMpConfigStorage(), timestamp, nonce, msg_signature);
			this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
			WxMpXmlOutMessage outMessage = this.getWxService().route(inMessage);
			if (outMessage == null) {
				return "你";
			}

			out = outMessage.toEncryptedXml(this.getWxService().getWxMpConfigStorage());
		}

		this.logger.debug("\n组装回复信息：{}", out);

		return out;
	}

	protected WeixinService getWxService() {
		return this.wxService;
	}

}
