package com.david.unico.common;

public class GcdNumber extends Numbers {

	private Integer gcd;

	
	public GcdNumber() {
		super();
	}
	
	public GcdNumber(Numbers number,int gcd) {
		super(number.getFirst(),number.getSecond());
		this.gcd=gcd;
	}
	

	public Integer getGcd() {
		return gcd;
	}

	public void setGcd(Integer gcd) {
		this.gcd = gcd;
	}

	@Override
	public String toString() {
		return "GcdNumber [gcd=" + gcd + ", Numbers=" + super.toString() + "]";
	}
	
}
