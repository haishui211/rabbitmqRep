package com.navy.mq.direct;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("direct1Producer")
public class Direct1Producer{
	
	private static Logger logger = LoggerFactory.getLogger(Direct1Producer.class);
	
	private AtomicLong atomicLong = new AtomicLong();
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void send(Map<String,Object> message) throws InterruptedException {
		if(message != null) {
			rabbitTemplate.convertAndSend("direct1", message, getCorrelationData());
			logger.info("send a message: " + message);
		}
	}
	
	public CorrelationData getCorrelationData() {
		CorrelationData correlationData = new CorrelationData(String.valueOf(atomicLong.incrementAndGet()));
		return correlationData;
	}
}
