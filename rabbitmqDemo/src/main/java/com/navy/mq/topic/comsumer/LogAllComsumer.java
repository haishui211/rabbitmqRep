package com.navy.mq.topic.comsumer;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.navy.mq.config.RabbitmqTopicConfig;

@Component("logAllComsumer")
@RabbitListener(queues=RabbitmqTopicConfig.logAllQueueName)
public class LogAllComsumer {
	
	private static Logger logger = LoggerFactory.getLogger(LogAllComsumer.class);
	
	@RabbitHandler
	public void resolve(Map<String, Object> msg) {
		logger.info(RabbitmqTopicConfig.logAllQueueName + " receive a msg: " + msg);
	}
}
