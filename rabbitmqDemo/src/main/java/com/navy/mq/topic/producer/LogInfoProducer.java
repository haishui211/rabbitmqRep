package com.navy.mq.topic.producer;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.navy.mq.config.RabbitmqTopicConfig;

@Component("logInfoProducer")
public class LogInfoProducer {
	
	private static Logger logger = LoggerFactory.getLogger(LogInfoProducer.class);
	
	private static final String exchange = RabbitmqTopicConfig.logTopicExchangeName;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void send(Map<String, Object> msg) {
		if(msg != null) {
			rabbitTemplate.convertAndSend(exchange, RabbitmqTopicConfig.logInfoQueueName, msg);
			logger.info(RabbitmqTopicConfig.logInfoQueueName + " send a message: " + msg);
		}
	}
}
