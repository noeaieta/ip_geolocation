package com.ipgeolocation.clients.apiconvert;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class APIConvertClient {
	
	private static final String URL_EXCHANGE = "https://api.apilayer.com/fixer/convert?to=";

	public Double getExchangeRate(String from, String to) throws IOException, InterruptedException {
		Double rate;
		String url = URL_EXCHANGE + from + "&from=" + to + "&amount=1";

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("apikey", "XxpO7BqC7SupjS3ak400CLj7Q8EfxJ4O")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;

		response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		String responseExchangeRate = response.body();
		JSONObject exchangeRate = new JSONObject(responseExchangeRate);

		rate = exchangeRate.getJSONObject("info").getDouble("rate");
		
		return rate;
	}
	

}
