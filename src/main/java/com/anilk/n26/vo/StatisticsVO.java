package com.anilk.n26.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class StatisticsVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private double sum;
	private double avg;
	private double max;
	private double min;
	private long count;

	public double getSum() {
		return sum;
	}

	public StatisticsVO setSum(double sum) {
		this.sum = sum;
		return this;
	}

	public double getAvg() {
		return avg;
	}

	public StatisticsVO setAvg(double avg) {
		this.avg = avg;
		return this;
	}

	public double getMax() {
		return max;
	}

	public StatisticsVO setMax(double max) {
		this.max = max;
		return this;
	}

	public double getMin() {
		return min;
	}

	public StatisticsVO setMin(double min) {
		this.min = min;
		return this;
	}

	public long getCount() {
		return count;
	}

	public StatisticsVO setCount(long count) {
		this.count = count;
		return this;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
