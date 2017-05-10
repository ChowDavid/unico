package com.david.unico.common;

import java.io.Serializable;

public class Numbers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6645061005264251979L;
	protected Integer first;
	protected Integer second;
	protected Long id;

	
	public Numbers(){
	}
	
	public Numbers(Integer first, Integer second) {
		super();
		this.first = first;
		this.second = second;
	}

	public Integer getFirst() {
		return first;
	}

	public Integer getSecond() {
		return second;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}

	public void setSecond(Integer second) {
		this.second = second;
	}

	@Override
	public String toString() {
		return "Numbers [first=" + first + ", second=" + second + ", id=" + id + "]";
	}



	

}
