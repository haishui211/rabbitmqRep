package com.navy.mq.topic.comsumer;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.navy.mq.config.RabbitmqTopicConfig;

@Component("logErrorComsumer")
@RabbitListener(queues=RabbitmqTopicConfig.logErrorQueueName)
public class LogErrorComsumer {
	
	private static Logger logger = LoggerFactory.getLogger(LogErrorComsumer.class);
	
	@RabbitHandler
	public void resolve(Map<String, Object> msg) {
		logger.info(RabbitmqTopicConfig.logErrorQueueName + " receive a msg: " + msg);
	}
}
