package com.navy.mq.fanout;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.navy.mq.config.RabbitmqFanoutConfig;

@Component("uploadPic1Comsumer")
@RabbitListener(queues=RabbitmqFanoutConfig.uploadPicQueue1Name)
public class UploadPic1Comsumer {
	
	private static Logger logger = LoggerFactory.getLogger(UploadPic1Comsumer.class);
	
	@RabbitHandler
	public void resolve(Map<String, Object> msg) {
		logger.info(RabbitmqFanoutConfig.uploadPicQueue1Name + " receive a msg: " + msg);
	}
}
