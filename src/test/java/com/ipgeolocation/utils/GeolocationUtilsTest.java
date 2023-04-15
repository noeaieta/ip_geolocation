package com.ipgeolocation.utils;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.ipgeolocation.entity.Country;
import com.ipgeolocation.entity.Currency;
import com.ipgeolocation.entity.GeolocatedIP;

public class GeolocationUtilsTest {
	
	/*public static final String IP_TEST = "200.10.133.99";
	
	@Test
	 public void getCurrencyWithQuotationToShowTest() {
		GeolocatedIP geolocatedIP = new GeolocatedIP(IP_TEST);
		Country country = new Country();
		Currency currency = new Currency();
		
		currency.setName("ARS");
		currency.setPrice(200.00);		
		
		List<String> languages = new ArrayList<>();
		List<String> timeZones = new ArrayList<>();
		languages.add("ES");
		timeZones.add("UTC");
		
		country.setName("Argentina");
		country.setCodeISO("AR");
		//country.setLanguages(languages);
		//country.setTimezones(timeZones);
		country.setCurrency(currency);
		
		geolocatedIP.setCountry(country);
		
		String expectedResult = "ARS (1 ARS = 200.0 U$S)";
		assertEquals(expectedResult, GeolocationUtils.getCurrencyWithPriceToShow(geolocatedIP));  
		
    }*/
	
	/*public void showResultsTest() {
		
		GeolocatedIP geolocatedIP = new GeolocatedIP(IP_TEST);
		Country country = new Country();
		Currency currency = new Currency();
		
		currency.setName("ARS");
		currency.setPrice(200.00);		
		
		List<String> languages = new ArrayList<>();
		List<String> timeZones = new ArrayList<>();
		languages.add("ES");
		timeZones.add("UTC");
		
		country.setName("Argentina");
		country.setCodeISO("AR");
		//country.setLanguages(languages);
		//country.setTimezones(timeZones);
		country.setCurrency(currency);
		
		geolocatedIP.setCountry(country);
		
		GeolocationUtils.showResults(geolocatedIP);
		//asserts(GeolocationUtils.showResults(geolocatedIP));
		
	}*/

}
