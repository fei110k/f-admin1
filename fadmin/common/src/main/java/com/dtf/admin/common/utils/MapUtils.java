package com.dtf.admin.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class MapUtils {
	
	public static boolean isEmpty(Map map){
		if (map == null || map.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 从map中获取String字符串，如果不存在数据的情况，会返一个空串""
	 * @param map
	 * @param key
	 * @return
	 */
	public static String getString(Map map, String key){
		if (map == null) {
			return "";
		}
		Object val = map.get(key);
		if (val == null || (val+"").trim().equals("")) {
			return "";
		}
		return val+"";
	}
	
	/**
	 * 从map中获取String字符串，如果不存在数据的情况，会返一个defaultValue
	 * @param map
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getString(Map map, String key,String defaultValue){
		if (map == null) {
			return defaultValue;
		}
		
		Object val = map.get(key);
		if (val == null || (val+"").trim().equals("")) {
			return defaultValue;
		}
		return val+"";
	}
	
	/**
	 * 从map中获取int，如果不存在数据的情况，会返一个0
	 * @param map
	 * @param key
	 * @return
	 */
	public static int getInt(Map map, String key){
		if (map == null) {
			return 0;
		}
		
		Object val = map.get(key);
		if (val == null || (val+"").trim().equals("")) {
			return 0;
		}
		int v = 0;
		try {
			v = Integer.valueOf((val+"").trim());
		} catch (Exception e) {
		}
		return v;
	}
	
	/**
	 * 从map中获取int，如果不存在数据的情况，会返一个0
	 * @param map
	 * @param key
	 * @return
	 */
	public static int getInt(Map map, String key,int defaultValue){
		if (map == null) {
			return defaultValue;
		}
		
		Object val = map.get(key);
		if (val == null || (val+"").trim().equals("")) {
			return defaultValue;
		}
		int v = defaultValue;
		try {
			v = Integer.valueOf((val+"").trim());
		} catch (Exception e) {
		}
		return v;
	}
	
	/**
	 * Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	 * @param map
	 * @param obj
	 */
	public static void mapToBean(Map<String, Object> map, Object obj) {

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				if (map.containsKey(key)) {
					Object value = map.get(key);
					// 得到property对应的setter方法
					Method setter = property.getWriteMethod();
					setter.invoke(obj, value);
				}

			}

		} catch (Exception e) {
			System.out.println("transMap2Bean Error " + e);
		}

		return;

	}
	
	/**
	 * Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map  
	 * @param map
	 * @param obj
	 */
	public static Map<String, Object> beanToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}
		return map;
	}
	
}
