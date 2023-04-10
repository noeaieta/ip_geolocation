package com.ipgeolocation.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import com.ipgeolocation.model.Country;
import com.ipgeolocation.model.Currency;

public class IPGeolocationAPIClient {
	
	private static final String GET_URL_GEOLOCATION_IP = "http://ip-api.com/json/";
	private static final String PARAMETERS = "?fields=status,message,country,countryCode,currency,region,regionName,city,zip,lat,lon,timezone,isp,org,as,query";
	
	private String ip;

	public IPGeolocationAPIClient(String ip) {
		this.setIp(ip);
	}

	public Country callIPGeolocationAPI() {
		Country country = new Country();
		Currency currency = new Currency();
		
		// Call to API of geolocation
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(GET_URL_GEOLOCATION_IP + ip + PARAMETERS))
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Response " + response.body());
		String jsonResponseGeolocation = response.body();
		JSONObject responseGeolocation = new JSONObject(jsonResponseGeolocation);
	
		country.setName(responseGeolocation.getString("country"));
		country.setCodeISO(responseGeolocation.getString("countryCode"));
		country.setLatitude(responseGeolocation.getDouble("lat"));
		country.setLongitude(responseGeolocation.getDouble("lon"));
		currency.setName(responseGeolocation.getString("currency"));
		country.setCurrency(currency);
		
		return country;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
