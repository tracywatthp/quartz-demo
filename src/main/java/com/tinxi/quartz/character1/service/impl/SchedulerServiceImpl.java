package com.tinxi.quartz.character1.service.impl;

import com.tinxi.quartz.character1.service.ISchedulerService;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName SchedulerServiceImpl
 * @Dsecription SchedulerService 只有一个多态方法schedule，SchedulerServiceImpl实现SchedulerService接口，
 * 注入org.quartz.Schedulert和org.quartz.JobDetail，schedule方法可以动态配置org.quartz.CronExpression或org.quartz.SimpleTrigger调度时间。
 * @Auth tinxi
 * @DATAE 2019/4/11 13:52
 */
@Service
public class SchedulerServiceImpl implements ISchedulerService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String NULLSTRING = null;
	private static final Date NULLDATE = null;

	@Autowired
	private Scheduler scheduler;

	@Autowired
	private JobDetail jobDetail;

	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
	 */
	@Override
	public void schedule(String cronExpression) {
		schedule(NULLSTRING, cronExpression);
	}

	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * @param name           Quartz CronTrigger名称
	 * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
	 */
	@Override
	public void schedule(String name, String cronExpression) {
		schedule(name, NULLSTRING, cronExpression);
	}

	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * @param name           Quartz CronTrigger名称
	 * @param group          Quartz CronTrigger组
	 * @param cronExpression Quartz Cron 表达式，如 "0/10 * * ? * * *"等
	 */
	@Override
	public void schedule(String name, String group, String cronExpression) {
		try {
			schedule(name, group, new CronExpression(cronExpression));
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * @param cronExpression Quartz CronExpression
	 */
	@Override
	public void schedule(CronExpression cronExpression) {
		schedule(NULLSTRING, cronExpression);
	}

	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * @param name           Quartz CronTrigger名称
	 * @param cronExpression Quartz CronExpression
	 */
	@Override
	public void schedule(String name, CronExpression cronExpression) {
		schedule(name, NULLSTRING, cronExpression);
	}

	/**
	 * 根据 Quartz Cron Expression 调试任务
	 * @param name           Quartz CronTrigger名称
	 * @param group          Quartz CronTrigger组
	 * @param cronExpression Quartz CronExpression
	 */
	@Override
	public void schedule(String name, String group, CronExpression cronExpression) {
		if (isValidExpression(cronExpression)) {
			if (name == null || name.trim().equals("")) {
				name = UUID.randomUUID().toString();
			}

			CronTriggerImpl trigger = new CronTriggerImpl();
			trigger.setCronExpression(cronExpression);

			TriggerKey triggerKey = new TriggerKey(name, group);

			trigger.setJobName(jobDetail.getKey().getName());
			trigger.setKey(triggerKey);

			try {
				scheduler.addJob(jobDetail, true);
				if (scheduler.checkExists(triggerKey)) {
					scheduler.rescheduleJob(triggerKey, trigger);
				} else {
					scheduler.scheduleJob(trigger);
				}
			} catch (SchedulerException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	/**
	 * 在startTime时执行调试一次
	 * @param startTime 调度开始时间
	 */
	@Override
	public void schedule(Date startTime) {
		schedule(startTime, NULLDATE);
	}

	@Override
	public void schedule(Date startTime, String group) {
		schedule(startTime, NULLDATE, group);
	}

	/**
	 * 在startTime时执行调试一次
	 * @param name      Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 */
	@Override
	public void schedule(String name, Date startTime) {
		schedule(name, startTime, NULLDATE);
	}

	@Override
	public void schedule(String name, Date startTime, String group) {
		schedule(name, startTime, NULLDATE, group);
	}

	/**
	 * 在startTime时执行调试，endTime结束执行调度
	 * @param startTime 调度开始时间
	 * @param endTime   调度结束时间
	 */
	@Override
	public void schedule(Date startTime, Date endTime) {
		schedule(startTime, endTime, 0);
	}

	@Override
	public void schedule(Date startTime, Date endTime, String group) {
		schedule(startTime, endTime, 0, group);
	}

	/**
	 * 在startTime时执行调试，endTime结束执行调度
	 * @param name      Quartz SimpleTrigger 名称
	 * @param startTime 调度开始时间
	 * @param endTime   调度结束时间
	 */
	@Override
	public void schedule(String name, Date startTime, Date endTime) {
		schedule(name, startTime, endTime, 0);
	}

	@Override
	public void schedule(String name, Date startTime, Date endTime, String group) {
		schedule(name, startTime, endTime, 0, group);
	}

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次
	 * @param startTime   调度开始时间
	 * @param repeatCount 重复执行次数
	 */
	@Override
	public void schedule(Date startTime, int repeatCount) {
		schedule(null, startTime, NULLDATE, 0);
	}

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次
	 * @param startTime   调度开始时间
	 * @param endTime     调度结束时间
	 * @param repeatCount 重复执行次数
	 */
	@Override
	public void schedule(Date startTime, Date endTime, int repeatCount) {
		schedule(null, startTime, endTime, 0);
	}

	@Override
	public void schedule(Date startTime, Date endTime, int repeatCount, String group) {
		schedule(null, startTime, endTime, 0, group);
	}

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次
	 * @param name        Quartz SimpleTrigger 名称
	 * @param startTime   调度开始时间
	 * @param endTime     调度结束时间
	 * @param repeatCount 重复执行次数
	 */
	@Override
	public void schedule(String name, Date startTime, Date endTime, int repeatCount) {
		schedule(name, startTime, endTime, 0, 0L);
	}

	@Override
	public void schedule(String name, Date startTime, Date endTime, int repeatCount, String group) {
		schedule(name, startTime, endTime, 0, 0L, group);
	}

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * @param startTime      调度开始时间
	 * @param repeatCount    重复执行次数
	 * @param repeatInterval 执行时间隔间
	 */
	@Override
	public void schedule(Date startTime, int repeatCount, long repeatInterval) {
		schedule(null, startTime, NULLDATE, repeatCount, repeatInterval);
	}

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * @param startTime      调度开始时间
	 * @param endTime        调度结束时间
	 * @param repeatCount    重复执行次数
	 * @param repeatInterval 执行时间隔间
	 */
	@Override
	public void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval) {
		schedule(null, startTime, endTime, repeatCount, repeatInterval);
	}

	@Override
	public void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval, String group) {
		schedule(null, startTime, endTime, repeatCount, repeatInterval, group);
	}

	/**
	 * 在startTime时执行调试，endTime结束执行调度，重复执行repeatCount次，每隔repeatInterval秒执行一次
	 * @param name           Quartz SimpleTrigger 名称
	 * @param startTime      调度开始时间
	 * @param endTime        调度结束时间
	 * @param repeatCount    重复执行次数
	 * @param repeatInterval 执行时间隔间
	 */
	@Override
	public void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval) {
		schedule(name, startTime, endTime, repeatCount, repeatInterval, NULLSTRING);
	}

	@Override
	public void schedule(String name, Date startTime, Date endTime, int repeatCount, long repeatInterval, String group) {
		if (this.isValidExpression(startTime)) {

			if (name == null || name.trim().equals("")) {
				name = UUID.randomUUID().toString();
			}

			TriggerKey triggerKey = new TriggerKey(name, group);

			SimpleTriggerImpl trigger = new SimpleTriggerImpl();
			trigger.setKey(triggerKey);
			trigger.setJobName(jobDetail.getKey().getName());

			trigger.setStartTime(startTime);
			trigger.setEndTime(endTime);
			trigger.setRepeatCount(repeatCount);
			trigger.setRepeatInterval(repeatInterval);

			try {
				scheduler.addJob(jobDetail, true);
				if (scheduler.checkExists(triggerKey)) {
					scheduler.rescheduleJob(triggerKey, trigger);
				} else {
					scheduler.scheduleJob(trigger);
				}
			} catch (SchedulerException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	/**
	 * 暂停触发器
	 * @param triggerName 触发器名称
	 */
	@Override
	public void pauseTrigger(String triggerName) {
		pauseTrigger(triggerName, NULLSTRING);
	}

	/**
	 * 暂停触发器
	 * @param triggerName 触发器名称
	 * @param group       触发器组
	 */
	@Override
	public void pauseTrigger(String triggerName, String group) {
		try {
			scheduler.pauseTrigger(new TriggerKey(triggerName, group));// 停止触发器
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 恢复触发器
	 * @param triggerName 触发器名称
	 */
	@Override
	public void resumeTrigger(String triggerName) {
		resumeTrigger(triggerName, NULLSTRING);
	}

	/**
	 * 恢复触发器
	 * @param triggerName 触发器名称
	 * @param group       触发器组
	 */
	@Override
	public void resumeTrigger(String triggerName, String group) {
		try {
			scheduler.resumeTrigger(new TriggerKey(triggerName, group));// 重启触发器
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除触发器
	 * @param triggerName 触发器名称
	 * @return
	 */
	@Override
	public boolean removeTrigdger(String triggerName) {
		return removeTrigdger(triggerName, NULLSTRING);
	}

	/**
	 * 删除触发器
	 * @param triggerName 触发器名称
	 * @param group       触发器组
	 * @return
	 */
	@Override
	public boolean removeTrigdger(String triggerName, String group) {
		TriggerKey triggerKey = new TriggerKey(triggerName, group);
		try {
			scheduler.pauseTrigger(triggerKey);// 停止触发器
			return scheduler.unscheduleJob(triggerKey);// 移除触发器
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean isValidExpression(final CronExpression cronExpression) {

		CronTriggerImpl trigger = new CronTriggerImpl();
		trigger.setCronExpression(cronExpression);

		Date date = trigger.computeFirstFireTime(null);

		return date != null && date.after(new Date());
	}

	private boolean isValidExpression(final Date startTime) {

		SimpleTriggerImpl trigger = new SimpleTriggerImpl();
		trigger.setStartTime(startTime);

		Date date = trigger.computeFirstFireTime(null);

		return date != null && date.after(new Date());
	}
}
