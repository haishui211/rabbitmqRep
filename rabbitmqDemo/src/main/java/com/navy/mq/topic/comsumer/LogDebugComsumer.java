package com.navy.mq.topic.comsumer;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.navy.mq.config.RabbitmqTopicConfig;

@Component("logDebugComsumer")
@RabbitListener(queues=RabbitmqTopicConfig.logDebugQueueName)
public class LogDebugComsumer {
	
	private static Logger logger = LoggerFactory.getLogger(LogDebugComsumer.class);
	
	@RabbitHandler
	public void resolve(Map<String, Object> msg) {
		logger.info(RabbitmqTopicConfig.logDebugQueueName + " receive a msg: " + msg);
	}
}
