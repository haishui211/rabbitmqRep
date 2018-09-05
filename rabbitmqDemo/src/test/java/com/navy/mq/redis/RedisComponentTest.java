package com.navy.mq.redis;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.navy.mq.ack.ProducerMsg;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisComponentTest {
	
	@Autowired
	private RedisComponent redisComponent;
	
	@Test
	public void testGetFromSet(){
		Set<String> set = redisComponent.getFromSet("AckDemoComsumeredSet");
		System.out.println(set);
	}
	
	@Test
	public void testT(){
		
		int callBacked = 0;
		int notCallBacked = 0;
		
		for(String key : redisComponent.getAllKeys()){
			try {
				ProducerMsg msg = (ProducerMsg) redisComponent.getObj(key);
				if(msg.isCallBacked()){
					callBacked++;
				}
				else{
					notCallBacked++;
				}
			}
			catch(Exception e) {
				
			}
		}
		
		System.out.println("callBacked: " + callBacked);
		System.out.println("notCallBacked: " + notCallBacked);
	}
}
