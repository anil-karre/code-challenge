package com.anilk.n26.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TransactionVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private double amount;
	private long timestamp;

	public double getAmount() {
		return amount;
	}

	public TransactionVO setAmount(double amount) {
		this.amount = amount;
		return this;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public TransactionVO setTimestamp(long timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
