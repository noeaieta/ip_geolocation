package com.ipgeolocation.entity;

import java.util.List;

import com.ipgeolocation.repositories.Currency;

public class Country {

	private static final double LATITUDE_BS_AS = -34.6142;
	private static final double LONGITUDE_BS_AS = -58.3811;
	private static final double RADIO_EARTH_KM = 6371.0;
	
	
	private String name;
	private String codeISO;
	private List<String> languages;
	private List<String> timezones;
	private Currency currency;
	private double latitude;
	private double longitude;
	
	public Country() {
		super();
	}
	
	public Country(String name, String codeISO, List<String> languages, List<String> timezones, Currency currency) {
		super();
		this.name = name;
		this.codeISO = codeISO;
		this.languages = languages;
		this.timezones = timezones;
		this.currency = currency;
	}
	
	
	/* Returns the distance between Buenos Aires and countryTo in kilometers from latitudes and longitudes */
	public double getDistanceTo() {        
        double latitudes = Math.toRadians(this.getLatitude() - LATITUDE_BS_AS);  
        double longitudes = Math.toRadians(this.getLongitude() - LONGITUDE_BS_AS);  
        
        double sinLatitudes = Math.sin(latitudes / 2);  
        double sinLongitudes = Math.sin(longitudes / 2);
        
        double calculationFirst = Math.pow(sinLatitudes, 2) + Math.pow(sinLongitudes, 2)  
                * Math.cos(Math.toRadians(LATITUDE_BS_AS)) * Math.cos(Math.toRadians(this.getLatitude()));  
        double calculationSecond = 2 * Math.atan2(Math.sqrt(calculationFirst), Math.sqrt(1 - calculationFirst));
        
        double distance = RADIO_EARTH_KM * calculationSecond;  
   
        return distance;
		
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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
}
