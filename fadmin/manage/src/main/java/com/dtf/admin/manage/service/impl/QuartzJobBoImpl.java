package com.dtf.admin.manage.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.dtf.admin.common.ReturnVO;
import com.dtf.admin.common.log.Log;
import com.dtf.admin.common.utils.DateUtil;
import com.dtf.admin.common.utils.MapUtils;
import com.dtf.admin.common.utils.StringUtil;
import com.dtf.admin.common.utils.mybatis.DaoUtils;
import com.dtf.admin.manage.service.QuartzJobBo;
import com.github.pagehelper.PageInfo;


@Service
public class QuartzJobBoImpl implements QuartzJobBo{
	
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	
	@Autowired
	private DaoUtils daoUtils;
	
	@Override
	public PageInfo queryJobInfo(Map<String, Object> params) {
		String job_name = MapUtils.getString(params, "job_name");
		params.put("job_name", "%"+job_name+"%");
		PageInfo page = daoUtils.selectPage("QuartzJob.queryJobInfo", params);
		List<Map> list = page.getList();
		for (Map map : list) {
			String prev_fire_time = MapUtils.getString(map, "prev_fire_time");
			if (StringUtil.isNotEmpty(prev_fire_time)) {
				map.put("prev_fire_time", DateUtil.formatDate(new Date(Long.valueOf(prev_fire_time)), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
			}
			
			String next_fire_time = MapUtils.getString(map, "next_fire_time");
			if (StringUtil.isNotEmpty(next_fire_time)) {
				map.put("next_fire_time", 
						DateUtil.formatDate(new Date(Long.valueOf(next_fire_time)), 
								DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
			}
			
			String start_time = MapUtils.getString(map, "start_time");
			if (StringUtil.isNotEmpty(start_time)) {
				map.put("start_time", 
						DateUtil.formatDate(new Date(Long.valueOf(start_time)), 
						DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
			}
		}
		return page;
	}
	@Override
	public ReturnVO saveJobInfo(Map params) throws Exception {
		ReturnVO returnVO = new ReturnVO();
		String edit_type = MapUtils.getString(params, "edit_type");
		String cron_expression = MapUtils.getString(params, "cron_expression");
		String job_name = MapUtils.getString(params, "job_name");
		String job_class_name = MapUtils.getString(params, "job_class_name");
		Class<Job> clazz = null;
		try {
			clazz = (Class<Job>)Class.forName(job_class_name);
		} catch (Exception e) {
			returnVO.setCode("9999");
			returnVO.setMsg("类：“"+job_class_name+"”无法被初始化，请确认项目中是否包含此类！");
			return returnVO;
		}
		if ("E".equals(edit_type)) {
			String job_group = MapUtils.getString(params, "job_group");
			String trigger_name = MapUtils.getString(params, "trigger_name");
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			
			TriggerKey triggerKey = TriggerKey.triggerKey(trigger_name,job_group);
			//获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			//表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron_expression);
			//按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
			    .withSchedule(scheduleBuilder).build();
			//按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}else if ("A".equals(edit_type)) {
			
			Map map = daoUtils.getSqlSessionTemplate().selectOne("QuartzJob.queryJobInfoByConn", params);
			if (!MapUtils.isEmpty(map)) {
				returnVO.setCode("9999");
				returnVO.setMsg("任务名称“"+job_name+"”重复，请重新输入一个任务名称！ ");
				return returnVO;
			}
			
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			
			// 创建jobDetail实例，绑定Job实现类
			// 指明job的名称，所在组的名称，以及绑定job类
			JobDetail job = JobBuilder.newJob(clazz).withIdentity(job_name).build();
			Trigger trigger= TriggerBuilder.newTrigger().withIdentity(job_name+"_triggerName")
					.withSchedule(CronScheduleBuilder.cronSchedule(cron_expression))
					.startNow().build();
			
			// 把作业和触发器注册到任务调度中
			scheduler.scheduleJob(job, trigger);
		}
        
		return returnVO;
	}

	@Override
	public ReturnVO deleteJobInfo(Map<String, Object> params)throws Exception {
		ReturnVO returnVO = new ReturnVO();
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(MapUtils.getString(params, "job_name"), MapUtils.getString(params, "job_group"));
		scheduler.deleteJob(jobKey);
		return returnVO;
	}

	@Override
	public ReturnVO pauseJob(Map<String, String> params) throws Exception {
		Log.info("暂停任务："+MapUtils.getString(params, "job_name"));
		ReturnVO returnVO = new ReturnVO();
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(MapUtils.getString(params, "job_name"), MapUtils.getString(params, "job_group"));
		scheduler.pauseJob(jobKey);
		return returnVO;
	}

	@Override
	public ReturnVO resumeJob(Map<String, String> params) throws Exception{
		Log.info("恢复任务："+MapUtils.getString(params, "job_name"));
		ReturnVO returnVO = new ReturnVO();
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(MapUtils.getString(params, "job_name"), MapUtils.getString(params, "job_group"));
		scheduler.resumeJob(jobKey);
		return returnVO;
	}

	@Override
	public ReturnVO triggerJob(Map<String, String> params) throws Exception{
		Log.info("立即执行任务："+MapUtils.getString(params, "job_name"));
		ReturnVO returnVO = new ReturnVO();
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(MapUtils.getString(params, "job_name"), MapUtils.getString(params, "job_group"));
		scheduler.triggerJob(jobKey);
		return returnVO;
	}
	@Override
	public Map queryJobInfoByConn(Map<String, Object> params) {
		Map map = daoUtils.getSqlSessionTemplate().selectOne("QuartzJob.queryJobInfoByConn", params);
		if (!MapUtils.isEmpty(map)) {
			String prev_fire_time = MapUtils.getString(map, "prev_fire_time");
			if (StringUtil.isNotEmpty(prev_fire_time)) {
				map.put("prev_fire_time", DateUtil.formatDate(new Date(Long.valueOf(prev_fire_time)), DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
			}
			
			String next_fire_time = MapUtils.getString(map, "next_fire_time");
			if (StringUtil.isNotEmpty(next_fire_time)) {
				map.put("next_fire_time", 
						DateUtil.formatDate(new Date(Long.valueOf(next_fire_time)), 
								DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
			}
			
			String start_time = MapUtils.getString(map, "start_time");
			if (StringUtil.isNotEmpty(start_time)) {
				map.put("start_time", 
						DateUtil.formatDate(new Date(Long.valueOf(start_time)), 
						DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS));
			}
		}
		return map;
	}
	
}