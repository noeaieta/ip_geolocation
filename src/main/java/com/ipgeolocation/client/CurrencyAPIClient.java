package com.ipgeolocation.client;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class CurrencyAPIClient {
	
	private static final String GET_URL_CURRENCY_INITIAL = "https://api.apilayer.com/fixer/convert?to=";
	private static final String GET_URL_CURRENCY_END = "&from=USD&amount=1";
	private String currencyFrom;
	
	public CurrencyAPIClient(String currencyFrom) {
		super();
		this.currencyFrom = currencyFrom;
	}
	
	
	public Double getQuotationToDollar() throws IOException {

		Double quotation;
		String url = GET_URL_CURRENCY_INITIAL + this.currencyFrom + GET_URL_CURRENCY_END;

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.header("apikey", "XxpO7BqC7SupjS3ak400CLj7Q8EfxJ4O").method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;

		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Response Monedas" + response.body());
		String jsonResponse = response.body();
		JSONObject obj = new JSONObject(jsonResponse);

		/*if (obj.getString("success").equals("true")) {
			System.out.println(obj.getString("rate"));
		}*/

		quotation = obj.getJSONObject("info").getDouble("rate");
		
		return quotation;
	}
	

}
