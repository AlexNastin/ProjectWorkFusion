package com.workfusion.project.domain;

import java.io.Serializable;

public class Value implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9072111851942673742L;
	
	private int idValue;
	private long maxValue;
	private long minValue;
	private long meanValue;

	public Value() {
		super();
	}

	public int getIdValue() {
		return idValue;
	}

	public void setIdValue(int idValue) {
		this.idValue = idValue;
	}

	public long getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(long maxValue) {
		this.maxValue = maxValue;
	}

	public long getMinValue() {
		return minValue;
	}

	public void setMinValue(long minValue) {
		this.minValue = minValue;
	}

	public long getMeanValue() {
		return meanValue;
	}

	public void setMeanValue(long meanValue) {
		this.meanValue = meanValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idValue;
		result = prime * result + (int) (maxValue ^ (maxValue >>> 32));
		result = prime * result + (int) (meanValue ^ (meanValue >>> 32));
		result = prime * result + (int) (minValue ^ (minValue >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Value other = (Value) obj;
		if (idValue != other.idValue) {
			return false;
		}
		if (maxValue != other.maxValue) {
			return false;
		}
		if (meanValue != other.meanValue) {
			return false;
		}
		if (minValue != other.minValue) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Value [idValue=" + idValue + ", maxValue=" + maxValue
				+ ", minValue=" + minValue + ", meanValue=" + meanValue + "]";
	}

}
