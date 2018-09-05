package com.navy.mq.producer;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.navy.mq.topic.producer.LogDebugProducer;
import com.navy.mq.topic.producer.LogErrorProducer;
import com.navy.mq.topic.producer.LogInfoProducer;
import com.navy.mq.topic.producer.LogWarnProducer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogProducerTest {
	
	@Autowired
	private LogErrorProducer logErrorProducer;
	
	@Autowired
	private LogWarnProducer logWarnProducer;
	
	@Autowired
	private LogDebugProducer logDebugProducer;
	
	@Autowired
	private LogInfoProducer logInfoProducer;
	
	@Test
	public void testSend() {
		
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("hello", "world");
		
		logErrorProducer.send(msg);
		logWarnProducer.send(msg);
		logDebugProducer.send(msg);
		logInfoProducer.send(msg);
	}
}
