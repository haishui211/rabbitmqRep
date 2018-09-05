package com.navy.mq.topic.producer;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.navy.mq.config.RabbitmqTopicConfig;

@Component("logDebugProducer")
public class LogDebugProducer {
	
	private static Logger logger = LoggerFactory.getLogger(LogDebugProducer.class);
	
	private static final String exchange = RabbitmqTopicConfig.logTopicExchangeName;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void send(Map<String, Object> msg) {
		if(msg != null) {
			rabbitTemplate.convertAndSend(exchange, RabbitmqTopicConfig.logDebugQueueName, msg);
			logger.info(RabbitmqTopicConfig.logDebugQueueName + " send a message: " + msg);
		}
	}
}
