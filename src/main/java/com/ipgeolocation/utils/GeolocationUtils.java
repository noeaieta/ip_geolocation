package com.ipgeolocation.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ipgeolocation.model.GeolocatedIP;

public class GeolocationUtils {
	
	public static final String DIVIDER = "###############################################";
	
	
	public static void showResults(GeolocatedIP geolocatedIP) {
		System.out.println(DIVIDER);
		System.out.println("IP: " + geolocatedIP.getIp() + ", " + "Fecha Actual: " + getCurrentDate());
		System.out.println("País: " + geolocatedIP.getCountry().getName());
		System.out.println("Código ISO: " + geolocatedIP.getCountry().getCodeISO());
		System.out.println("Idiomas: "); // TODO complete
		System.out.println("Moneda: " + getCurrencyWithQuotationToShow(geolocatedIP));
		System.out.println("Hora: "); // TODO complete
		System.out.println("Distancia estimada: " + geolocatedIP.getCountry().getDistanceTo());
		System.out.println(DIVIDER);

	}

	public static String getCurrentDate() {
		DateTimeFormatter dateTimeCurrent = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");	
		LocalDateTime now = LocalDateTime.now();
		
		return dateTimeCurrent.format(now);
		
	}

	public static String getCurrencyWithQuotationToShow(GeolocatedIP geolocatedIP) {
		String currencyToShow = "";
		currencyToShow = geolocatedIP.getCountry().getCurrency().getName() + " (1 " + geolocatedIP.getCountry().getCurrency().getName() + " = " + Double.toString(geolocatedIP.getCountry().getCurrency().getQuotation()) + " U$S)";
	
		return currencyToShow;
	}
}
