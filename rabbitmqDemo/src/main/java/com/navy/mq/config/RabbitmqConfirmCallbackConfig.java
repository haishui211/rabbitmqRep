package com.navy.mq.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.navy.mq.ack.ProducerAckComponent;

@Component
public class RabbitmqConfirmCallbackConfig implements ConfirmCallback, InitializingBean{
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private ProducerAckComponent producerAckComponent;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		rabbitTemplate.setConfirmCallback(this);
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		
		if(correlationData == null) {
			return;
		}
		
		if(ack) {
			producerAckComponent.ackSuccessResolve(correlationData.getId());
		}
		else{
			producerAckComponent.ackFailedResolve(correlationData.getId());
		}
	}
	
}
