package com.navy.mq.ack;

import java.io.Serializable;

/**
 * 业务数据
 * @author lnavy
 * @param <idT>
 * @param <bodyT>
 */
public class BusinessMsg<idT, bodyT> implements Serializable{
	
	private static final long serialVersionUID = 5779159500627951696L;
	
	private idT businessId;
	private bodyT businessBody;
	
	public idT getBusinessId() {
		return businessId;
	}
	public void setBusinessId(idT businessId) {
		this.businessId = businessId;
	}
	public bodyT getBusinessBody() {
		return businessBody;
	}
	public void setBusinessBody(bodyT businessBody) {
		this.businessBody = businessBody;
	}
}
