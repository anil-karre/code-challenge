package com.anilk.n26.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ExpiryWrapper<T> {

	private T value;
	private long expiryTimestamp;

	public T getValue() {
		return value;
	}

	public ExpiryWrapper<T> setValue(T value) {
		this.value = value;
		return this;
	}

	public long getExpiryTimestamp() {
		return expiryTimestamp;
	}

	public ExpiryWrapper<T> setExpiryTimestamp(long expiryTimestamp) {
		this.expiryTimestamp = expiryTimestamp;
		return this;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
