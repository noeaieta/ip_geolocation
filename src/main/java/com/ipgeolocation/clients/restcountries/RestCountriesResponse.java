package com.ipgeolocation.clients.restcountries;

public class RestCountriesResponse {
	
	private String[] timezones;
	private String currencyCode;
	private String currencyName;
	
	
	
	public RestCountriesResponse(String[] timezones, String currencyCode, String currencyName) {
		super();
		this.timezones = timezones;
		this.currencyCode = currencyCode;
		this.currencyName = currencyName;
	}

	public String[] getTimezones() {
		return timezones;
	}
	
	public void setTimezones(String[] timezones) {
		this.timezones = timezones;
	}
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	public String getCurrencyName() {
		return currencyName;
	}
	
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}	
	
	

}
