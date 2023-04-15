package com.ipgeolocation.clients.ipapi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.ipgeolocation.entity.Country;
import com.ipgeolocation.entity.Currency;

@Service
public class IPAPIClient {

	private static final String URL_API_GEOLOCATION_IP_ALL_FIELDS = "http://api.ipapi.com/api/";

	public IPAPIResponse locateIP(String ip) throws IOException, InterruptedException {
		HttpResponse<String> responseAPI = null;

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(
						URL_API_GEOLOCATION_IP_ALL_FIELDS + ip + "?access_key=19acde721fc63953b3bdc2179824ca3d"))
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
				
		responseAPI = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		String jsonResponseGeolocation = responseAPI.body();
		JSONObject responseGeolocation = new JSONObject(jsonResponseGeolocation);

		JSONArray jsonArrayLanguages = responseGeolocation.getJSONObject("location").getJSONArray("languages");

		String[] languages = new String[jsonArrayLanguages.length()]; 
		for (int i = 0; i < jsonArrayLanguages.length(); i++) {
			languages[i] = jsonArrayLanguages.getJSONObject(i).getString("name");
		}

		IPAPIResponse response = new IPAPIResponse(
				responseGeolocation.getString("country_name"),
				responseGeolocation.getString("country_code"), 
				responseGeolocation.getDouble("latitude"),
				responseGeolocation.getDouble("longitude"), 
				languages
		);
		
		
		return response;

	}

}
