package com.ipgeolocation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipgeolocation.entity.Country;
import com.ipgeolocation.entity.Distance;
import com.ipgeolocation.repositories.DistanceRepository;

@Service
public class DistanceService {
	
	private static final double LATITUDE_BS_AS = -34.6142;
	private static final double LONGITUDE_BS_AS = -58.3811;
	private static final double RADIO_EARTH_KM = 6371.0;

	@Autowired
	private DistanceRepository distanceRepository;
	
	public Iterable<Distance> getDistances() {
		 return this.distanceRepository.findAll();
	}

	public Distance saveDistance(Distance distance) {
        return this.distanceRepository.save(distance);
	}
	
	
	/* Returns the distance between Buenos Aires and countryTo in kilometers from latitudes and longitudes */
	public double getDistanceTo(Country countryTo) {
		double latitudes = Math.toRadians(countryTo.getLatitude() - LATITUDE_BS_AS);
		double longitudes = Math.toRadians(countryTo.getLongitude() - LONGITUDE_BS_AS);

		double sinLatitudes = Math.sin(latitudes / 2);
		double sinLongitudes = Math.sin(longitudes / 2);

		double calculationFirst = Math.pow(sinLatitudes, 2) + Math.pow(sinLongitudes, 2)
				* Math.cos(Math.toRadians(LATITUDE_BS_AS)) * Math.cos(Math.toRadians(countryTo.getLatitude()));
		double calculationSecond = 2 * Math.atan2(Math.sqrt(calculationFirst), Math.sqrt(1 - calculationFirst));

		double distance = RADIO_EARTH_KM * calculationSecond;

		return distance;

	}

}
