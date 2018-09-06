package com.navy.mq.producer;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.navy.mq.order.OrderTtlProducer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTtlProducerTest {
	
	@Autowired
	private OrderTtlProducer orderTtlProducer;
	
	@Test
	public void testSend() throws InterruptedException {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("hello", "world");
		
		orderTtlProducer.send(msg);
		
		Thread.sleep(1000*60*60);
	}
}
