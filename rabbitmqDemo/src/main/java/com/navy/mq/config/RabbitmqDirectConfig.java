package com.navy.mq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqDirectConfig {
	
	private static final String directQueue = "direct1";
	
	public static final String ackDemoQueue = "ack.demo";
	
	@Bean
	public Queue getDirect1Queue() {
		return new Queue(directQueue);
	}
	
	@Bean
	public Queue ackDemoQueue() {
		return new Queue(ackDemoQueue);
	}
}
