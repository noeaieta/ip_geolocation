package com.ipgeolocation.model;

public class Currency {
	
	private String name;
	private float quotation;
	
	public Currency(String name, float quotation) {
		super();
		this.name = name;
		this.quotation = quotation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getQuotation() {
		return quotation;
	}

	public void setQuotation(float quotation) {
		this.quotation = quotation;
	}

}
