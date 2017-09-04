package com.dtf.admin.common.cache.ehcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import com.dtf.admin.common.cache.CacheUtils;
import com.dtf.admin.common.log.Log;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Service(value="localCacheUtil")
public class EhCacheUtil extends CacheUtils{
	
	private static Cache cache = null;
	
	private Cache getCache(){
		if (cache == null) {
			cache = CacheManager.getInstance().getCache("MyCache");
		}
		return cache;
	}
	@Override
	public String set(String key, Object value) {
		Element element = new Element(key, value);
		getCache().put(element);
		return null;
	}

	@Override
	public String set(String key, Object value, int effTime) {
		Log.info("本地缓存不支持时间控制");
		return null;
	}

	@Override
	public <E> E get(String key) {
		Element element = getCache().get(key);
		if (element == null) {
			return null;
		}
		return (E) element.getObjectValue();
	}

	@Override
	public Long del(String key) {
		getCache().remove(key);
		return null;
	}

	@Override
	public Long incr(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long incr(String key, long number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long decr(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long decr(String key, long number) {
		// TODO Auto-generated method stub
		return null;
	}

}
