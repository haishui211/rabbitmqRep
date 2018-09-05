package com.navy.mq.fanout;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.navy.mq.config.RabbitmqFanoutConfig;

@Component("uploadPicProducer")
public class UploadPicProducer {
	
	private static Logger logger = LoggerFactory.getLogger(UploadPicProducer.class);
	
	private static final String exchange = RabbitmqFanoutConfig.uploadFanoutExchangeName;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void send(Map<String, Object> msg) {
		if(msg != null) {
			rabbitTemplate.convertAndSend(exchange, "", msg);
			logger.info("send a message: " + msg);
		}
	}
}
