package com.dtf.admin.manage.service;

import java.util.Map;

import com.dtf.admin.common.ReturnVO;
import com.github.pagehelper.PageInfo;

/**
 * 定时任务管理接口类
 *
 */
public interface QuartzJobBo{

	/**
	 * 查询定时任务（分页）
	 */
	public PageInfo queryJobInfo(Map<String, Object> params);
	
	/**
	 * 查询定时任务（分页）
	 */
	public Map queryJobInfoByConn(Map<String, Object> params);
	
	/**
	 * 保存定时任务信息
	 * @throws Exception 
	 */
	public ReturnVO saveJobInfo(Map params) throws Exception;

	/**
	 * 删除定时任务信息
	 * @throws Exception 
	 */
	public ReturnVO deleteJobInfo(Map<String, Object> params) throws Exception;

	/**
	 * 暂停定时任务
	 * @throws SchedulerException 
	 */
	public ReturnVO pauseJob(Map<String, String> params) throws Exception;

	/**
	 * 恢复定时任务
	 */
	public ReturnVO resumeJob(Map<String, String> params)throws Exception;
	
	/**
	 * 立刻执行指定定时任务
	 */
	public ReturnVO triggerJob(Map<String, String> params)throws Exception;

}