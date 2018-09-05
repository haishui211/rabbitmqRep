package com.navy.mq.ack;

public class ProducerMsg {
	
	private String producerName;
	
	@SuppressWarnings("rawtypes")
	private BusinessMsg msg;
	
	private int retryCount;
	private boolean isCallBacked;
	
	public ProducerMsg() {
		
	}
	
	@SuppressWarnings("rawtypes")
	public ProducerMsg(String producerName, BusinessMsg msg) {
		this.producerName = producerName;
		this.msg = msg;
		this.retryCount = 0;
	}
	
	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}


	@SuppressWarnings("rawtypes")
	public BusinessMsg getMsg() {
		return msg;
	}

	@SuppressWarnings("rawtypes")
	public void setMsg(BusinessMsg msg) {
		this.msg = msg;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public boolean isCallBacked() {
		return isCallBacked;
	}

	public void setCallBacked(boolean isCallBacked) {
		this.isCallBacked = isCallBacked;
	}
}
