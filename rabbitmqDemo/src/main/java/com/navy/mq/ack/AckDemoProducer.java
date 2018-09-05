package com.navy.mq.ack;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.navy.mq.config.RabbitmqDirectConfig;

@Component("ackDemoProducer")
public class AckDemoProducer extends AckProducerAbstract{
	
	private static Logger logger = LoggerFactory.getLogger(AckDemoProducer.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void send(BusinessMsg<Integer, Map<String, Object>> msg) {
		if(msg != null) {
			CorrelationData correlationData = getCorrelationData(msg);
			rabbitTemplate.convertAndSend(RabbitmqDirectConfig.ackDemoQueue, msg, correlationData);
			logger.info("send a message: " + msg);
		}
	}
}
