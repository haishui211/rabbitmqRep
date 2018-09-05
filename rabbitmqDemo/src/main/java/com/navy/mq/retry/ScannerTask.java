package com.navy.mq.retry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.navy.mq.RabbitmqDemoApplicationContext;
import com.navy.mq.ack.AckConst;
import com.navy.mq.ack.AckProducerAbstract;
import com.navy.mq.ack.BusinessMsg;
import com.navy.mq.ack.ProducerMsg;
import com.navy.mq.redis.RedisComponent;

@Component
public class ScannerTask {
	
	@Autowired
	private RabbitmqDemoApplicationContext rabbitmqDemoApplicationContext;
	
	@Autowired
	private RedisComponent redisComponent;
	
	@Scheduled(fixedRate = 1000*60)
    public void scanner() {
		while(true){
			//可根据具体业务要求，实现更为复杂的重发策略
			ProducerMsg producerMsg = (ProducerMsg) redisComponent.getOneFromList(AckConst.ackFailListKey);
			if(producerMsg == null) {
				break;
			}
			resolveOne(producerMsg);
		}
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void resolveOne(ProducerMsg producerMsg){
		BusinessMsg businessMsg = producerMsg.getMsg();
		AckProducerAbstract producer = getProducer(producerMsg.getProducerName());
		producer.send(businessMsg);
	}
	
	public AckProducerAbstract getProducer(String producerName) {
		AckProducerAbstract producer = (AckProducerAbstract) rabbitmqDemoApplicationContext.getBean(producerName);
		return producer;
	}
}
