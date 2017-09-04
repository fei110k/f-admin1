package com.dtf.admin.manage.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.dtf.admin.common.log.Log;
import com.dtf.admin.common.utils.DateUtil;
/**
 * 此类为定时任务quartz示例类，可直接复制此类进行开发
 * 必须放在service层来处理，因为quartz集群能够运行的条件就是能够连接到数据库
 * @author fei
 *
 */

@DisallowConcurrentExecution //添加此注解后job执行就会阻塞，在上一次job执行完毕后再去执行下一次job
public class JobDemo implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		Log.info("\n"+DateUtil.getFormatedDateTime()+"示例定时任务类开始执行");
	}

}
