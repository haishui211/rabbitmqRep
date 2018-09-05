package com.navy.mq.ack;

import java.util.Map;

import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AckProducerAbstract implements InitializingBean{
	
	@Autowired
	private ProducerAckComponent producerAckComponent;
	
	protected String producerName;
	
	public abstract void send(BusinessMsg<Integer, Map<String, Object>> msg);
	
	@Override
	public void afterPropertiesSet() throws Exception {
		String className = this.getClass().getSimpleName();
		String oldFirst = className.substring(0, 1);
		String newFirst = oldFirst.toLowerCase();
		this.producerName = className.replaceFirst(oldFirst, newFirst);
	}
	
	protected CorrelationData getCorrelationData(BusinessMsg<Integer, Map<String, Object>> msg) {
		return producerAckComponent.generateCorrelation(msg, this.producerName);
	}
}
