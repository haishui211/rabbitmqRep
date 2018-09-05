package com.navy.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqFanoutConfig {
	
	public static final String uploadFanoutExchangeName = "uploadFanoutExchange";
	
	public static final String uploadPicQueue1Name = "upload:pic:queue1";
	public static final String uploadPicQueue2Name = "upload:pic:queue2";
	
	@Bean
	public Queue uploadPicQueue1() {
		return new Queue(uploadPicQueue1Name);
	}
	
	@Bean
	public Queue uploadPicQueue2() {
		return new Queue(uploadPicQueue2Name);
	}
	
	@Bean(name=uploadFanoutExchangeName)
	public FanoutExchange uploadFanoutExchange() {
		return new FanoutExchange(uploadFanoutExchangeName);
	}
	
	@Bean
	public Binding bindUploadPicQueue1ToUploadFanoutExchange(Queue uploadPicQueue1, FanoutExchange uploadFanoutExchange) {
		return BindingBuilder.bind(uploadPicQueue1).to(uploadFanoutExchange);
	}
	
	@Bean
	public Binding bindUploadPicQueue2ToUploadFanoutExchange(Queue uploadPicQueue2, FanoutExchange uploadFanoutExchange) {
		return BindingBuilder.bind(uploadPicQueue2).to(uploadFanoutExchange);
	}
}
