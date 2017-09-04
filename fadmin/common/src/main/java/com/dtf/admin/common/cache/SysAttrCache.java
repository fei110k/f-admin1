package com.dtf.admin.common.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.common.utils.SpringUtils;
import com.dtf.admin.manage.service.SysAttrBo;
import com.dtf.admin.manage.vo.SysAttr;
import com.dtf.admin.manage.vo.SysAttrValue;

public class SysAttrCache {
	
	/**
	 * 获取attr对象，包含attr_value对象列表
	 * 如果在缓存中没有找到，将自动从数据库去获取
	 * @param attr_code
	 * @return
	 */
	public static SysAttr getSysAttr(String attr_code){
		String key = CacheUtils.CACHE_WORKSPACE_ATTR+"_"+attr_code;
		SysAttr attr = CacheUtils.getDistributedInst().get(key);
		if (attr == null) {
			SysAttrBo sysAttrBo = SpringUtils.getBean(SysAttrBo.class);
			Map param = new HashMap();
			param.put("attr_code", attr_code);
			Map attrMap = sysAttrBo.findSysAttrById(param);
			List<Map<String, String>> attr_values = (List<Map<String, String>>) attrMap.get("attr_values");
			attr = new SysAttr();
			attr.setAttr_code(attr_code);
			attr.setAttr_name(MapUtils.getString(attrMap, "attr_name"));
			
			List<SysAttrValue> values = new ArrayList<SysAttrValue>();
			for (Map map : attr_values) {
				SysAttrValue value = new SysAttrValue();
				value.setAttr_value(MapUtils.getString(map, "attr_value"));
				value.setAttr_value_name(MapUtils.getString(map, "attr_value_name"));
				values.add(value);
			}
			attr.setAttrValues(values);
			CacheUtils.getLocalInst().set(key, attr);
		}
		return attr;
	}
	
	/**
	 * 根据key删除缓存
	 * @param attr_code
	 */
	public static void removeSysAttr(String attr_code){
		String key = CacheUtils.CACHE_WORKSPACE_ATTR+"_"+attr_code;
		CacheUtils.getDistributedInst().del(key);
	}
}
