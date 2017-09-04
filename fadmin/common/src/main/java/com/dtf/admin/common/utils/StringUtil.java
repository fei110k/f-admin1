package com.dtf.admin.common.utils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.soap.SOAPConstants;

import com.dtf.admin.common.Consts;

/**
 * 字符串工具
 * @author zhenym
 * @date 2015-4-10
 */
public class StringUtil{
	
	/**
	 * 判断字符串不为空
	 * @param string
	 * @return
	 */
	public static boolean isNotEmpty(String string) {
		return !isEmpty(string);
	}

	/**
	 * 判断字符串为空
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		return string == null || "null".equals(string) || string.length() == 0;
	}
	
	/**
	 * 字符串比较(null和空或空格字符串认为相等)
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equals(String str1, String str2){
		if(StringUtil.isEmpty(str1)){
			str1 = "";
		}
		
		if(StringUtil.isEmpty(str2)){
			str2 = "";
		}
		
		return str1.equals(str2);
	}
	
	
	/**
	 * 生成自定义主键 :时间戳+n位随机数
	 * @param date  前缀：当前系统时间
	 * @param n     后缀：n为随机数
	 * @param max   随机数最大值
	 * @return
	 */
	public static String genCustomSeq(Date date, int n, int max){
		//ID前缀 由时间戳构成
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String prefix_id = df.format(date);
		
		//ID后缀 由n位随机数构成
		Random random = new Random();
		int randVal = random.nextInt(max);
		String suffix_id = String.format("%0" + n + "d", randVal);//0 代表前面补充0 3 代表长度为3 d代表参数为正数型
		
		return (prefix_id + suffix_id);
	}
	
	/**
	 * @param url
	 * @return str 数据
	 * str[0] 为ip
	 * str[1] 为端口，未配置端口时，默认为80
	 */
	public static String[] getIpAndPortByUrl(String url){
		String[] strs = new String[2];
		String regex = "//(.*?)(:\\d{1,5})?/";//"//(.*?):(\\d{1,5})?";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(url);
		while(m.find()){
			strs[0] = m.group(1);
			String ports = m.group(2);
			if(isNotEmpty(ports)){
				ports = ports.substring(1);
			}
			else{
				ports = "80";
			}
			strs[1] = ports;
		}
		return strs;
	}
	
	/**
	 * 判断报文是否SOAP协议
	 * @param content
	 * @return
	 */
	public static boolean isSoap(String content){
		if(StringUtil.isEmpty(content)){
			return false;
		}
		
		return isSoap11(content) || isSoap12(content);
	}
	
	/**
	 * 判断是否soap1.1协议
	 * @param content
	 * @return
	 */
	public static boolean isSoap11(String content){
		if(StringUtil.isEmpty(content)){
			return false;
		}
		
		if(content.indexOf(SOAPConstants.URI_NS_SOAP_1_1_ENVELOPE) != -1){//SOAP1.1
			return true;
		}
		
		return false;
	}
	
	/**
	 * 判断是否soap1.2协议
	 * @param content
	 * @return
	 */
	public static boolean isSoap12(String content){
		if(StringUtil.isEmpty(content)){
			return false;
		}
		
		if(content.indexOf(SOAPConstants.URI_NS_SOAP_1_2_ENVELOPE) != -1){//SOAP1.2
			return true;
		}
		
		return false;
	}
	
	/**
	 * 判断字符串是否JSON
	 * @param str
	 * @return
	 */
	public static boolean isJson(String str){
		if(StringUtil.isNotEmpty(str) && 
			((str.trim().startsWith("{") && str.trim().endsWith("}")) || (str.trim().startsWith("[") && str.trim().endsWith("]")))){
			return true;
		}
		
		return false;
	}
	
	//返回编码格式
	public static String getEncode(String str){
		String encode = null;
		if(verifyEncode(str, Consts.ENCODING_GBK)){
			encode = Consts.ENCODING_GBK;
		}
		else if(verifyEncode(str, Consts.ENCODING_ISO88591)){
			encode = Consts.ENCODING_ISO88591;
		}
		else if(verifyEncode(str, Consts.ENCODING_UTF8)){
			encode = Consts.ENCODING_UTF8;
		}
		
		return encode;
	}

	// 判断编码格式是否相符
	public static boolean verifyEncode(String str, String encode){
		try{
			if(str.equals(new String(str.getBytes(encode), encode))){
				return true;
			}
		}
		catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 手机号码校验
	 * @param mobilePhone
	 * @return
	 */
	public static boolean validateMobilePhone(String mobilePhone) {
		if(isEmpty(mobilePhone)) {
			return false;
		}
		
		String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		return Pattern.matches(REGEX_MOBILE, mobilePhone);
	}
	
	/**
	 * 电子邮箱地址校验
	 * @param email
	 * @return
	 */
	public static boolean validateEmail(String email) {
		if(isEmpty(email)) {
			return false;
		}
		
		String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return Pattern.matches(REGEX_EMAIL, email);
	}

	/**
	 * 是否是数字
	 * @param str
	 * @return true 是， false 否
	 */
	public static boolean isNumeric(String str) {
		
		if(isEmpty(str)) {
			return false;
		}
		
		String NUMERIC = "[0-9]*";
	    return Pattern.matches(NUMERIC, str);
	}
	
	/**
	 * 多字符参数非空判断
	 * 
	 * @param error_msg
	 * @param strings
	 */
	public static void valiteMultiStringNull(String error_msg,String...strings){
		for (String str : strings) {
			if(isEmpty(str)){
				throw new IllegalArgumentException(error_msg);
			}
		}
	}
	
	
	/**
	 * 根据字节长度截取字符串
	 * @param str
	 * @param byteLen
	 * @return
	 */
	public static String substringByteLen(String str, int byteLen){
		try {
			if (str != null) {
				byte[] bytes = str.getBytes("utf-8");
				if (bytes.length > byteLen) {
					byte[] bb = new byte[byteLen];
					for (int i = 0; i < bb.length; i++) {
						bb[i] = bytes[i];
					}
					str = new String(bb, "utf-8");
				}
			}
		} catch (Exception e1) {
		}
		return str;
	}
	
	/**
	 * 首字母大写
	 * @param name
	 * @return
	 */
	public static String captureName(String name) {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }
	
	/**
     * 下划线格式字符串转换为驼峰格式字符串
     * 
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == '_') {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}