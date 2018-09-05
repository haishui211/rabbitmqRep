package com.navy.mq.producer;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.navy.mq.ack.AckDemoProducer;
import com.navy.mq.ack.BusinessMsg;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AckDemoProducerTest {

	@Autowired
	private AckDemoProducer ackDemoProducer;
	
	private long totals = 10000*3;

	@Test
	public void testSend() throws InterruptedException {

		for (int i = 0; i < totals; i++) {
			try {
				BusinessMsg<Integer, Map<String, Object>> businessData = getTestBusinessMsg(i);
				
				ackDemoProducer.send(businessData);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		Thread.sleep(1000*60*60);
	}
	
	private BusinessMsg<Integer, Map<String, Object>> getTestBusinessMsg(Integer i) {
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("hello", "I am body msg"+i);
		
		BusinessMsg<Integer, Map<String, Object>> businessData = new BusinessMsg<Integer, Map<String, Object>>();
		businessData.setBusinessId(i);
		businessData.setBusinessBody(body);
		return businessData;
	}
}
