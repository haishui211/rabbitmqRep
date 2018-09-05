package com.navy.mq.fanout;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.navy.mq.config.RabbitmqFanoutConfig;

@Component("uploadPic2Comsumer")
@RabbitListener(queues=RabbitmqFanoutConfig.uploadPicQueue2Name)
public class UploadPic2Comsumer {
	
	private static Logger logger = LoggerFactory.getLogger(UploadPic2Comsumer.class);
	
	@RabbitHandler
	public void resolve(Map<String, Object> msg) {
		logger.info(RabbitmqFanoutConfig.uploadPicQueue2Name + " receive a msg: " + msg);
	}
}
