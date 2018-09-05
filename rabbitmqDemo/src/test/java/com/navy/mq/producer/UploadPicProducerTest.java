package com.navy.mq.producer;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.navy.mq.fanout.UploadPicProducer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UploadPicProducerTest {
	
	@Autowired
	private UploadPicProducer uploadPicProducer;
	
	@Test
	public void testSend() {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("hello", "world");
		
		uploadPicProducer.send(msg);
	}
}
