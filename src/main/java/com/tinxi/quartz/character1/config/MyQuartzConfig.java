package com.tinxi.quartz.character1.config;

import com.tinxi.quartz.character1.bean.MyQuartzJobBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @ClassName MyConfig
 * @Dsecription TODO
 * @Auth tinxi
 * @DATAE 2019/4/11 14:06
 */
@Configuration
public class MyQuartzConfig {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
		<bean name="quartzScheduler" class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
	        <property name="dataSource">
	            <ref bean="dataSource" />
	        </property>
	        <property name="applicationContextSchedulerContextKey" value="applicationContextKey" />
	        <property name="configLocation" value="classpath:quartz.properties" />
	    </bean>

	    <bean id="jobDetail" class="org.springframework.scheduling.quartz.JobDetailFactoryBean">
	        <property name="jobClass">
	            <value>com.sundoctor.example.service.MyQuartzJobBean</value>
	        </property>
	        <property name="durability" value="true" />
	    </bean>
	 */
	@Bean
	public SchedulerFactoryBean getSchedulerFactoryBean(DataSource dataSource) throws IOException {
		SchedulerFactoryBean bean = new SchedulerFactoryBean();
		bean.setDataSource(dataSource);
		bean.setApplicationContextSchedulerContextKey("applicationContextKey");
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		// Resource resource = resolver.getResource("quartz.properties");
		bean.setConfigLocation(resolver.getResource("quartz.properties"));

		// Properties p = new Properties();
		// try {
		// 	p.load(this.getClass().getClassLoader().getResourceAsStream("quartz.properties"));
		// } catch (IOException e) {
		// 	this.logger.error("加载quartz.properties失败", e);
		// 	throw new Error(e);
		// }
		// bean.setQuartzProperties(p);

		return bean;
	}

	/**
	 * 配置持久化
	 * @return
	 */
	@Bean
	public JobDetailFactoryBean getJobDetail() {
		JobDetailFactoryBean jobDetai = new JobDetailFactoryBean();
		jobDetai.setJobClass(MyQuartzJobBean.class);
		jobDetai.setDurability(true);
		return jobDetai;
	}

	public static void main(String[] args) {

		String property = System.getProperty("user.dir");

		SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();
		// quartzScheduler.setApplicationContext("applicationContextKey");
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource resource = resolver.getResource("quartz.properties");
	}

}
