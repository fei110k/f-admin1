package com.dtf.admin.common;

public class Consts {
	//系统参数
	public static final String SYSTEM_NAME = "卫浴进销存后台管理系统";				//系统名称
	public static final String SYSTEM_COPYRIGHT = "fei110k@qq.com";			//版权所有
	public static final String SYSTEM_VERSION = "V1.0.0.0";			//版本号
	
	/**数据库类型**/
	public static final String DB_TYPE_MYSQL = "mysql";//数据库用mysql，对应param.properties的DBTYPE
	public static final String DB_TYPE_ORACLE = "oracle";//数据库用oracle，对应param.properties的DBTYPE
	public static final String DB_TYPE_MONGODB = "mongodb";//日志写hound的mongodb
	
	/**编码类型*/
	public static final String ENCODING_UTF8 = "UTF-8";
	public static final String ENCODING_GBK = "GBK";
	public static final String ENCODING_ISO88591 = "ISO8859-1";
	
	
	/**动作类型**/
	public static final String OPER_ADD = "A";//新增
	public static final String OPER_MOD = "M";//修改
	public static final String OPER_DEL = "D";//删除
	public static final String OPER_KEEP = "K";//不变
	public static final String OPER_PUB = "P";//发布
	
	public static final String SESSION_USER_KEY = "SESSION_USER_KEY";
}
