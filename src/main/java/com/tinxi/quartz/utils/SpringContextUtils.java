package com.tinxi.quartz.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtils implements ApplicationContextAware {
	// 实现ApplicationContextAware接口自动注入applicationContext
	private static ApplicationContext applicationContext;

	public SpringContextUtils() {
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtils.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static <T> T getBean(Class<T> clazz) {
		if (applicationContext != null) {
			try {
				return applicationContext.getBean(clazz);
			} catch (Throwable var2) {
				;
			}
		}

		return null;
	}

	public static <T> T getBean(String name) {
		if (applicationContext != null) {
			try {
				return (T) applicationContext.getBean(name);
			} catch (Throwable var2) {
				;
			}
		}

		return null;
	}

	public static <T> T getBean(String name, Class<T> clazz) {
		if (applicationContext != null) {
			try {
				return applicationContext.getBean(name, clazz);
			} catch (Throwable var3) {
				;
			}
		}

		return null;
	}

	public static void register(String name, Object bean) {
		if (applicationContext != null && applicationContext instanceof ConfigurableApplicationContext) {
			ConfigurableApplicationContext context = (ConfigurableApplicationContext)applicationContext;
			DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)context.getBeanFactory();
			BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(bean.getClass());
			beanFactory.registerBeanDefinition(name, beanDefinitionBuilder.getBeanDefinition());
		}

	}

	public static void register(Object bean) {
		register(bean.getClass().getName(), bean);
	}
}
