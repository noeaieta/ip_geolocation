package com.ipgeolocation.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import com.ipgeolocation.entity.Country;
import com.ipgeolocation.entity.GeolocatedIP;

public class GeolocationUtils {
	
	public static final String DIVIDER = "###############################################";
	
	
	public static void showResults(GeolocatedIP geolocatedIP) {
		System.out.println(DIVIDER);
		System.out.println("IP: " + geolocatedIP.getIp() + ", " + "Fecha Actual: " + getCurrentDate());
		System.out.println("País: " + geolocatedIP.getCountry().getName());
		System.out.println("Código ISO: " + geolocatedIP.getCountry().getCodeISO());
		System.out.println("Idiomas: " + getLanguagesToShow(geolocatedIP));
		System.out.println("Moneda: " + getCurrencyWithPriceToShow(geolocatedIP));
		System.out.println("Hora: " + getHoursInCountry(geolocatedIP)); 
		System.out.println("Distancia estimada: " + geolocatedIP.getCountry().getDistanceTo() + "kms"); //TODO add coordinates
		System.out.println(DIVIDER);

	}

	public static String getCurrentDate() {
		DateTimeFormatter dateTimeCurrent = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");	
		LocalDateTime now = LocalDateTime.now();
		
		return dateTimeCurrent.format(now);
		
	}

	public static String getCurrencyWithPriceToShow(GeolocatedIP geolocatedIP) {
		String currencyToShow = "";
		currencyToShow = geolocatedIP.getCountry().getCurrency().getName() + " (1 " + geolocatedIP.getCountry().getCurrency().getName() + " = " + Double.toString(geolocatedIP.getCountry().getCurrency().getPrice()) + " U$S)";
	
		return currencyToShow;
	}
	
	public static String getLanguagesToShow(GeolocatedIP geolocatedIP) {
		String languagesToShow = "";
		String[] languages = geolocatedIP.getCountry().getLanguages();
		for(int i = 0; i < languages.length; i++) {
			languagesToShow = languagesToShow + languages[i];
		}
		return languagesToShow;
		
	}
	
	
	public static String getHoursInCountry(GeolocatedIP geolocatedIP) {
		String[] prueba = new String[1];
		prueba[0] = "UTC-03:00";
		geolocatedIP.setCountry(new Country());
		geolocatedIP.getCountry().setTimezones(prueba);
		
		System.out.println("UTC INSTANTE " + Instant.now());
		Instant now = Instant.now();
		String datesToShow = Instant.now().toString() + " (UTC) "; 
		for (int i = 0; i < geolocatedIP.getCountry().getTimezones().length; i++) {
			ZonedDateTime nowUTC = ZonedDateTime.ofInstant(now, ZoneId.of(geolocatedIP.getCountry().getTimezones()[i]));
			datesToShow = datesToShow + nowUTC.getHour() + ":" +  nowUTC.getMinute() + ":" + nowUTC.getSecond()
			+ " (" + geolocatedIP.getCountry().getTimezones()[i] + ") ";
		}
	        
	    return datesToShow;  
	  }  
	
}
