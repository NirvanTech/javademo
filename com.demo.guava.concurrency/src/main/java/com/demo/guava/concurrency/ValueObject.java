/**
 * 
 */
package com.demo.guava.concurrency;

/**
 * @author parupati
 *
 */
public class ValueObject {
	
	private String string;
	
	private Long long1;
	
	private Integer integer;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public Long getLong1() {
		return long1;
	}

	public void setLong1(Long long1) {
		this.long1 = long1;
	}

	public Integer getInteger() {
		return integer;
	}

	public void setInteger(Integer integer) {
		this.integer = integer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((integer == null) ? 0 : integer.hashCode());
		result = prime * result + ((long1 == null) ? 0 : long1.hashCode());
		result = prime * result + ((string == null) ? 0 : string.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValueObject other = (ValueObject) obj;
		if (integer == null) {
			if (other.integer != null)
				return false;
		} else if (!integer.equals(other.integer))
			return false;
		if (long1 == null) {
			if (other.long1 != null)
				return false;
		} else if (!long1.equals(other.long1))
			return false;
		if (string == null) {
			if (other.string != null)
				return false;
		} else if (!string.equals(other.string))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ValueObject [string=" + string + ", long1=" + long1
				+ ", integer=" + integer + "]";
	}

	
	
	
	

}
