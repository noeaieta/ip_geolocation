package com.ipgeolocation.entity;

public class GeolocatedIP {
	
	private String ip;
	private Country country;
	
	public GeolocatedIP(String ip) {
		super();
		this.ip = ip;
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
