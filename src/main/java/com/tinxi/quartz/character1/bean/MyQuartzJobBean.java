package com.tinxi.quartz.character1.bean;

import com.tinxi.quartz.character1.service.SimpleService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @ClassName MyQuartzJobBean
 * @Dsecription TODO
 * @Auth tinxi
 * @DATAE 2019/4/11 13:41
 */
public class MyQuartzJobBean extends QuartzJobBean {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		Trigger trigger = context.getTrigger();
		String triggerName = trigger.getKey().getName();

		SimpleService simpleService = getApplicationContext(context).getBean("simpleService",
				SimpleService.class);
		simpleService.testMethod(triggerName);
	}

	private BeanFactory getApplicationContext(JobExecutionContext context) {
		try {
			return (ApplicationContext) context.getScheduler().getContext().get("applicationContextKey");
		} catch (SchedulerException e) {
			logger.error("jobexecutioncontext.getScheduler().getContext() error!", e);
			throw new RuntimeException(e);
		}
	}
}
