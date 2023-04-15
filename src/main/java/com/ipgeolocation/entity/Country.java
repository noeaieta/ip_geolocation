package com.ipgeolocation.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Country {
	
	@Id
	@Column(name="code_iso")
	private String codeISO;
	
	@Column
	private String name;
	
	@Column(name = "languages", columnDefinition = "text[]")
	private String[] languages;
	
	@Column(name = "timezones", columnDefinition = "text[]")
	private String[] timezones;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "currency")
	private Currency currency;
	
	@Column
	private double latitude;
	
	@Column
	private double longitude;
	
	public Country() {
		super();
	}
	
	
	public Country(String codeISO, String name, String[] languages, String[] timezones, Currency currency,
			double latitude, double longitude) {
		super();
		this.codeISO = codeISO;
		this.name = name;
		this.languages = languages;
		this.timezones = timezones;
		this.currency = currency;
		this.latitude = latitude;
		this.longitude = longitude;
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

	public String[] getLanguages() {
		return languages;
	}

	public void setLanguages (String[] languages) {
		this.languages = languages;
	}

	public String[] getTimezones() {
		return timezones;
	}

	public void setTimezones(String[] timezones) {
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
	
	
	//TODO modify, pass with parameters.
	/* Returns the distance between Buenos Aires and countryTo in kilometers from latitudes and longitudes */
	/*public double getDistanceTo() {        
        double latitudes = Math.toRadians(this.getLatitude() - LATITUDE_BS_AS);  
        double longitudes = Math.toRadians(this.getLongitude() - LONGITUDE_BS_AS);  
        
        double sinLatitudes = Math.sin(latitudes / 2);  
        double sinLongitudes = Math.sin(longitudes / 2);
        
        double calculationFirst = Math.pow(sinLatitudes, 2) + Math.pow(sinLongitudes, 2)  
                * Math.cos(Math.toRadians(LATITUDE_BS_AS)) * Math.cos(Math.toRadians(this.getLatitude()));  
        double calculationSecond = 2 * Math.atan2(Math.sqrt(calculationFirst), Math.sqrt(1 - calculationFirst));
        
        double distance = RADIO_EARTH_KM * calculationSecond;  
   
        return distance;
		
	}*/
	
}
