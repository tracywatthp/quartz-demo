package com.tinxi.quartz.character1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName SimpleService
 * @Dsecription 主要执行定时调度业务，在这里我只是简单打印一下log日志。
 * @Auth tinxi
 * @DATAE 2019/4/11 13:43
 */
@Service
public class SimpleService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void testMethod(String triggerName) {
		// 这里执行定时调度业务
		logger.info(triggerName);
	}

}
