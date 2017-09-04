package com.dtf.admin.common.email;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import com.dtf.admin.common.cache.SysParamsCache;
import com.dtf.admin.common.log.Log;

public class EMailSender {
	
	private static EMailUtil mail = null;
	
	/**
	 * 将发送邮件的实例重置
	 */
	public static void reloadMail(){
		SysParamsCache.removeSysParams("SYSTEM_EMAIL_USER_NAME");
		SysParamsCache.removeSysParams("SYSTEM_EMAIL_USER_PWD");
		SysParamsCache.removeSysParams("SYSTEM_EMAIL_SEND_FROM");
		mail = null;
	}
	
	private static EMailUtil getMail() throws Exception{
		if (mail == null) {
			String user_name = SysParamsCache.getSysParamVal("SYSTEM_EMAIL_USER_NAME");
			String user_pwd = SysParamsCache.getSysParamVal("SYSTEM_EMAIL_USER_PWD");
			String from = SysParamsCache.getSysParamVal("SYSTEM_EMAIL_SEND_FROM");
			
			mail = new EMailUtil(user_name, user_pwd);
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("mail.smtp.host", "smtp.qq.com");
			map.put("mail.smtp.auth", "true");
			map.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			map.put("mail.smtp.port", "465");
			map.put("mail.smtp.socketFactory.port", "465");
			mail.setPros(map);
			mail.initMessage();
			mail.setFrom(from);
		}
		
		return mail;
	}
	/**
	 * 发送邮件
	 * @param to 收件人，可以设置多人 
	 * @param subject 邮件主题
	 * @param content  邮件内容
	 * @return
	 * @throws MessagingException 
	 * @throws Exception 
	 */
	public static boolean send(List<String> to,String subject,String content){
		try {
			EMailUtil mail = getMail();
			mail.setRecipients(to);
			mail.setSubject(subject);
			mail.setContent(content, "text/html; charset=UTF-8");
			mail.setDate(new Date());	//发送时间
			String s = mail.sendMessage();
			if ("success".equals(s)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			Log.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 
	 * 发送带附件的邮件
	 * @param to 收件人，可以设置多人 
	 * @param subject 邮件主题
	 * @param fileList  附件列表
	 * @return
	 */
	public static boolean send(List<String> to,String subject,
			String content,List<String> fileList){
		try {
			EMailUtil mail = getMail();
			mail.setRecipients(to);
			mail.setSubject(subject);
			mail.setContent(content, "text/html; charset=UTF-8");
			mail.setDate(new Date());	//发送时间
			mail.setMultiparts(fileList);	//发送附件
			String s = mail.sendMessage();
			if ("success".equals(s)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			Log.error(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args){
		//只发送带文本的邮件
		List<String> to = new ArrayList<>();
		to.add("fei110k@qq.com");
		send(to, "你牛B", "你厉害！");
		
//		//发送带附件的邮件
//		List<String> to = new ArrayList<>();
//		to.add("fei110k@qq.com");
//		List<String> fileList = new ArrayList<>();
//		fileList.add("d://BugReport.txt");
//		EMailSender.send(to, "我又来测试了", "看看加个附件行不行",fileList);
	}
}
