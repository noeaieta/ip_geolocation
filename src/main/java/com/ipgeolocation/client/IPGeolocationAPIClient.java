package com.ipgeolocation.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ipgeolocation.entity.Country;
import com.ipgeolocation.entity.Currency;

public class IPGeolocationAPIClient {
	
	private static final String GET_URL_GEOLOCATION_IP = "http://ip-api.com/json/";
	private static final String URL_API_GEOLOCATION_IP_ALL_FIELDS = "http://api.ipapi.com/api/";
	private static final String PARAMETERS = "?fields=status,message,country,countryCode,currency,region,regionName,city,zip,lat,lon,timezone,isp,org,as,query";
	
	private String ip;
	

	public IPGeolocationAPIClient(String ip) {
		this.setIp(ip);
	}

	public void callIPGeolocationAPI(Country country) {
		
		// Call to API of geolocation
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL_API_GEOLOCATION_IP_ALL_FIELDS + ip + "?access_key=19acde721fc63953b3bdc2179824ca3d"))
				//.header("access_key", "19acde721fc63953b3bdc2179824ca3d")
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println("Response " + response.body());
		String jsonResponseGeolocation = response.body();
		JSONObject responseGeolocation = new JSONObject(jsonResponseGeolocation);
	
		country.setName(responseGeolocation.getString("country_name"));
		country.setCodeISO(responseGeolocation.getString("country_code"));
		country.setLatitude(responseGeolocation.getDouble("latitude"));
		country.setLongitude(responseGeolocation.getDouble("longitude"));

		//Init Languages
		JSONArray jsonArrayLanguages = responseGeolocation.getJSONObject("location").getJSONArray("languages");
		
		List<String> listLanguages = new ArrayList<String>();
		String[] languages = new String[10]; //TODO Make a variable static for 10.
		for(int i = 0 ; i < jsonArrayLanguages.length() ; i++){
			listLanguages.add(jsonArrayLanguages.getJSONObject(i).getString("name"));
			languages[i] = jsonArrayLanguages.getJSONObject(i).getString("name");
		}
		country.setLanguages(languages);
		//Finish Languages

	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
