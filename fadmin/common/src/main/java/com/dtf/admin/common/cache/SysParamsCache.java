package com.dtf.admin.common.cache;

import java.util.HashMap;
import java.util.Map;

import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.common.utils.SpringUtils;
import com.dtf.admin.manage.service.SysParamsBo;
import com.dtf.admin.manage.vo.SysParams;

public class SysParamsCache {
	/**
	 * 获取attr对象，包含attr_value对象列表
	 * 如果在缓存中没有找到，将自动从数据库去获取
	 * @param param_code
	 * @return
	 */
	public static SysParams getSysParams(String param_code){
		String key = CacheUtils.CACHE_WORKSPACE_SYS_PARAM+"_"+param_code;
		SysParams params = CacheUtils.getDistributedInst().get(key);
		if (params == null) {
			SysParamsBo sysParamsBo = SpringUtils.getBean(SysParamsBo.class);
			Map param = new HashMap();
			param.put("param_code", param_code);
			Map attrMap = sysParamsBo.findSysParamsById(param);
			if (MapUtils.isEmpty(attrMap)) {
				return null;
			}
			params = new SysParams();
			MapUtils.mapToBean(attrMap, params);
			CacheUtils.getLocalInst().set(key, params);
		}
		return params;
	}
	
	
	/**
	 * 获取attr对象，包含attr_value对象列表
	 * 如果在缓存中没有找到，将自动从数据库去获取
	 * @param param_code
	 * @return
	 */
	public static String getSysParamVal(String param_code){
		SysParams params = getSysParams(param_code);
		if (params == null) {
			return null;
		}
		return params.getParam_val();
	}
	
	/**
	 * 根据key删除缓存
	 * @param param_code
	 */
	public static void removeSysParams(String param_code){
		String key = CacheUtils.CACHE_WORKSPACE_SYS_PARAM+"_"+param_code;
		CacheUtils.getDistributedInst().del(key);
	}
}
