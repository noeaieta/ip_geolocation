package com.ipgeolocation;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ipgeolocation.entity.*;
import com.ipgeolocation.services.*;
import com.ipgeolocation.statistics.*;
import com.ipgeolocation.utils.*;

@SpringBootApplication
public class IPGeolocationAPI {	
	
	@Autowired
	private GeolocatedIPService geolocatedIPService;
	
	@Autowired
	private StatisticsService statisticsService;
	
	private static Logger logger = LogManager.getLogger(IPGeolocationAPI.class);
	
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
				  List<StatisticsResponse> statisticsTableDefault = this.geolocatedIPService.getInvocationsPerCountry();
				  GeolocationUtils.showStatistics(statisticsTableDefault);
			}
			
		};
	}
}
