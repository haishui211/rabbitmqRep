package com.navy.mq.order;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component("orderDelayProducer")
public class OrderDelayProducer {
	
	private static Logger logger = LoggerFactory.getLogger(OrderDelayProducer.class);
	
	private static final String exchange = OrderMqConfig.orderDelayExchange;
	
	private static final String routeKey = OrderMqConfig.orderDelayRoute;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void send(Map<String, Object> msg) {
		if(msg != null) {
			rabbitTemplate.convertAndSend(exchange, routeKey, msg);
			logger.info("send a message: " + msg);
		}
	}
}
