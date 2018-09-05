package com.navy.mq.ack;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.navy.mq.config.RabbitmqDirectConfig;
import com.navy.mq.redis.RedisComponent;

@Component("ackDemoComsumer")
@RabbitListener(queues=RabbitmqDirectConfig.ackDemoQueue)
public class AckDemoComsumer implements InitializingBean{
	
	private static Logger logger = LoggerFactory.getLogger(AckDemoComsumer.class);
	
	private String cosumeredSetKey;
	
	@Autowired
	private RedisComponent redisComponent;
	
	@RabbitHandler
	public void resolve(BusinessMsg<Integer, Map<String, Object>> msg) {
		
		redisComponent.increment("receiveTimes");
		
		Integer msgId = msg.getBusinessId();
		
		if(!isComsumered(msgId)) {
			
			concreteOperation(msg);
			
			saveComsumered(msgId);
			
			redisComponent.increment("ackDemoComsumerTimes");
		}
	}
	
	private boolean concreteOperation(BusinessMsg<Integer, Map<String, Object>> msg) {
		//具体业务逻辑操作
		logger.info("receive a message: " + msg);
		return true;
	}
	
	private void saveComsumered(Integer msgId) {
		redisComponent.saveWithSet(this.cosumeredSetKey, msgId+"");
	}
	
	private boolean isComsumered(Integer msgId) {
		return redisComponent.isContain(this.cosumeredSetKey, msgId+"");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		cosumeredSetKey = this.getClass().getSimpleName() + "eds";
	}
}
