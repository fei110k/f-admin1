package com.dtf.admin.common.cache;

import com.dtf.admin.common.utils.SpringUtils;

public abstract class CacheUtils{
	
	public static CacheUtils distributedCacheUtil;
	
	public static CacheUtils localCacheUtil;
	
	//默认空间
	public static final String CACHE_WORKSPACE_DEFAULT = "0";
	
	//字典表空间
	public static final String CACHE_WORKSPACE_ATTR = "1";
	
	//系统参数空间
	public static final String CACHE_WORKSPACE_SYS_PARAM = "2";
	
	/**
	 * 获取集群式分布式缓存
	 * 如redis、memcached、Hbase等
	 * @return
	 */
	public static CacheUtils getDistributedInst(){
		//如果有redis可以把这个分布式缓存替换，sys_params/sys_attr等是直接调用分布式缓存的
		//由于目前未引入redis，所以暂行先使用本地缓存
//		if (distributedCacheUtil != null) {
//			return distributedCacheUtil;
//		}
//		distributedCacheUtil = SpringUtils.getBean("distributedCacheUtil");
//		return distributedCacheUtil;
		return getLocalInst();
	}
	
	/**
	 * 获取本地缓存
	 * 如redis、memcached、Hbase等
	 * @return
	 */
	public static CacheUtils getLocalInst(){
		if (localCacheUtil != null) {
			return localCacheUtil;
		}
		localCacheUtil = SpringUtils.getBean("localCacheUtil");
		return localCacheUtil;
	}
	
	public abstract String set(String key,Object value);
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @param time 有效时长（秒）
	 * @return
	 */
	public abstract String set(String key,Object value,int effTime);

	/**
	 * 根据key获取值
	 * @param key
	 * @return
	 */
	public abstract <E> E get(String key);
	
	/**
	 * 根据key删除数据
	 * @param key
	 * @return
	 */
	public abstract Long del(String key);
	
	/**
	 * 为key的自增+1
	 * @param key
	 * @return
	 */
	public abstract Long incr(String key);
	/**
	 * 为key的值加number
	 * @param key
	 * @param number 要添加的数字
	 * @return
	 */
	public abstract Long incr(String key,long number);
	
	/**
	 * 为key的自增-1
	 * @param key
	 * @return
	 */
	public abstract Long decr(String key);
	/**
	 * 为key的减去number
	 * @param key
	 * @param number 要减去的的数字
	 * @return
	 */
	public abstract Long decr(String key,long number);
}
