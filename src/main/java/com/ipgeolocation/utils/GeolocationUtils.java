package com.ipgeolocation.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import com.ipgeolocation.clients.apiconvert.APIConvertClient;
import com.ipgeolocation.entity.Country;
import com.ipgeolocation.entity.Distance;
import com.ipgeolocation.entity.GeolocatedIP;
import com.ipgeolocation.services.DistanceService;

public class GeolocationUtils {
	
	public static final String DIVIDER = "###############################################";
	
	
	public static void showResults(GeolocatedIP geolocatedIP) throws Exception {
		DistanceService distanceService = new DistanceService();
		System.out.println(DIVIDER);
		System.out.println("IP: " + geolocatedIP.getIp() + ", " + "Fecha Actual: " + getCurrentDate());
		System.out.println("País: " + geolocatedIP.getCountry().getName());
		System.out.println("Código ISO: " + geolocatedIP.getCountry().getCodeISO());
		System.out.println("Idiomas: " + getLanguagesToShow(geolocatedIP));
		System.out.println("Moneda: " + getCurrencyWithPriceToShow(geolocatedIP));
		System.out.println("Hora: " + getHoursInCountry(geolocatedIP)); 
		System.out.println("Distancia estimada: " + distanceService.getDistanceTo(geolocatedIP.getCountry()) + " kms"); 
		System.out.println(DIVIDER);

	}

	public static String getCurrentDate() {
		DateTimeFormatter dateTimeCurrent = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");	
		LocalDateTime now = LocalDateTime.now();
		
		return dateTimeCurrent.format(now);
		
	}

	public static String getCurrencyWithPriceToShow(GeolocatedIP geolocatedIP) throws Exception {
		String currencyToShow = "";
		Double rate;
		APIConvertClient apiConvertClient = new APIConvertClient();
		try {
			rate = apiConvertClient.getExchangeRate("USD", geolocatedIP.getCountry().getCurrency().getCode());
			currencyToShow = geolocatedIP.getCountry().getCurrency().getName() + 
					" (1 " + geolocatedIP.getCountry().getCurrency().getName() + " = " + rate + " U$S)";
		
		} catch (Exception exception) {
			throw new Exception("Error while trying to call CurrencyAPI :" + exception.getMessage());
		}
		
		return currencyToShow;
	}
	
	public static String getLanguagesToShow(GeolocatedIP geolocatedIP) {
		String languagesToShow = "";
		String[] languages = geolocatedIP.getCountry().getLanguages();
		for(int i = 0; i < languages.length; i++) {
			languagesToShow = languagesToShow + " " + languages[i];
		}
		return languagesToShow;
		
	}
	
	
	public static String getHoursInCountry(GeolocatedIP geolocatedIP) {	
		Instant now = Instant.now();
		OffsetDateTime dateTimeNowUTC;  
		dateTimeNowUTC = OffsetDateTime.now(ZoneOffset.UTC);
		String datesToShow = dateTimeNowUTC.getHour() + ":" +  dateTimeNowUTC.getMinute() + ":" + dateTimeNowUTC.getSecond() + " (UTC) "; 
		for (int i = 0; i < geolocatedIP.getCountry().getTimezones().length; i++) {
			ZonedDateTime nowUTC = ZonedDateTime.ofInstant(now, ZoneId.of(geolocatedIP.getCountry().getTimezones()[i]));
			datesToShow = datesToShow + nowUTC.getHour() + ":" +  nowUTC.getMinute() + ":" + nowUTC.getSecond()
			+ " (" + geolocatedIP.getCountry().getTimezones()[i] + ") ";
		}
	        
	    return datesToShow;  
	  } 
	
	
	public static void showDistance(Distance distance) {
	
		final Object[][] table = new String[2][];
		table[0] = new String[] { "País", "Distancia"};
		table[1] = new String[] { distance.getGeolocatedIp().getCountry().getName(), distance.getDistance().toString()};

		for (final Object[] row : table) {
		    System.out.format("%-15s%-15s%n", row);
		}
	}
	
}
