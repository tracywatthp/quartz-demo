package com.tinxi.quartz.character1.controller;

import com.tinxi.quartz.character1.service.ISchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName TestController
 * @Dsecription
 * @Auth tinxi
 * @DATAE 2019/4/11 12:11
 */
@RestController
public class TestController {

	@Autowired
	private ISchedulerService schedulerService;

	@GetMapping("/getTest")
	public String getTest() {
		// ISchedulerService schedulerService = SpringContextUtils.getBean(SchedulerServiceImpl.class);

		// 执行业务逻辑...

		// 设置高度任务
		// 每10秒中执行调试一次
		schedulerService.schedule("0/10 * * ? * * *");

		Date startTime = parse("2014-08-19 16:33:00");
		Date endTime = parse("2014-08-22 21:10:00");

		// 2014-08-19 16:33:00开始执行调度
		schedulerService.schedule(startTime);

		// 2014-08-19 16:33:00开始执行调度，2014-08-22 21:10:00结束执行调试
		schedulerService.schedule(startTime, endTime);

		// 2014-08-19 16:33:00开始执行调度，执行5次结束
		schedulerService.schedule(startTime, 5);

		// 2014-08-19 16:33:00开始执行调度，每隔20秒执行一次，执行5次结束
		schedulerService.schedule(startTime, 5, 20);

		// 等等，查看com.sundoctor.quartz.service.SchedulerService
		return "success";
	}

	private Date parse(String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}


}
