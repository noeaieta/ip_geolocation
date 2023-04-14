package com.ipgeolocation.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ipgeolocation.entity.Country;
import com.ipgeolocation.entity.Currency;

public class CountryAPIClient {

	private String ip;
	
	public CountryAPIClient(String ip) {
		this.ip = ip;
	}
	
	public void callCountryAPI(Country country) {
		
		// Call to API of geolocation
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.apilayer.com/ip_to_location/200.123.140.97"))
				.header("apikey", "XxpO7BqC7SupjS3ak400CLj7Q8EfxJ4O")
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println("Response Country" + response.body());
		
		//Init save timezones data
		String jsonResponseCountry = response.body();
		JSONObject responseCountry = new JSONObject(jsonResponseCountry);

		JSONArray arrayTimezones = responseCountry.getJSONArray("timezones");
		String[] timezones = new String[arrayTimezones.length()]; //TODO modify
		for(int i = 0 ; i < arrayTimezones.length() ; i++){
			//System.out.println("Timezone " + (String) arrayTimezones.get(i));
			timezones[i] = (String) arrayTimezones.get(i);
		}
		country.setTimezones(timezones);
		//Finish save timezone data
		
		//Init Save currencies data
		JSONArray arrayCurrencies = responseCountry.getJSONArray("currencies");
		for(int i = 0 ; i < arrayCurrencies.length() ; i++){
			//System.out.println("Moneda code" + arrayCurrencies.getJSONObject(i).getString("code"));
			//System.out.println("Moneda name" + arrayCurrencies.getJSONObject(i).getString("name"));
			
			country.getCurrency().setCode(arrayCurrencies.getJSONObject(i).getString("code"));
			country.getCurrency().setName(arrayCurrencies.getJSONObject(i).getString("name"));
			
		}
		
		
		//Finish save timezone data
		
		
	}
}
