package com.ipgeolocation.clients.apiconvert;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import com.ipgeolocation.properties.GeolocationIPProperties;

public class APIConvertClient {
	
	private static final String URL_EXCHANGE = "https://api.apilayer.com/fixer/convert?to=";

	private String apikey;
	
	public APIConvertClient() {
		GeolocationIPProperties properties = new GeolocationIPProperties();
		this.apikey = properties.getPropertyValue("APIKEY_APICONVERT");	
	}
	
	public Double getExchangeRate(String from, String to) throws IOException, InterruptedException {
		
		Double rate = 0.0;
		String url = URL_EXCHANGE + from + "&from=" + to + "&amount=1";

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("apikey", this.apikey)
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;

		response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		if (response != null) {
			String responseExchangeRate = response.body();
			JSONObject exchangeRate = new JSONObject(responseExchangeRate);

			rate = exchangeRate.getJSONObject("info").getDouble("rate");	
		}
		

		return rate;
	}
	

}
