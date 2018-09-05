package com.navy.mq.direct;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("direct1Producer")
public class Direct1Producer{
	
	private static Logger logger = LoggerFactory.getLogger(Direct1Producer.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void send(Map<String,Object> message) throws InterruptedException {
		if(message != null) {
			rabbitTemplate.convertAndSend("direct1", message);
			logger.info("send a message: " + message);
		}
	}
}
