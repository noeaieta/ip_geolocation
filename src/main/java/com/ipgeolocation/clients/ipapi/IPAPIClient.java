package com.ipgeolocation.clients.ipapi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ipgeolocation.properties.GeolocationIPProperties;

@Service
public class IPAPIClient {

	private static final String URL_API_GEOLOCATION_IP_ALL_FIELDS = "http://api.ipapi.com/api/";

	@Value("${apikeyIPAPI}")
	private String apikeyIPAPI;

	public IPAPIClient() {
			
	}
	
	
	public IPAPIResponse locateIP(String ip) throws IOException, InterruptedException {
		/*GeolocationIPProperties properties = new GeolocationIPProperties();
		this.apikey = properties.getPropertyValue("APIKEY_IPAPI");*/
		
		HttpResponse<String> responseAPI = null;

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(
						URL_API_GEOLOCATION_IP_ALL_FIELDS + ip + "?access_key="+apikeyIPAPI))
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
