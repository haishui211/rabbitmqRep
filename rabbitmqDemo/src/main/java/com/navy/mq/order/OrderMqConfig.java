package com.navy.mq.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.BindingBuilder.DirectExchangeRoutingKeyConfigurer;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderMqConfig {
	
	private final String orderTtlQueue = "order.ttl.queue";
	
	public static final String orderDelayQueue = "order.delay.queue";
	
	public static final String orderDelayRoute = "order.delay.route";
	
	public static final String orderFanoutExchange = "orderFanoutExchange";
	
	public static final String orderDelayExchange = "orderDelayExchange";
	
	/**
	args . put( "x-dead-letter-exchange " , "exchange . dlx");
    args . put( "x-dead-letter-routing-key" , " routingkey");
	args.put("x-message-ttl", 10000);
	*/
	
	@Bean
	public Queue orderTtlQueue() {
		Map<String,Object> args = new HashMap<String, Object>();
		args.put("x-message-ttl", 1);
		args.put("x-dead-letter-exchange" , orderDelayExchange);
	    args.put("x-dead-letter-routing-key" , orderDelayRoute);
		return new Queue(orderTtlQueue, true, false, false, args);
	}
	
	@Bean
	public Queue orderDelayQueue() {
		return new Queue(orderDelayQueue);
	}
	
	@Bean(name=orderFanoutExchange)
	public FanoutExchange orderFanoutExchange() {
		return new FanoutExchange(orderFanoutExchange);
	}
	
	@Bean(name=orderDelayExchange)
	public FanoutExchange orderDelayExchange() {
		return new FanoutExchange(orderDelayExchange);
	}
	
	@Bean
	public Binding bindOrderTtlQueueToOrderFanoutExchange(Queue orderTtlQueue, FanoutExchange orderFanoutExchange) {
		return BindingBuilder.bind(orderTtlQueue).to(orderFanoutExchange);
	}
	
	@Bean
	public Binding bindOrderDelayQueueToOrderDelayExchange(Queue orderDelayQueue, FanoutExchange orderDelayExchange) {
		return BindingBuilder.bind(orderDelayQueue).to(orderDelayExchange);
	}
}
