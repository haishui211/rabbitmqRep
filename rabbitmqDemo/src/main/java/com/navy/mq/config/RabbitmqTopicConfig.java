package com.navy.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqTopicConfig {
	
	public static final String logTopicExchangeName = "logTopicExchange";
	
	public static final String logAllQueueName = "log.all";
	public static final String logErrorQueueName = "log.error";
	public static final String logWarnQueueName = "log.warn";
	public static final String logDebugQueueName = "log.debug";
	public static final String logInfoQueueName = "log.info";
	
	@Bean
	public Queue logAllQueue() {
		return new Queue(logAllQueueName);
	}
	
	@Bean
	public Queue logErrorQueue() {
		return new Queue(logErrorQueueName);
	}
	
	@Bean
	public Queue logWarnQueue() {
		return new Queue(logWarnQueueName);
	}
	
	@Bean
	public Queue logDebugQueue() {
		return new Queue(logDebugQueueName);
	}
	
	@Bean
	public Queue logInfoQueue() {
		return new Queue(logInfoQueueName);
	}
	
	@Bean(name=logTopicExchangeName)
	public TopicExchange logTopicExchange() {
		return new TopicExchange(logTopicExchangeName);
	}
	
	@Bean
	public Binding bindLogAllToLogTopicExchange(Queue logAllQueue, TopicExchange logTopicExchange) {
		return BindingBuilder.bind(logAllQueue).to(logTopicExchange).with("log.#");
	}
	
	@Bean Binding bindLogErrorQueueToLogTopicExchange(Queue logErrorQueue, TopicExchange logTopicExchange) {
		return BindingBuilder.bind(logErrorQueue).to(logTopicExchange).with(logErrorQueueName);
	}
	
	@Bean Binding bindLogWarnQueueToLogTopicExchange(Queue logWarnQueue, TopicExchange logTopicExchange) {
		return BindingBuilder.bind(logWarnQueue).to(logTopicExchange).with(logWarnQueueName);
	}
	
	@Bean Binding bindLogDebugQueueToLogTopicExchange(Queue logDebugQueue, TopicExchange logTopicExchange) {
		return BindingBuilder.bind(logDebugQueue).to(logTopicExchange).with(logDebugQueueName);
	}
	
	@Bean Binding bindLogInfoQueueToLogTopicExchange(Queue logInfoQueue, TopicExchange logTopicExchange) {
		return BindingBuilder.bind(logInfoQueue).to(logTopicExchange).with(logInfoQueueName);
	}
}
