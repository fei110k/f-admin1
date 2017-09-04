package com.dtf.admin.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 数组工具类
 * @author zhenym
 * @date 2015-4-15
 */
public class ListUtils{
	
	private static Log log = LogFactory.getLog(ListUtils.class);
	
	/**
	 * 判断数组是否为空
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(List<?> list){
		return list == null || list.isEmpty();
	}
	
	/**
	 * 判断数组是否为空
	 * @param list
	 * @return
	 */
	public static boolean isNotEmpty(List<?> list){
		return !isEmpty(list);
	}
	
	/**
	 * 拷贝bean数组
	 * @param beans
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> toList(List<?> beans, Class<T> clazz){
		List<T> list = new ArrayList<T>();
		if(isEmpty(beans)){
			return list;
		}
		
		try{
			for(Object bean : beans){
				T targetBean = clazz.newInstance();
				PropertyUtils.copyProperties(targetBean, bean);
				list.add(targetBean);
			}
		}
		catch(Exception e){
			log.error(e.getMessage(), e);
		}
		
		return list;
	}
	
	/**
	 * 把bean数组转换成map数组
	 * @param beans
	 * @return
	 */
	public static List<Map<String, String>> toMap(List<?> beans){
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		
		if(isEmpty(beans)){
			return mapList;
		}
		
		try{
			for(Object bean : beans){
				Map<String, String> map = BeanUtils.describe(bean);
				mapList.add(map);
			}
		}
		catch(Exception e){
			log.error(e.getMessage(), e);
		}
		
		return mapList;
	}
}