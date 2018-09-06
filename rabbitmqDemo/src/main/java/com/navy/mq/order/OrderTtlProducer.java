package com.navy.mq.order;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("orderTtlProducer")
public class OrderTtlProducer {
	
	private static Logger logger = LoggerFactory.getLogger(OrderTtlProducer.class);
	
	private static final String exchange = OrderMqConfig.orderFanoutExchange;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void send(Map<String, Object> msg) {
		if(msg != null) {
			rabbitTemplate.convertAndSend(exchange, "", msg);
			logger.info("send a message: " + msg);
		}
	}
}
