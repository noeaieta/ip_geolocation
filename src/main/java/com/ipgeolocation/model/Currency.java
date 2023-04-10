package com.ipgeolocation.model;

public class Currency {
	
	private String name;
	private Double quotation;
	
	/*public Currency(String name, Double quotation) {
		super();
		this.name = name;
		this.quotation = quotation;
	}*/

	public Currency() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getQuotation() {
		return quotation;
	}

	public void setQuotation(Double quotation) {
		this.quotation = quotation;
	}


}
