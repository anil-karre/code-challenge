package com.anilk.n26.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Value Object containing transaction details
 * 
 * @author anilk
 *
 */
public class TransactionVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Amount of the transaction
	 */
	private double amount;
	/*
	 * timestampt at which the transaction happened
	 */
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
