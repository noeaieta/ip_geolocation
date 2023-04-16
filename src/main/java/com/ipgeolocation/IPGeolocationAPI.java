package com.ipgeolocation;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ipgeolocation.clients.restcountries.RestCountriesAPIClient;
import com.ipgeolocation.entity.Country;
import com.ipgeolocation.entity.Currency;
import com.ipgeolocation.entity.Distance;
import com.ipgeolocation.entity.GeolocatedIP;
import com.ipgeolocation.services.CountryService;
import com.ipgeolocation.services.CurrencyService;
import com.ipgeolocation.services.DistanceService;
import com.ipgeolocation.services.GeolocatedIPService;
import com.ipgeolocation.statistics.StatisticsService;
import com.ipgeolocation.utils.GeolocationUtils;

@SpringBootApplication
public class IPGeolocationAPI {
	
	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	private CountryService countryService;
	
	
	@Autowired
	private GeolocatedIPService geolocatedIPService;
	
	@Autowired
	private DistanceService distanceService;
	
	@Autowired
	private StatisticsService statisticsService;
	
	public static void main(String[] args) {
		SpringApplication.run(IPGeolocationAPI.class, args);
	}
	
	@Bean
	public CommandLineRunner cmd() {
		return (args) -> {
			
			String ip = "104.53.33.253"; 	//String ip = args[0];
		
			GeolocatedIP geolocatedIP = new GeolocatedIP();
			String [] languages = new String[1];
			languages[0] = "Español";
			String [] timezones = new String[1];
			timezones[0] = "UTC-08:00";
			Country country = new Country("CO", "Colombia", languages, timezones, new Currency("CO", "Peso Colombiano"),
					4.6126,  -74.0705);
			
			geolocatedIP.setIp("104.53.33.253");
			geolocatedIP.setCountry(country);
			//this.geolocatedIPService.saveGeolocatedIp(geolocatedIP);
			//this.geolocatedIPService.calculateAndSaveDistance(geolocatedIP);
			
			
			//this.geolocatedIPService.geolocateIP(ip);
			
			
			List<Distance> distances = this.statisticsService.getTheFarthestAndNearestDistance();
			GeolocationUtils.showDistance(distances.get(0));
			GeolocationUtils.showDistance(distances.get(1));
		
		};
	}
}
