package com.dtf.admin.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dtf.admin.common.ReturnVO;
import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.common.utils.StringUtil;
import com.dtf.admin.common.utils.UUIDGenerator;
import com.dtf.admin.common.utils.mybatis.DaoUtils;
import com.dtf.admin.manage.service.SysMenuBo;
import com.github.pagehelper.PageInfo;

@Service
public class SysMenuBoImpl implements SysMenuBo{
	
	private static final Log log = LogFactory.getLog(SysMenuBoImpl.class);
	
	@Autowired
	private DaoUtils daoUtils;
	
	@Override
	public List findMenuBtns(Map param){
		
		List<Map<String, Object>> list = 
				daoUtils.getSqlSessionTemplate().selectList("SysMenu.findMenuBtns");
		String pic10 = "/public/lib/H-ui.admin/lib/zTree/v3/css/zTreeStyle/img/diy/10.png";
		String pic13 = "/public/lib/H-ui.admin/lib/zTree/v3/css/zTreeStyle/img/diy/13.png";
		//如果只是页面的情况下，更改页面的图标
		for (Map<String, Object> map : list) {
			String type = MapUtils.getString(map, "type");
			String menuId = MapUtils.getString(map, "id");
			//默认展开全部菜单
			map.put("open", "true");
			
			//按钮图标
			if ("btn".equals(type)) {
				map.put("icon",pic10);
				continue;
			}
			
			if ("-1".equals(menuId)) {
				continue;
			}
			
			for (int i = 0; i < list.size(); i++) {
				Map map2 = list.get(i);
				String type2 = MapUtils.getString(map2, "type");
				String pid = MapUtils.getString(map2, "pid");
				if(i == list.size()-1){
					if("btn".equals(type2)){
						map.put("icon", pic13);
					}else if(!menuId.equals(pid)){
						map.put("icon", pic13);
					}
				}
				if(menuId.equals(pid) && "menu".equals(type2)){
					break;
				}
				
			}
		}
		
		return list;
	}
	
	@Override
	public PageInfo findSysMenu(Map param) {
		// TODO Auto-generated method stub
		return daoUtils.selectPage("SysMenu.findSysMenu", param);
	}
	
	@Override
	public Map findSysMenuById(Map param) {
		// TODO Auto-generated method stub
		Map req = daoUtils.getSqlSessionTemplate().selectOne("SysMenu.findSysMenu", param);
		List list = daoUtils.getSqlSessionTemplate().selectList("SysMenu.findSysMenuChildren", param);
		req.put("menu_children_urls", list);
		return req;
	}
	
	@Override
	public ReturnVO insertSysMenu(Map param) {
		ReturnVO req = new ReturnVO();
		String menu_id = UUIDGenerator.getInst().getUUID32();
		param.put("menu_id", menu_id);
		String parent_id = MapUtils.getString(param, "parent_id");
		
		Map queryParentParam = new HashMap();
		queryParentParam.put("menu_id", parent_id);
		Map parent_menu = daoUtils.getSqlSessionTemplate().selectOne("SysMenu.findSysMenu", queryParentParam);
		String menu_path = MapUtils.getString(parent_menu, "menu_path")+"."+menu_id;
		int menu_level = MapUtils.getInt(parent_menu, "menu_level")+1;
		
		param.put("menu_path", menu_path);
		param.put("menu_level", menu_level);
		
		int update_count = daoUtils.getSqlSessionTemplate().insert("SysMenu.insertSysMenu", param);
		
		
		String menu_children_urls = MapUtils.getString(param, "menu_children_urls");
		String[] urls = menu_children_urls.split(",");
		for (int i = 0; i < urls.length; i++) {
			Map m = new HashMap();
			m.put("menu_children_url", urls[i]);
			m.put("menu_id", menu_id);
			daoUtils.getSqlSessionTemplate().insert("SysMenu.insertSysMenuChildrenById", m);
		}
		
		
		return req;
	}

	@Override
	@Transactional
	public ReturnVO updateSysMenuByConn(Map param) {
		ReturnVO req = new ReturnVO();
		
		//页面防XSS攻击特殊处理
		String class_style = MapUtils.getString(param, "class_style");
		class_style = class_style.replaceAll("＆", "&");
		param.put("class_style", class_style);
		
		int update_count = daoUtils.getSqlSessionTemplate().update("SysMenu.updateSysMenuByConn", param);
		
		daoUtils.getSqlSessionTemplate().delete("SysMenu.deleteSysMenuChildrenById", param);
		
		String menu_id = MapUtils.getString(param, "menu_id");
		String menu_children_urls = MapUtils.getString(param, "menu_children_urls");
		
		String[] urls = menu_children_urls.split(",");
		for (int i = 0; i < urls.length; i++) {
			if (StringUtil.isEmpty(urls[i])) {
				continue;
			}
			Map m = new HashMap();
			m.put("menu_children_url", urls[i]);
			m.put("menu_id", menu_id);
			daoUtils.getSqlSessionTemplate().insert("SysMenu.insertSysMenuChildrenById", m);
		}
		
		return req;
	}

	@Override
	@Transactional
	public ReturnVO deleteSysMenuById(Map param) {
		ReturnVO req = new ReturnVO();
		//先删除按钮，再删除菜单
		daoUtils.getSqlSessionTemplate().delete("SysButton.deleteSysButtonByMenuId", param);
		daoUtils.getSqlSessionTemplate().delete("SysMenu.deleteSysMenuById", param);
		
		daoUtils.getSqlSessionTemplate().delete("SysMenu.deleteSysMenuChildrenById", param);
		return req;
	}

	@Override
	public ReturnVO deleteSysMenuByIds(Map param) {
		ReturnVO req = new ReturnVO();
		int update_count = daoUtils.getSqlSessionTemplate().delete("SysMenu.deleteSysMenuByIds", param);
		return req;
	}
	
}
