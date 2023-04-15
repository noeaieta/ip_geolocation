package com.ipgeolocation.clients.restcountries;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@Service
public class RestCountriesAPIClient {
	
	public RestCountriesResponse getCountryTimeZoneAndCurrency(String code) throws IOException, InterruptedException {
		HttpResponse<String> responseAPI = null;
		
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://restcountries.com/v3.1/alpha/"+code))
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();

		responseAPI = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		String jsonResponseCountry = responseAPI.body();
		
		ArrayNode responseCountryList = (ArrayNode) (new ObjectMapper()).readTree(jsonResponseCountry);
		JsonNode responseCountry = (JsonNode) responseCountryList.get(0);
		
		String countryCurrencyCode = ((JsonNode) responseCountry.get("currencies")).fieldNames().next();
		JsonNode responseCurrencies = responseCountry.get("currencies");
		
		String countryCurrencyName = responseCurrencies.get(countryCurrencyCode).get("name").toString();
		String[] timezones = new ObjectMapper().readValue(responseCountry.get("timezones").toString(), new TypeReference<String[]>(){});
		
		
		return new RestCountriesResponse(timezones, countryCurrencyCode, countryCurrencyName); 
		
	}
}
