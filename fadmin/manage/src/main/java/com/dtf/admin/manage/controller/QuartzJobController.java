package com.dtf.admin.manage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dtf.admin.common.ReturnVO;
import com.dtf.admin.manage.service.QuartzJobBo;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/QuartzJob")
public class QuartzJobController {
	
	@Autowired
	private QuartzJobBo quartzJobBo;
	
	/**
	 * 查询定时任务（分页）
	 */
	@ResponseBody
	@RequestMapping(value="/queryJobInfo")
	public PageInfo queryJobInfo(@RequestParam Map param){
		return quartzJobBo.queryJobInfo(param);
	}
	
	/**
	 * 根据定时任务参数查询定时任务
	 */
	@ResponseBody
	@RequestMapping(value="/queryJobInfoByConn")
	public Map queryJobInfoByConn(@RequestParam Map param){
		return quartzJobBo.queryJobInfoByConn(param);
	}

	/**
	 * 保存定时任务信息
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/saveJobInfo")
	public ReturnVO saveJobInfo(@RequestParam Map param) throws Exception{
		return quartzJobBo.saveJobInfo(param);
	}

	/**
	 * 删除定时任务信息
	 */
	@ResponseBody
	@RequestMapping(value="/deleteJobInfo")
	public ReturnVO deleteJobInfo(@RequestParam Map param)throws Exception{
		return quartzJobBo.deleteJobInfo(param);
	}

	/**
	 * 暂停定时任务
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/pauseJob")
	public ReturnVO pauseJob(@RequestParam Map param) throws Exception{
		return quartzJobBo.pauseJob(param);
	}

	/**
	 * 恢复定时任务
	 */
	@ResponseBody
	@RequestMapping(value="/resumeJob")
	public ReturnVO resumeJob(@RequestParam Map param)throws Exception{
		return quartzJobBo.resumeJob(param);
	}
	
	/**
	 * 立刻执行指定定时任务
	 */
	@ResponseBody
	@RequestMapping(value="/triggerJob")
	public ReturnVO triggerJob(@RequestParam Map param)throws Exception{
		return quartzJobBo.triggerJob(param);
	}
}
