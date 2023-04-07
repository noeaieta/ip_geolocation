package com.ipgeolocation.model;

import java.util.List;

public class Country {

	private String name;
	private String codeISO;
	private List<String> languages;
	private List<String> timezones;
	private Currency currency;
	
	public Country(String name, String codeISO, List<String> languages, List<String> timezones, Currency currency) {
		super();
		this.name = name;
		this.codeISO = codeISO;
		this.languages = languages;
		this.timezones = timezones;
		this.currency = currency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeISO() {
		return codeISO;
	}

	public void setCodeISO(String codeISO) {
		this.codeISO = codeISO;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public List<String> getTimezones() {
		return timezones;
	}

	public void setTimezones(List<String> timezones) {
		this.timezones = timezones;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	
}
