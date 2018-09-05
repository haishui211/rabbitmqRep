package com.navy.mq.topic.comsumer;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.navy.mq.config.RabbitmqTopicConfig;

@Component("logInfoComsumer")
@RabbitListener(queues=RabbitmqTopicConfig.logInfoQueueName)
public class LogInfoComsumer {
	
	private static Logger logger = LoggerFactory.getLogger(LogInfoComsumer.class);
	
	@RabbitHandler
	public void resolve(Map<String, Object> msg) {
		logger.info(RabbitmqTopicConfig.logInfoQueueName + " receive a msg: " + msg);
	}
}
