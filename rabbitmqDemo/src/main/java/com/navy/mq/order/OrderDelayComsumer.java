package com.navy.mq.order;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component("orderDelayComsumer")
@RabbitListener(queues=OrderMqConfig.orderDelayQueue)
public class OrderDelayComsumer {
	
	private static Logger logger = LoggerFactory.getLogger(OrderDelayComsumer.class);
	
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
