package com.ipgeolocation.model;

public class GeolocatedIP {
	
	private String ip;
	private Country country;
	
	public GeolocatedIP(String ip, Country country) {
		super();
		this.ip = ip;
		this.country = country;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	

}
