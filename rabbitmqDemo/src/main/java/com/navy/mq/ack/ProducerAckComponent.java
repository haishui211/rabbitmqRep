package com.navy.mq.ack;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.navy.mq.redis.RedisComponent;

@Component("producerAckComponent")
public class ProducerAckComponent {
	
	@Autowired
	private RedisComponent redisComponent;
	
	private AtomicLong atomicLong = new AtomicLong();
	
	/**
	 * 生成CorrelationData，以便消息发送后进行确认
	 * @param msg
	 * @param producerName
	 * @return
	 */
	public CorrelationData generateCorrelation(BusinessMsg<Integer, Map<String, Object>> msg, String producerName) {
		
		CorrelationData correlationData = generateCorrelationData();
		
		save(correlationData, msg, producerName);
		
		return correlationData;
	}
	
	/**
	 * 生成确认id
	 * @return
	 */
	private CorrelationData generateCorrelationData() {
		String correlationDataId = String.valueOf(atomicLong.incrementAndGet());
		CorrelationData correlationData = new CorrelationData(correlationDataId);
		return correlationData;
	}
	
	/**
	 * 保存correlation与消息的对应关系
	 * @param correlationData
	 * @param msg
	 * @param producerName
	 */
	private void save(CorrelationData correlationData, BusinessMsg<Integer, Map<String, Object>> msg, String producerName) {
		ProducerMsg producerMsg = new ProducerMsg(producerName, msg);
		redisComponent.saveObj("sendedes", correlationData.getId());
		redisComponent.saveObj(correlationData.getId(), producerMsg);//为节省内存，可将此数据保存的结构调整为hash结构
	}
	
	/**
	 * 确认成功，则将correlationDataId对应的cache删除
	 * @param correlationDataId
	 */
	public void ackSuccessResolve(String correlationDataId){
		redisComponent.increment("ackSuccessResolve");
		redisComponent.delete(correlationDataId);
	}
	
	/**
	 * 处理确认失败的情况
	 * @param correlationDataId
	 */
	public void ackFailedResolve(String correlationDataId){
		redisComponent.increment("ackFailedResolve");
		ProducerMsg sendedFail = (ProducerMsg) redisComponent.getObj(correlationDataId);
		sendedFail.setCallBacked(true);
		redisComponent.saveWithList(AckConst.ackFailListKey, sendedFail);//可考虑将失败消息放置在不同生产者失败队列
		redisComponent.delete(correlationDataId);
	}
}
