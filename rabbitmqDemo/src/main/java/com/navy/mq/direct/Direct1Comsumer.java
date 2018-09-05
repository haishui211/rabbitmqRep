package com.navy.mq.direct;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component("direct1Comsumer")
@RabbitListener(queues="direct1")
public class Direct1Comsumer {
	
	private static Logger logger = LoggerFactory.getLogger(Direct1Comsumer.class);
	
	@RabbitHandler
	public void resolve(Map<String,Object> message) {
		logger.info("receive a message: " + message);
	}
}
