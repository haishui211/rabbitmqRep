package com.navy.mq.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqReturnCallbackConfig implements ReturnCallback, InitializingBean{

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		rabbitTemplate.setReturnCallback(this);
	}

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		System.out.println("message: " + message);
		System.out.println("replyCode: " + replyCode);
		System.out.println("replyText: " + replyText);
		System.out.println("exchange: " + exchange);
		System.out.println("routingKey: " + routingKey);
	}

}
