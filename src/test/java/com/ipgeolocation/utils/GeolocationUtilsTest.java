package com.ipgeolocation.utils;



import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.ipgeolocation.entity.Country;
import com.ipgeolocation.entity.Currency;
import com.ipgeolocation.entity.GeolocatedIP;

public class GeolocationUtilsTest {
	
	public static final String IP_TEST = "200.10.133.99";
	
	@Test
	public void getCurrentDateTest() {
		assertNotNull(GeolocationUtils.getCurrentDate());
	}
	
	@Test 
	public void getCurrencyWithPriceToShow() throws Exception {
		GeolocatedIP geolocatedIP = new GeolocatedIP(IP_TEST);
		Country country = new Country();
		Currency currency = new Currency();
		
		currency.setName("ARS");
		currency.setName("Peso Argentino");		
		
		String[] languages = new String[1];
		String[] timeZones = new String[1];
		languages[0] = "Español";
		timeZones[0] = "UTC-03:00";
		
		country.setName("Argentina");
		country.setCodeISO("AR");
		country.setLanguages(languages);
		country.setTimezones(timeZones);
		country.setCurrency(currency);
		
		geolocatedIP.setCountry(country);
		
		String expectedResult = "ARS (1 ARS = 200.0 U$S)";
		assertEquals(expectedResult, GeolocationUtils.getCurrencyWithPriceToShow(geolocatedIP));
	}

}
