package com.ipgeolocation;



import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ipgeolocation.clients.apiconvert.APIConvertClient;
import com.ipgeolocation.clients.restcountries.RestCountriesAPIClient;
import com.ipgeolocation.entity.Country;
import com.ipgeolocation.entity.Currency;
import com.ipgeolocation.entity.Distance;
import com.ipgeolocation.entity.GeolocatedIP;
import com.ipgeolocation.services.CountryService;
import com.ipgeolocation.services.CurrencyService;
import com.ipgeolocation.services.DistanceService;
import com.ipgeolocation.services.GeolocatedIPService;
import com.ipgeolocation.statistics.StatisticsResponse;
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
			switch(args[0]) {
			  case "traceip":
				  GeolocatedIP geolocatedIP = this.geolocatedIPService.geolocateIP(args[1]);
				  GeolocationUtils.showResults(geolocatedIP);
			    break;
			  case "statistics":
				  List<StatisticsResponse> statisticsTable = this.geolocatedIPService.getInvocationsPerCountry();
				  GeolocationUtils.showStatistics(statisticsTable);
			    break;
			  case "statisticNearestDistance":
				  GeolocationUtils.showStatisticsDistances(this.statisticsService.getTheNearestDistance(), "cercana");
			  case "statisticFurthestDistance":
				  GeolocationUtils.showStatisticsDistances(this.statisticsService.getTheFurthestDistance(), "lejana");
			  case "statisticAverageInvocations":
				  GeolocationUtils.showAverageInvocations(this.statisticsService.averageInvocations(this.geolocatedIPService.getInvocationsPerCountry()));
			  default:
			    // code block
			}
			
			//String ip = "200.123.140.97"; 	//String ip = args[0];			
		};
	}
}
