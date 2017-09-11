package com.dtf.admin.common.utils.mybatis;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.dtf.admin.common.utils.MapUtils;
import com.github.pagehelper.Page;
//import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 数据库工具类
 * 继承SqlSessionTemplate，得到此工具类中的原有方法
 * @author
 *
 */
@Service
public class DaoUtils {
	
//	public DaoUtils(SqlSessionFactory sqlSessionFactory) {
//		super(sqlSessionFactory);
//	}
	
	@Resource  
    private SqlSessionTemplate sqlSessionTemplate;  
	
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public String pageNumStr = "pageNum";
	public String pageSizeStr = "pageSize";
	
	/***
	 * 查询分页信息
	 * @param statement sql映射名称.id
	 * @param pageNum 当前页
	 * @param pageSize 每页条数
	 * @return
	 */
	public PageInfo selectPage(String statement,int pageNum,int pageSize){
		return this.selectPage(statement, null, pageNum, pageSize);
	}
	
	/***
	 * 查询分页信息
	 * @param statement sql映射名称.id
	 * @param param 查询条件,param里边必须包含pageNum和pageSize，否则会报错
	 * @return
	 */
	public PageInfo selectPage(String statement,Map param){
		int pageNum = MapUtils.getInt(param, pageNumStr);
		int pageSize = MapUtils.getInt(param, pageSizeStr);
		if (pageNum == 0 || pageSize == 0) {
			throw new RuntimeException(
					"param is not for:pageNum="+MapUtils.getString(param, pageNumStr) +
					",pageSize="+MapUtils.getString(param, pageSizeStr));
		}
		return this.selectPage(statement, param,pageNum , pageSize);
	}
	/***
	 * 查询分页信息基础方法
	 * @param statement SQL映射路径.ID
	 * @param param 参数
	 * @param pageNum 查询第多个页
	 * @param pageSize 每页多少条数据
	 * @return
	 */
	public PageInfo selectPage(final String statement,final Object param,int pageNum,int pageSize){
		return selectPage(statement, param, true, pageNum, pageSize);
	}
	/***
	 * 查询分页数据，不查询数据总条数
	 * @param statement SQL映射路径.ID
	 * @param param 参数
	 * @param pageNum 查询第多个页
	 * @param pageSize 每页多少条数据
	 * @return
	 */
	public PageInfo selectPageNoCount(final String statement,final Object param,int pageNum,int pageSize){
		return selectPage(statement, param, false, pageNum, pageSize);
	}
	
	/***
	 * 查询分页数据，不查询数据总条数
	 * @param statement SQL映射路径.ID
	 * @param param 参数
	 * @param pageNum 查询第多个页
	 * @param pageSize 每页多少条数据
	 * @return
	 */
	public PageInfo selectPageNoCount(final String statement,int pageNum,int pageSize){
		return selectPage(statement, null, false, pageNum, pageSize);
	}
	
	/***
	 * 查询分页数据,不查询总条数
	 * @param statement sql映射名称.id
	 * @param param 查询条件,param里边必须包含pageNum和pageSize，否则会报错
	 * @return
	 */
	public PageInfo selectPageNoCount(String statement,Map param){
		int pageNum = MapUtils.getInt(param, pageNumStr);
		int pageSize = MapUtils.getInt(param, pageSizeStr);
		if (pageNum == 0 || pageSize == 0) {
			throw new RuntimeException(
					"param is not for:pageNum="+MapUtils.getString(param, pageNumStr) +
					",pageSize="+MapUtils.getString(param, pageSizeStr));
		}
		return selectPageNoCount(statement, param,pageNum , pageSize);
	}
	/***
	 * 查询分页信息基础方法
	 * @param statement SQL映射路径.ID
	 * @param param 参数
	 * @param selectCount 是否进行count查询
	 * @param pageNum 查询第多个页
	 * @param pageSize 每页多少条数据
	 * @return
	 */
	public PageInfo selectPage(final String statement,final Object param,boolean selectCount,int pageNum,int pageSize){
		final DaoUtils daoUtils = this;
//		Page page = PageHelper.startPage(pageNum, pageSize,selectCount);
		PageHelper.startPage(pageNum, pageSize,selectCount);
		List list = daoUtils.sqlSessionTemplate.selectList(statement,param);
//		PageInfo info = PageHelper.startPage(pageNum, pageSize,selectCount).doSelectPageInfo(new ISelect() {
//		    @Override
//		    public void doSelect(){
//		    	daoUtils.selectList(statement,param);
//		    }
//		});
		PageInfo page= new PageInfo(list);
//		return info;
		return page;
	}
}
