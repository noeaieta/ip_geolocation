package com.ipgeolocation.clients.ipapi;

public class IPAPIResponse {
	
	private String countryName;
	private String countryCode;
	private Double latitude;
	private Double longitude;
	private String[] languages;
	
	public IPAPIResponse(String countryName, String countryCode, Double latitude, Double longitude,
			String[] languages) {
		super();
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.languages = languages;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String[] getLanguages() {
		return languages;
	}
	public void setLanguages(String[] languages) {
		this.languages = languages;
	}

	
}
