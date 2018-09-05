package com.navy.mq.direct;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component("direct1Comsumer")
@RabbitListener(queues="direct1")
public class Direct1Comsumer {
	
	private static Logger logger = LoggerFactory.getLogger(Direct1Comsumer.class);
	
	@RabbitHandler
	public void resolve(Map<String,Object> msg, Message message, Channel channel) throws IOException {
		boolean concreteOperationResult = concreteOperation(msg);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), concreteOperationResult);
	}
	
	private boolean concreteOperation(Map<String,Object> msg) {
		//具体业务处理
		logger.info("receive a message: " + msg);
		return false;
	}
}
