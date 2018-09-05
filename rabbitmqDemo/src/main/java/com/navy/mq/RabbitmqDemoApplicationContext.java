package com.navy.mq;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 上下文组件
 * 以便获取当前容器中的bean实例
 * @author lnavy
 */
@Component
public class RabbitmqDemoApplicationContext implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		applicationContext = arg0;
	}
	
	public Object getBean(String producerName){
		return applicationContext.getBean(producerName);
	}
}
